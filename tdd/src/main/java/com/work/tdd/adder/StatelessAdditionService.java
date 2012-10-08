package com.work.tdd.adder;

import com.work.tdd.adder.vendor.AddCallBack;
import com.work.tdd.adder.vendor.Adder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class StatelessAdditionService implements AdditionService, AddCallBack {

    private static final Logger logger = Logger.getLogger(StatelessAdditionService.class.getName());
    private Lock lock = new ReentrantLock();
    private Condition condition;
    private Adder adder;
    private Integer result;

    public StatelessAdditionService(Adder adder) {
        if (adder == null) {
            throw new IllegalArgumentException("The stateless addition service needs an instance of Vendor Adder interface");
        }
        this.adder = adder;
    }

    public int add(int a, int b) {
        try {
            lock.lock();
            condition = lock.newCondition();
            logger.info("Calling Vendor Addition");
            adder.add(a, b, this);
            logger.info("Awaiting signal from caller");
            condition.await(5, TimeUnit.SECONDS);
            if(result == null) {
                throw new RuntimeException("Vendor addition timed out");
            }
            /** If the Vendor called this in the same thread*/
            return result;
        } catch (InterruptedException e) {
            throw new RuntimeException("Unable to compute the sum", e);
        } finally {
            lock.unlock();
        }
    }

    public void withResult(int result) {
        try {
            logger.info("Attempting to lock for result");
            lock.lock();
            logger.info("Acquired lock for result");
            this.result = result;
            condition.signal();
            logger.info("Signalling Completion");
        } finally {
            lock.unlock();
        }
    }
}
