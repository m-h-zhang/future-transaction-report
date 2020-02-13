package com.abnamor.app;

import java.io.File;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.abnamor.job.Job;

@SpringBootApplication
@ComponentScan(basePackages = "com.abnamor")
public class FutureTransactionReportApplication {
   
	private final static Logger LOGGER = LoggerFactory.getLogger(FutureTransactionReportApplication.class);

	
	public static void main(String[] args) throws Exception  {
		
		 LOGGER.info("start FutureTransactionReportApplication");		 
		 ConfigurableApplicationContext context = SpringApplication.run(FutureTransactionReportApplication.class, args);
		 Job<InputStream, File> job = ((Job)context.getBean("reportJob")); 	
		 job.run();
		 LOGGER.info("Finish FutureTransactionReportApplication successfully.");
	}

}
