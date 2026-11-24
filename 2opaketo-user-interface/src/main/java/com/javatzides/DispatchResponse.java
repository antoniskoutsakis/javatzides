package com.javatzides;

public class DispatchResponse {
    private int status;
    private String assignedElevator;
    private String error;

//νο κατασκευαστής επειδή θα δημιουργηθεί αυτόματα από την βιβλιοθήκη Gson όταν κηφθεί απάντηση από το κεντρικό σύστημα

    public int getStatus() {
        return status;
    }

    public String getAssignedElevator() {
        return assignedElevator;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return status == 200;
    }
}
}