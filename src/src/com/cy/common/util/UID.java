package com.cy.common.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UID {
	
	private static final long ONE_STEP = 10;
    private static final long BASE = 11233333703383453l;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    /**
    * a time (as returned by {@link System#currentTimeMillis()}) at which
    * the VM that this UID was generated in was alive
    * @serial
    */
    private static long time;
    /**
    * 16-bit number to distinguish UID instances created
    * in the same VM with the same time value
    * @serial
    */
    private static short count;
    /**
    * Generates a UID that is unique over time with
    * respect to the host that it was generated on.
    */
    @SuppressWarnings("finally")
	public static String nextUid() {
    	LOCK.lock();
    try {
    	if (lastCount == ONE_STEP) {
    		boolean done = false;
    		while (!done) {
    			long now = System.currentTimeMillis();
    			if (now == lastTime) {
    				// pause for a second to wait for time to change
    				try {
    					Thread.currentThread().sleep(1);
    				}
    				catch (java.lang.InterruptedException e) {
    				} // ignore exception
    				continue;
    			}
    			else {
    				lastTime = now;
    				lastCount = 0;
    				done = true;
    			}
    		}
    	}
    	time = lastTime;
    	count = lastCount++;
    	}
    	finally {
    		LOCK.unlock();
    		return time+""+count;
    	}
    }
        
}
