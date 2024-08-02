package com.ooredoo.projetfinetude.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ScheduledJobLauncher {

    @Autowired
    private JobLauncher jobLauncher;



    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BatchConfiguration batchConfiguration;

    @Autowired
    private JobExplorer jobExplorer;



    @Scheduled(cron = "0 07 17 * * ?")
    public void runJob() throws IOException {
        String directoryPath = "C:/Users/lenovo/OneDrive/Bureau/reportss/";
        List<Resource> newFiles = fileUtil.getNewFiles(directoryPath);

        System.out.println("New files detected:");
        for (Resource file : newFiles) {

            String filePath = file.getFile().getAbsolutePath();
            System.out.println(filePath);
            // Check if a job with the same file path has been executed
            if (isJobExecuted(filePath)) {
                System.out.println("Job for file " + file + " has already been executed.");
                continue;
            }
            // Create a JobParameters object for the file
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .addString("filePath", file.getFile().getAbsolutePath())
                    .toJobParameters();


            // Create a step dynamically for each file
            Step step = batchConfiguration.createStep(file);

            // Create a job dynamically for each step
            Job job = batchConfiguration.createJob(step);

            // Run the job
            try {
                jobLauncher.run(job, params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isJobExecuted(String filePath) {
        return jobExplorer.findJobInstancesByJobName("importUserJob", 0, Integer.MAX_VALUE).stream()
                .flatMap(jobInstance -> jobExplorer.getJobExecutions(jobInstance).stream())
                .flatMap(jobExecution -> jobExecution.getJobParameters().getParameters().entrySet().stream())
                .anyMatch(param -> "filePath".equals(param.getKey()) && filePath.equals(param.getValue().getValue()));
    }
}
