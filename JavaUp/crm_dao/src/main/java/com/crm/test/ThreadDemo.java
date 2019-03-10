package com.crm.test;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo {
    public class MyThread extends Thread {
        public void run() {
            System.out.println("MyThread.run");
        }
    }

    public class MyThreadRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("MyThreadRunable.run");
        }
    }

    public class MyThreadCallable<T> implements Callable<T> {

        @Override
        public T call() throws Exception {
            System.out.println("MyThreadCallable.run");
            return (T)"sucess";
        }
    }
    @Test
    public void test() throws Exception {
        MyThread myThread1 = new MyThread();
        myThread1.start();

        Thread myThread2 = new Thread(new MyThreadRunable());
        myThread2.start();

        FutureTask<String> stringFutureTask = new FutureTask<>(new MyThreadCallable<String>());
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        System.out.println(stringFutureTask.get());
    }

}
