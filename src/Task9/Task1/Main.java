package Task9.Task1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    // Переходим в WAITING
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Главный поток захватывает lock ПЕРВЫМ

        // NEW
        System.out.println("NEW: \t\t" + thread.getState());

        thread.start();

        // RUNNABLE
        System.out.println("RUNNABLE: \t" + thread.getState());

        synchronized (lock) {
            // Удерживаем lock - поток должен быть BLOCKED
            Thread.sleep(300);
            System.out.println("BLOCKED: \t" + thread.getState());
        }

        // Поток захватывает lock и переходит в WAITING
        Thread.sleep(100);
        System.out.println("WAITING: \t" + thread.getState());

        // Возвращаем поток в RUNNABLE
        synchronized (lock) {
            lock.notify();
        }

        // TERMINATED
        Thread.sleep(100);
        System.out.println("TERMINATED:\t" + thread.getState());
    }
}