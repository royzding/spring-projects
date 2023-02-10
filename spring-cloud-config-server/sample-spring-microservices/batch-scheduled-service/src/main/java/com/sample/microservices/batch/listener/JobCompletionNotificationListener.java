package com.sample.microservices.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sample.microservices.batch.data.model.Coffee;
import com.sample.microservices.batch.repository.ManagerEntityRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;
    private final ManagerEntityRepository meRepo;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate, ManagerEntityRepository meRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.meRepo = meRepo;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++" + jobExecution.getStatus());
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("!!! JOB FINISHED! Time to verify the results");

            String query = "SELECT brand, origin, characteristics FROM coffee";
            //jdbcTemplate.query(query, (rs, row) -> new Coffee(rs.getString(1), rs.getString(2), rs.getString(3)))
           //     .forEach(coffee -> LOGGER.info("Found < {} > in the database.", coffee));
            
            this.meRepo.findAll().forEach(e->System.out.println(e.getName()));
        }
    }
}