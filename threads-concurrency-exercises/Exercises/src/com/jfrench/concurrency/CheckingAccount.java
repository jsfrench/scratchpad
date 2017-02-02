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
public class CheckingAccount {

    // Adding volatile here prevents potential problems with caching (each thread has it's own copy of the variable).
    // The volatile keyword ensures each thread accesses the main memory copy of the variable.
    private volatile int balance;

    public CheckingAccount(int initialBalance) {
        balance = initialBalance;
    }

    // Using synchronized prevents a possible "check-then-act" race condition which could allow more to be withdrawn
    // than is available (i.e. if a withdrawal is made between the check and the decrement operation).
    public synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            try {
                Thread.sleep((int) (Math.random() * 200));
            } catch (InterruptedException ie) {
            }
            balance -= amount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        final CheckingAccount ca = new CheckingAccount(100);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    System.out.println(name + " withdraws $10: " + ca.withdraw(10));

                }
            }
        };
        Thread thdHusband = new Thread(r);
        thdHusband.setName("Husband");
        Thread thdWife = new Thread(r);
        thdWife.setName("Wife");
        thdHusband.start();
        thdWife.start();
    }
}
