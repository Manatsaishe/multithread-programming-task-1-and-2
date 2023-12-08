public class MyThreadVer3 implements Runnable {
    Thread thread;

    MyThreadVer3(String name) {
        thread = new Thread(this, name);
    }

    public static MyThreadVer3 createAndStart(String name) {
        MyThreadVer3 myThreadVer3 = new MyThreadVer3(name);
        myThreadVer3.thread.start();
        return myThreadVer3;
    }

    public void run() {
        System.out.println(thread.getName() + " starts to operate");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(100);
                System.out.println(thread.getName() + " is executed, the counter value: " + count);
            }
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " has been interrupted");
        }
        System.out.println(thread.getName() + " finishes running.");
    }
}

class MoreThreads2 {
    public static void main(String[] args) {
        System.out.println("The main thread starts running.");

        MyThreadVer3 myThread1 = MyThreadVer3.createAndStart("Child thread #1");
        MyThreadVer3 myThread2 = MyThreadVer3.createAndStart("Child thread #2");
        MyThreadVer3 myThread3 = MyThreadVer3.createAndStart("Child thread #3");

        System.out.println(".");

        try {
            myThread1.thread.join();
            System.out.println( "child thread # 1 terminated");
            myThread2.thread.join();
            System.out.println( "child thread # 2 terminated");
            myThread3.thread.join();
            System.out.println( "child thread # 3 terminated");
        } catch (InterruptedException e) {
            System.out.println("The main thread has been interrupted");
        }

        System.out.println("All child threads have finished, and the main thread is done.");
    }
}