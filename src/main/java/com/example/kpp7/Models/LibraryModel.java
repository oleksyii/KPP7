package com.example.kpp7.Models;

import com.example.kpp7.HelloController;
import com.example.kpp7.StatusObserver.StatusObserver;
import com.example.kpp7.Threads.Library;
import com.example.kpp7.Threads.Reader;
import com.example.kpp7.Threads.ThreadFactoryReaders;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class LibraryModel {
    Library library;
    HelloController controller;
    ArrayList<Thread> readers = new ArrayList<>();
    Thread checker;
    ExecutorService executor;
    ArrayList<Future<?>> futureThreads;

    public static void main(String[] args){
//        LibraryModel model = new LibraryModel(3, 3, co);
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

//        try{
//
//            Thread.sleep(10000);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        model.killAThread(0);
    }

    public LibraryModel(int numOfBooks, int numOfReaders, HelloController controller){
        library = new Library(numOfBooks);
        this.controller = controller;
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
        ThreadFactory threadFactory = new ThreadFactoryReaders();

        // Create a ThreadPool using the custom ThreadFactory
//        ThreadPoolExecutor ex = (ThreadPoolExecutor)Executors.newFixedThreadPool(numOfReaders, threadFactory);
        executor = Executors.newFixedThreadPool(numOfReaders, threadFactory);
        futureThreads = new ArrayList<>();
        // Submit tasks to the ThreadPool
        ArrayList<Reader> rds = new ArrayList<>();
        for (int i = 0; i < numOfReaders; i++) {
            rds.add(new Reader(i, library));
            getReaders().add(new Thread(rds.get(i)));

            futureThreads.add(executor.submit(rds.get(i)));
            library.addReader(i, true);
        }

        checker = new Thread(new StatusObserver(this, controller));

        checker.start();


        // Shutdown the ThreadPool
//        executor.shutdown();
    }

    public void suspendAThread(int id){
        library.suspendThread(id);
    }

    public void resumeAThread(int id){
        library.resumeThread(id);
    }

    public void killAThread(int id){
        getReaders().get(id).interrupt();
//        futureThreads.get(0).resultNow();
//        futureThreads.get(id).
    }

    public LibraryViewModel constructView(int id){
        return new LibraryViewModel(
                getReaders().get(id).getName(),
//                String.valueOf(futureThreads.get(id).isDone()),
                String.valueOf(getReaders().get(id).getPriority()),
                String.valueOf(getReaders().get(id).getState()),
//                String.valueOf(futureThreads.get(id).state()),
                id
        );
    }

    public String getState(int id){
        return String.valueOf(getReaders().get(id).getState());
//        return String.valueOf(futureThreads.get(id).state());
    }

    public synchronized ArrayList<Thread> getReaders(){
        return readers;
    }
}
