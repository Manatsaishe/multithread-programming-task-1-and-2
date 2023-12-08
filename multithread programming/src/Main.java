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