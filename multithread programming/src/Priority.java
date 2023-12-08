package pl.vistula.nuttithreads;

public class Priority implements Runnable {
    int count;
    Thread thread;
    static boolean stop = false;
    static String currentName;

    // Constructor to create a new thread
    Priority(String name, int priorityValue) {
        thread = new Thread(this, name);
        count = 0;
        currentName = name;
        thread.setPriority(priorityValue);
    }

    public void run() {
        System.out.println(thread.getName() + " starts to operate");
        do {
            count++;
            if (!currentName.equals(thread.getName())) {
                currentName = thread.getName();
                System.out.println(currentName + " is executed");
            }
        } while (!stop && count < 10_000_000); // The first thread that reaches 10 million stops the rest
        System.out.println(thread.getName() + " finishes running");
    }

    public static void main(String[] args) {
        int highestPriorityThreadFirstCount = 0;

        for (int i = 0; i < 10; i++) {
            Priority nt1 = new Priority("High priority thread", Thread.MAX_PRIORITY);
            Priority nt2 = new Priority("Low priority thread", Thread.MIN_PRIORITY);

            try {
                Thread.sleep(100); // Delay to allow system to set thread priorities
                nt1.thread.start();
                nt2.thread.start();

                nt1.thread.join();
                nt2.thread.join();
            } catch (InterruptedException e) {
                System.out.println("The main thread has been interrupted");
            }

            if (nt1.count < nt2.count) {
                highestPriorityThreadFirstCount++;
            }
        }

        System.out.println("The highest priority thread finished first " + highestPriorityThreadFirstCount + " times out of 10 runs.");
    }
}