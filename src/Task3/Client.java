package Task3;

import java.util.Arrays;

class Client extends Thread {

    private final int[] numbers;

    public Client(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public synchronized void run() {
        while (true) {
            if (numbers[numbers.length-1] != 0){
                for (int i = 0; i < numbers.length; i++) {
                    System.out.print(numbers[i] + " ");
                    numbers[i] = 0;
                }
                System.out.println();
            } else {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
