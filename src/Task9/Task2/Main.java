package Task9.Task2;

public class Main {
    public static void main(String[] args) {
        MyThread thread0 = new MyThread();
        MyThread thread1 = new MyThread();

        thread0.start();
        thread1.start();

        System.out.println("Потоки созданный и запущенны: ");
    }
}
