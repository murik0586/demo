package com.example.demo.service;

import com.example.demo.task.CounterTask;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ThreadDemoService {

    public void runDemo() throws InterruptedException {
        System.out.println("Запуск демо потока");
        Runnable counterRunnable = new CounterTask("CounterWorker", 5);
        Thread counterThread = new Thread(counterRunnable, "CounterWorker");

        Thread loggerThread = new Thread("LoggerThread") {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.out.printf("[LoggerThread] thread=%s, number=%d%n",
                            Thread.currentThread().getName(), i);
                    try {
                        Thread.sleep(150);          // <-- перенесено внутрь цикла
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        };
        counterThread.start();
        loggerThread.start();

        counterThread.join();
        loggerThread.join();

        System.out.println("Демо потоки завершены!");
        printActiveThreads();
    }

    public void printActiveThreads() {
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();
        System.out.println("Количество активных потоков: " + traces.size());
        traces.keySet().stream().
                sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(t -> System.out.printf(
                        " - name = '%s', id =%d, state=%s,daemon = %b%n", t.getName(), t.getId(), t.getState(), t.isDaemon()
                ));
    }
}
