package Task9.Task4;

import java.time.LocalTime;

public class TimePrinter extends Thread{

    private final long pause;

    public TimePrinter(long pause) {
        this.pause = pause;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(pause * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(LocalTime.now());
        }
    }
}
