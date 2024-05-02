package Thread;


public class Main {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        thread1.start(); // Bắt đầu chạy thread

        // In ra thông điệp từ main thread
        for(int i = 1; i <= 5; i++) {
            System.out.println("Main thread is running: " + i);
            try {
                Thread.sleep(1000); // Đợi 1 giây trước khi in ra số tiếp theo
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable); // Tạo một thread từ đối tượng MyRunnable
        thread.start(); // Bắt đầu chạy thread

        // In ra thông điệp từ main thread
        for (int i = 1; i <= 5; i++) {
            System.out.println("Main thread is running: " + i);
            try {
                Thread.sleep(1000); // Đợi 1 giây trước khi in ra số tiếp theo
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        SynchronousExample synchronousExample = new SynchronousExample();
        synchronousExample.synchronousTask();

        AsynchronousExample asynchronousExample = new AsynchronousExample();
        asynchronousExample.asynchronousTask();

        SynchronizedExample synchronizedExample = new SynchronizedExample();

        Thread increThread = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                synchronizedExample.increament();
            }
        });

        Thread decreThread = new Thread(() ->{
            for(int i = 0; i <5; i++){
                synchronizedExample.decreament();
            }
        });

        increThread.start();
        decreThread.start();
    }
}

class MyThread extends Thread {
    public void run() {
        for(int i = 1; i <= 5; i++) {
            System.out.println("Thread is running: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Child thread is running: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class SynchronousExample{
    public void synchronousTask(){
        System.out.println("Task 1");
        System.out.println("Task 2");
        System.out.println("Task 3");
    }
}

class AsynchronousExample{
    public void asynchronousTask() {
        new Thread(() -> System.out.println("Task 1")).start();
        new Thread(() -> System.out.println("Task 2")).start();
        new Thread(() -> System.out.println("Task 3")).start();
    }
}

class SynchronizedExample{
    int count = 0;

    public synchronized void increament(){
        count++;
    }

    public synchronized void decreament(){
        count--;
    }

    public int getCount(){
        return count;
    }
}