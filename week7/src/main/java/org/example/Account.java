package org.example;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private double balance;

	private ReentrantLock lock = new ReentrantLock();

    /**
     *
     * @param money
     */
    public synchronized void deposit(double money) {
        try {
//			lock.lock();
			double newBalance = balance + money;
			try {
			    Thread.sleep(10);   // Simulating this service takes some processing time
			}
			catch(InterruptedException ex) {
			    ex.printStackTrace();
			}
			balance = newBalance;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
//			lock.unlock();
		}
    }


    public double getBalance() {
        return balance;
    }
}