package com.example.kpp7.StatusObserver;

import com.example.kpp7.HelloController;
import com.example.kpp7.Models.LibraryModel;

import java.util.ArrayList;

public class StatusObserver implements Runnable{

    LibraryModel libraryModel;
    HelloController controller;
    public StatusObserver(LibraryModel lib, HelloController controller){
        libraryModel = lib;
        this.controller = controller;
    }
    @Override
    public void run() {
        ArrayList<Thread> tmpThreads;
                try{

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        while(true){

//            tmpThreads = ;
            for (int i = 0; i < libraryModel.getReaders().size(); i++) {
                controller.UpdateThreadStatus(i, String.valueOf(libraryModel.getReaders().get(i).getState()));
            }
        }
    }
}
