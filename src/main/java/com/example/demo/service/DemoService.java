package com.example.demo.service;
import org.springframework.stereotype.Service;
@Service
public class DemoService{
    public String getGreeting() {
        return "DemiService is running";
    }
    public void logServiceInfo(){
        System.out.println("DemoService" + getGreeting());
    }
}
