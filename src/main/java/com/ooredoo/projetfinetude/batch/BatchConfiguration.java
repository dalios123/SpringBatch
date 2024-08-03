package com.ooredoo.projetfinetude.batch;


import com.ooredoo.projetfinetude.Entities.Rapport.ReportedDataVol;
import com.ooredoo.projetfinetude.Repositories.ReportedDataVolRepository;
import javax.transaction.Transactional;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;

import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration  {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    private ReportedDataVolRepository reportedDataVolRepository;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    private static final List<SimpleDateFormat> dateFormats = new ArrayList<>();

    static {
        dateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));
        dateFormats.add(new SimpleDateFormat("dd-MM-yyyy"));
        dateFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
    }

    public FlatFileItemReader<ReportedDataVol> reader(Resource resource) {
        // Create a new FlatFileItemReader instance
        FlatFileItemReader<ReportedDataVol> reader = new FlatFileItemReader<ReportedDataVol>() {
            @Override
            public ReportedDataVol doRead() throws Exception {
                ReportedDataVol item = super.doRead();
                // If the item is null or any of its fields are empty, skip it
                if (item == null || isEmpty(item)) {
                    return null;
                }
                return item;
            }

            private boolean isEmpty(ReportedDataVol item) {
                return item.getTime() == null && item.getImsi() == null && item.getMsisdn() == null && item.getReported_data_vol_dl()== null && item.getReported_data_vol_ul() == null && item.getGranted_data_vol_total() == null;
            }
        };
        // Set the resource to the CSV file
        reader.setResource(resource);
        // Skip the header line
        reader.setLinesToSkip(1);
        // Set the line mapper which defines how each line should be parsed
        reader.setLineMapper(lineMapper());
        return reader;
    }


    @Bean
    public DefaultLineMapper<ReportedDataVol> lineMapper() {
        // Create a new DefaultLineMapper instance
        DefaultLineMapper<ReportedDataVol> lineMapper = new DefaultLineMapper<>();

        // Create and configure a DelimitedLineTokenizer
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        // Set the delimiter to a semicolon
        lineTokenizer.setDelimiter(";");
        // Define the names of the columns as they appear in the CSV
        lineTokenizer.setNames("Time", "IMSI", "MSISDN", "Reported data vol DL (GB)",
                "Reported data vol UL (GB)", "Granted data vol total (GB)");
        // Set the tokenizer for the line mapper
        lineMapper.setLineTokenizer(lineTokenizer);

        // Create and configure a BeanWrapperFieldSetMapper
        BeanWrapperFieldSetMapper<ReportedDataVol> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        // Set the target type to ReportedDataVol
        fieldSetMapper.setTargetType(ReportedDataVol.class);
        // Set the field set mapper for the line mapper
        lineMapper.setFieldSetMapper(new CustomFieldSetMapper());

        return lineMapper;
    }

    public static class CustomFieldSetMapper implements FieldSetMapper<ReportedDataVol> {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        @Override
        public ReportedDataVol mapFieldSet(FieldSet fieldSet) throws BindException {
            ReportedDataVol report = new ReportedDataVol();
            report.setTime(getDateField(fieldSet, "Time"));
            report.setImsi(getStringField(fieldSet, "IMSI"));
            report.setMsisdn(getStringField(fieldSet, "MSISDN"));
            if(getDoubleField(fieldSet, "Reported data vol DL (GB)")!=null)
                     report.setReported_data_vol_dl( fieldSet.readFloat("Reported data vol DL (GB)"));
            if(getDoubleField(fieldSet, "Reported data vol UL (GB)")!=null)
                     report.setReported_data_vol_ul( fieldSet.readFloat("Reported data vol UL (GB)"));
            if(getDoubleField(fieldSet, "Granted data vol total (GB)")!=null)
                     report.setGranted_data_vol_total( fieldSet.readFloat("Granted data vol total (GB)"));
            return report;
        }

        private String getStringField(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            return ( value.trim().isEmpty()) ? null : value;
        }
        private Date getDateField(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            if (value == null || value.trim().isEmpty()) {
                return null;
            }
            for (SimpleDateFormat dateFormat : dateFormats) {
                try {
                    return dateFormat.parse(value);
                } catch (ParseException | java.text.ParseException e) {
                    // Continue to next format
                }
            }
            throw new IllegalArgumentException("Invalid date format for field " + fieldName + ": " + value);
        }

        private Double getDoubleField(FieldSet fieldSet, String fieldName) {
            String value = fieldSet.readString(fieldName);
            return ( value.trim().isEmpty()) ? null : fieldSet.readDouble(fieldName);
        }
    }
    @Bean
    @Transactional
    public ItemWriter<ReportedDataVol> writer(ReportedDataVolRepository reportedDataVolRepository) {
        return reportedDataVolRepository::saveAll;
    }

    public Step createStep(Resource resource) {
        return stepBuilderFactory.get("step1")
                .<ReportedDataVol, ReportedDataVol>chunk(10)
                .reader(reader(resource))
                .writer(writer(reportedDataVolRepository))
                .transactionManager(transactionManager)
                .build();
    }


    public Job createJob(Step step) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }


}