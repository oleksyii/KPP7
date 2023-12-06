package com.example.kpp7.Models;

import com.example.kpp7.Threads.Library;
import com.example.kpp7.Threads.Reader;
import com.example.kpp7.Threads.ThreadFactoryReaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class LibraryModel {
    Library library;
    ArrayList<Thread> readers = new ArrayList<>();
    ExecutorService executor;

    public static void main(String[] args){
        LibraryModel model = new LibraryModel(3, 3);
//        try{
//
//            Thread.sleep(10000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//        model.suspendAThread(0);
//
//        try{
//
//            Thread.sleep(10000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//
//        model.resumeAThread(0);

        try{

            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        model.killAThread(0);
    }

    public LibraryModel(int numOfBooks, int numOfReaders){
        library = new Library(numOfBooks);

        // Create an instance of the Random class
        Random random = new Random();

        for (int i = 0; i < numOfReaders; i++) {
            Thread thread = new Thread(new Reader(i, library), "Thread-" + i);
            library.addReader(i, true);
            readers.add(thread);
            int randomNumber = random.nextInt(3);
            if (randomNumber == 0) {
                thread.setPriority(Thread.MIN_PRIORITY); // Priority 1
            } else if (randomNumber == 1) {
                thread.setPriority(Thread.NORM_PRIORITY); // Priority 5 (default)
            } else {
                thread.setPriority(Thread.MAX_PRIORITY); // Priority 10
            }

            thread.start();
        }
//        ThreadFactory threadFactory = new ThreadFactoryReaders();
//
//        // Create a ThreadPool using the custom ThreadFactory
//        // You can adjust the number of threads as needed
//         executor = Executors.newFixedThreadPool(numOfReaders, threadFactory);
//
//        // Submit tasks to the ThreadPool
//        for (int i = 0; i < numOfReaders; i++) {
//            executor.submit(new Reader(i, library));
//        }
//
//        // Shutdown the ThreadPool
//        executor.shutdown();
    }

    public void suspendAThread(int id){
        library.suspendThread(id);
    }

    public void resumeAThread(int id){
        library.resumeThread(id);
    }

    public void killAThread(int id){
        readers.get(id).interrupt();
    }

    public Tempclass constructView(int id){
        return new Tempclass(
                readers.get(id).getName(),
                String.valueOf(readers.get(id).getPriority()),
                String.valueOf(readers.get(id).getState()),
                id
        );
    }

    public String getState(int id){
        return String.valueOf(readers.get(id).getState());
    }
}
