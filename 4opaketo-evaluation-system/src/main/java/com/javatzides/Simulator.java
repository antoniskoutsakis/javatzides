package com.javatzides;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private Random random = new Random();

    //creates numberofrequests for 100 floors
    public List<Passengers> passengersRequests (int numberofRequests) {
        List<Passengers>requests = new ArrayList<>();
        for (int i = 0; i <= numberofRequests; i++) {

            int originFloor = random.nextInt(100);
            int destinationFloor;
            //must destinatioFloor different from originFloor
            do {
                destinationFloor = random.nextInt(100);
            } while (destinationFloor == originFloor);

            Passengers p = new Passengers(i, originFloor, destinationFloor);
            requests.add(p);
        }
        return requests;
    }
}
