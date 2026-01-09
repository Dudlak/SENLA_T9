package Task3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] numbers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Client client = new Client(numbers);
        Factory factory = new Factory(numbers);

        client.start();
        factory.start();
    }
}
