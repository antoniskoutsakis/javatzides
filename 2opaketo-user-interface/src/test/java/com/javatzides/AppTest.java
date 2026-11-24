package com.javatzides;

public class App {
    public static void main(String[] args) {
        int installedFloor = 0;
        int maxFloor = 100;

        // Αν δοθούν ορίσματα από τη γραμμή εντολών (π.χ. args[0] = όροφος εγκατάστασης)
        if (args.length >= 1) {
            installedFloor = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            maxFloor = Integer.parseInt(args[1]);
        }

        FloorCheck validator = new FloorCheck(0, maxFloor);
        CentralSystemClient client = new CentralSystemClient("http://localhost:8081");
        TerminalUI ui = new TerminalUI(installedFloor, validator, client);

        ui.start();
    }
}