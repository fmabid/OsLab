/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author Sudipta
 */
public class Semaphore {

    private int initialValue = 0;
    private int bound = 0;
    private String name = null;

    public Semaphore(int initialValue,int upperBound, String name){
        this.bound = upperBound;
        this.initialValue = initialValue;
        this.name = name;
    }

    public synchronized void up() throws InterruptedException{
        while(this.initialValue == bound){
            System.out.println(Thread.currentThread().getName()+" is waiting in up for " + name +":");
            wait();
        }
        this.initialValue++;
        System.out.println(Thread.currentThread().getName()+" performed up for " + name +":");
        this.notify();
    }

    public synchronized void down() throws InterruptedException{
        while(this.initialValue == 0){
            System.out.println(Thread.currentThread().getName()+" is waiting in down " + name +":");
            wait();
        }
        this.initialValue--;
        System.out.println(Thread.currentThread().getName()+" peformed down for " + name +":");
        this.notify();
    }
}
