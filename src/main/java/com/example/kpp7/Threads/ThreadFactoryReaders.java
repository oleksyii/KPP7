package com.example.kpp7.Threads;

import java.util.Random;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryReaders implements ThreadFactory {
    private int threadCount = 1;
    @Override
    public Thread newThread(Runnable r) {
        // Create a new thread with a custom name and priority
        Thread thread = new Thread(r, "Thread-" + threadCount++);


        // Create an instance of the Random class
        Random random = new Random();
        // Generate a random number in the range [0, 2]
        int randomNumber = random.nextInt(3);

        // Set different priorities based on your requirements
        if (randomNumber == 0) {
            thread.setPriority(Thread.MIN_PRIORITY); // Priority 1
        } else if (randomNumber == 1) {
            thread.setPriority(Thread.NORM_PRIORITY); // Priority 5 (default)
        } else {
            thread.setPriority(Thread.MAX_PRIORITY); // Priority 10
        }


        return thread;
    }
}
