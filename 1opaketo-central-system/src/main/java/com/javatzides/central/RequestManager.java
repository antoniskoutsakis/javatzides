package com.javatzides.central;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    //λιστα που αποθηκευει ολα τα αιτηματα
    private final List<Request> requests;

    //κατασκευαστης
    public RequestManager() {
        requests = new ArrayList<>();
    }

    //δημιουργια νεου αιτηματος
    public Request createRequest(int originFloor, int destinationFloor) {
        Request request = new Request(originFloor, destinationFloor);
        requests.add(request);
        return request;
    }
    //αναζητηση αιτηματος με βαση το id
    public Request getRequestById(int requestId) {

        for (Request request : requests) {

            if (request.getRequestId() == requestId) {
                return request;
            }
        }
        return null;
    }

    //επιστροφη ολων τψν αιτηματων
    public List<Request> getAllRequests() {
        return new arrayList<>(requests);
    }

    //επιστροφη αιτηματων που περιμενουν αναθεση
    public List<Request> getPendingRequests() {
        List<Request> pendingRequests = new ArrayList<>();

        for (Request request : requests) {
            

            if (request.getStatus().equal("PENDING")) {
                pendingRequests.add(request);
            }
        }
        return pendingRequests;
    }

    //αναθεση ανελκυστηρα σε αιτημα
    public boolean assignElevator(int requestId, String elevatorId) {
        
        Request request = getRequestById(requestId);

        //ελεγχοσ εγκυροτητας
        if (request == null || !request.getStatus().equals("PENDING") || elevatorId == null || elevatorId.isBlank()) {
            return false;
        }
        //ελεγχος αν ο ανεκλυστηρας εξυπηρετει αλλο αιτημα
        for (Request r : requests) {
            
            if (r.getStatus().equals("ASSIGNED") && elevatorId.equals(r.getAssingedElevator())) {
                return false;
            }
        }
        request.assignElevator(elevatorId);

        return true;
    }
    //ολοκληρωση αιτηματος
    public boolean completeRequest(int requestId) {

        Request request = getRequestById(requestId); 

        if(request == null || !request.getStatus().equals("ASSIGNED")) {
            return false;
        }

        request.setStastus("COMPLETED");

        return true;
    }

}
