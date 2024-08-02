package com.ooredoo.projetfinetude.batch;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtil {
    public  List<Resource> getNewFiles(String directoryPath) {
        List<Resource> newFiles = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    Instant fileTime = attrs.lastModifiedTime().toInstant();

                        newFiles.add(new FileSystemResource(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return newFiles;
    }
}
