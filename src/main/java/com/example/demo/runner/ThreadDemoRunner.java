package com.example.demo.runner;

import com.example.demo.service.DemoService;
import com.example.demo.service.ThreadDemoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ThreadDemoRunner implements CommandLineRunner {
    private final DemoService demoService;
    private final ThreadDemoService threadDemoService;

    public ThreadDemoRunner(DemoService demoService, ThreadDemoService threadDemoService) {
        this.demoService = demoService;
        this.threadDemoService = threadDemoService;
    }

    @Override
    public void run(String... args) throws Exception {
        demoService.logServiceInfo();
        threadDemoService.runDemo();
    }
}