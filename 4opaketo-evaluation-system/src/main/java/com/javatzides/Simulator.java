package com.javatzides;
import java.util.Random;

public class Simulator {
    private Random random = new Random();

    //creates 100 requests for 100 floors
    for (int i = 0; i <= 100; i++) {

        int originFloor = random.nextInt(100);
        int destinationFloor;
        //must destinatioFloor different from originFloor
        do {
            destinationFloor = random.nextInt(100);
        } while (destinationFloor == originFloor);

        Passengers p1 = new Passengers(i, originFloor, destinationFloor);
    }
}
