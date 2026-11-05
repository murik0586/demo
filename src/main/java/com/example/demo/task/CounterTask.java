package com.example.demo.task;

public class CounterTask implements Runnable {
    private final String taskName;
    private final int iterations;

    public CounterTask(String taskName, int iterations) {
        this.taskName = taskName;
        this.iterations = iterations;
    }

    @Override
    public void run() {
        for (int i = 1; i <= iterations; i++) {
            System.out.printf("[%s] thread =%s, number = %d%n", taskName, Thread.currentThread().getName(),i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(taskName + " interrupted");
                return;
            }

        }


    }
}
