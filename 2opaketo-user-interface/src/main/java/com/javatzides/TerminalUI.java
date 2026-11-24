package com.javatzides;

import java.util.Scanner;

public class TerminalUI {
    private final int currentFloor;
    private final FloorCheck validator;
    private final CentralSystemClient client;

    public TerminalUI(int currentFloor, FloorCheck validator, CentralSystemClient client) {
        this.currentFloor = currentFloor;
        this.validator = validator;
        this.client = client;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ΤΕΡΜΑΤΙΚΟ ΑΝΕΛΚΥΣΤΗΡΑ (ΟΡΟΦΟΣ " + currentFloor + ") ===");

        while (true) {
            System.out.print("\nΕισάγετε όροφο προορισμού (ή -99 για έξοδο): ");
            if (!scanner.hasNextInt()) {
                System.out.println("Παρακαλώ δώστε έγκυρο αριθμό ορόφου.");
                scanner.next();
                continue;
            }

            int destinationFloor = scanner.nextInt();
            if (destinationFloor == -99) break;
            if (!validator.isFloorValid(currentFloor, destinationFloor)) {
                System.out.println("Σφάλμα: Μη έγκυρος όροφος προορισμού (ίδιος όροφος ή εκτός ορίων).");
                continue;
            }

            try {
                ElevatorRequest req = new ElevatorRequest(currentFloor, destinationFloor);
                DispatchResponse res = client.sendRequest(req);
                displayResult(res);
            } catch (Exception e) {
                System.out.println("Αδυναμία σύνδεσης με το Κεντρικό Σύστημα Ελέγχου: " + e.getMessage());
            }
        }
        scanner.close();
    }
    public void displayResult(DispatchResponse res) {
        if (res != null && res.isSuccess()) {
            System.out.println(">>> ΠΑΡΑΚΑΛΩ ΠΗΓΑΙΝΕΤΕ ΣΤΟΝ ΑΝΕΛΚΥΣΤΗΡΑ: " + res.getAssignedElevator() + " <<<");
        } else {
            String err = (res != null && res.getError() != null) ? res.getError() : "Άγνωστο σφάλμα";
            System.out.println("Απόρριψη αιτήματος: " + err);
        }
    }
}

