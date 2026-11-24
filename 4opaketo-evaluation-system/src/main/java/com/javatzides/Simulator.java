package com.javatzides;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private Random random = new Random();

    //creates numberOfRequests requestsfor 100 floors
    public List<Passengers> passengersRequests (int numberOfRequests) {

        List<Passengers>requests = new ArrayList<>();

        for (int i = 0; i < numberOfRequests; i++) {

            int originFloor = random.nextInt(100);
            int destinationFloor;
            //destinationFloor must bedifferent from originFloor
            do {
                destinationFloor = random.nextInt(100);
            } while (destinationFloor == originFloor);

            //creating a passenger request
            Passengers p = new Passengers(i, originFloor, destinationFloor);
            //inserting it to the list
            requests.add(p);
        }
        return requests;
    }
}
