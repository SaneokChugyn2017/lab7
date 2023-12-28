package org.example;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int CARS_COUNT = 9;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(CARS_COUNT + 1);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("!НА СТАРТ!");
        System.out.println("?ВНИМАНИЕ?");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 200 + (int) (Math.random() * 200), BARRIER, BARRIER);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        try {
            BARRIER.await();
            System.out.println("!МАРШ!");

            try {
                BARRIER.await();
                BARRIER.await();
                System.out.println("?ФИНИЩ?");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
