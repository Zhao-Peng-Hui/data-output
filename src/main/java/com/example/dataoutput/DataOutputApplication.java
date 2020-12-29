package com.example.dataoutput;

import com.example.dataoutput.entity.UserEntity;
import com.example.dataoutput.strategy.Export;
import com.example.dataoutput.strategy.UserExportStrategy;
import com.example.dataoutput.thread.ExportTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class DataOutputApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DataOutputApplication.class, args);
        UserExportStrategy userExportStrategy = run.getBean(UserExportStrategy.class);
        ExportTask<UserEntity, Map> exportTask = new ExportTask<>(userExportStrategy);
    }

}
