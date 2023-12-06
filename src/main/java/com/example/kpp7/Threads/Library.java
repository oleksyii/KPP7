package com.example.kpp7.Threads;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    Integer totalBooks = 0;
    HashMap<Integer, Boolean> readers = new HashMap<>();
    private final Lock lock = new ReentrantLock();
    private final Lock lockReading = new ReentrantLock();

    public Library(Integer totalBooks) {
        this.totalBooks = totalBooks;
    }

    public Integer getTotalBooks() {
        return totalBooks;
    }

    public void setTotalBooks(Integer totalBooks) {
        this.totalBooks = totalBooks;
    }

    public void borrowBook(){
        lock.lock();
        try {
            if (totalBooks > 0) {
                System.out.println(Thread.currentThread().getName() + " Borrowing a book.");
                totalBooks--;
                System.out.println("Total books available: " + totalBooks);
//                Thread.sleep(5000);
            } else {
                System.out.println(Thread.currentThread().getName() + " No books available to borrow.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void returnABook(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Returning a book.");
            totalBooks++;
            System.out.println("Total books available: " + totalBooks);
//          Thread.sleep(5000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void addReader(Integer id, Boolean status){
        readers.put(id, status);
    }

    public Boolean getMe(Integer id){
        return readers.get(id);
    }

    private boolean isSuspended = false;

    public synchronized void suspendThread(Integer id) {
        System.out.println("Thread " + id + " is suspended");
        readers.put(id, false);
    }

    public synchronized void resumeThread(Integer id) {
        readers.put(id, true);
        System.out.println("Thread " + id + " is resumed");
        notify();
    }

    public synchronized void checkAndSuspend(Integer id) throws InterruptedException {
        while (!readers.get(id)) {

            wait();
        }
    }



}
