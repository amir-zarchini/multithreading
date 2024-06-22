/**
 * Threads Creation - Part 2. Thread Inheritance
 * https://www.udemy.com/java-multithreading-concurrency-performance-optimization
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static final int MAX_PASSWORD = 9999;
    private static final int COLUMN_WIDTH = 15;

    public static void main(String[] args) {
        Random random = new Random();

        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {
                int padding = 1;
                String formattedMessage = String.format("%" + padding + "s%s", "", this.getName()+ " -> guess-"+guess);
                System.out.println(formattedMessage);
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {
                int padding = 3 * COLUMN_WIDTH;
                String formattedMessage = String.format("%" + padding + "s%s", "", this.getName()+ " -> guess-"+guess);
                System.out.println(formattedMessage);
                if (vault.isCorrectPassword(guess)) {
                    System.out.print(this.getName() + " guessed the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {

        public PoliceThread() {
            this.setName(this.getClass().getSimpleName());
        }

        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                int padding = 6 * COLUMN_WIDTH;
                String formattedMessage = String.format("%" + padding + "s%s", "", this.getName()+ " number is:" + i);
                System.out.println(formattedMessage);
            }

            System.out.println("Game over for you hackers");
            System.exit(0);
        }

        @Override
        public void start() {
            System.out.println("Starting thread " + this.getName());
            super.start();
        }
    }
}
