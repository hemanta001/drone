package com.drone.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Override
    @Bean(name="createLog")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("drone-");
        executor.initialize();
        return executor;
    }

}
