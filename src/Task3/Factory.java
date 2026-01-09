package Task3;

import java.util.Random;

class Factory extends Thread {
    Random random = new Random();
    private final int[] numbers;

    public Factory(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public synchronized void run() {
        while (true) {
            for (int i = 0; i < numbers.length; i++) {
                int rand = random.nextInt() / 10000000;
                if (rand != 0) {
                    numbers[i] = rand;
                } else {
                    numbers[i] += 1;
                }
            }

            while (numbers[numbers.length-1] != 0){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}