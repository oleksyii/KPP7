package com.example.kpp7.Threads;

public class Reader implements Runnable {
    int id;
    Library library;

    public Reader(int id, Library lib){
        this.id = id;
        this.library = lib;
    }

    @Override
    public void run() {
//        System.out.println(Thread.currentThread().getName() + " - Priority: " + Thread.currentThread().getPriority());
        while(!Thread.interrupted()){
            try{
                library.checkAndSuspend(id);

                library.borrowBook();
                Thread.sleep(5000);
                library.returnABook();
            } catch (InterruptedException e){
//                e.printStackTrace();
                System.out.println("Thread-"+id+" was interrupted");
                return;
            }
        }
        System.out.println("Thread-"+id+" was interrupted");
    }
}
