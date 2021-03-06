/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jfrench.concurrency;

/**
 *
 * @author french2
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (!Thread.interrupted()) {
                    System.out.println(name + ": " + count++);
                }
            }
        };
        
        Thread thdA = new Thread(r);
        Thread thdB = new Thread(r);
        thdA.start();
        thdB.start();
        
        while (true) {
            double n = Math.random();
            if (n >= 0.49999999 && n<= 0.50000001)
                break;
        }
        thdA.interrupt();
        thdB.interrupt();
    }
    
}
