package com.epam.mentoring.troubleshooting.hw1;

/**
 * Created by Endeg on 12.09.2016.
 */
public class RunDeadlock {

    public static final String RES_1 = "RES_1";
    public static final String RES_2 = "RES_2";

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) throws InterruptedException {
        final Thread thread1 = new Thread(new ResLocker(RES_1, RES_2));
        final Thread thread2 = new Thread(new ResLocker(RES_2, RES_1));

        log("Running two threads with shared resources");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        log("No deadlock occurred");
    }

    static class ResLocker implements Runnable {

        private final String res1;
        private final String res2;

        ResLocker(String res, String res2) {
            this.res1 = res;
            this.res2 = res2;
        }

        @Override
        public void run() {
            log("Locking resource " + res1);
            synchronized (res1) {
                log(res1 + " is locked");
                rest();
                log("Locking resource " + res2);
                synchronized (res2) {
                    log(res2 + " is locked");
                }
                log("Resource " + res2 + " is free now");
            }
            log("Resource " + res1 + " is free now");
        }

        private void rest() {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
