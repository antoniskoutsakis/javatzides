package com.javatzides;

//βιβλιοθήκη google για μετατροπή κλασεων σε JSON και αντιστροφα
import com.google.gson.Gson;

//Η βιβλιοθήκη δικτύου που κάνει την HTTP κλήση (POST) προς τον server
import okhttp3.*;

//Το σφάλμα που πετάει η Java αν ο server του Κεντρικού Συστήματος είναι κλειστός ή κοπεί η σύνδεση
import java.io.IOException;


public class CentralSystemClient {
    private final String serverUrl;
    private final OkHttpClient httpClient;
    private final Gson gson;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public CentralSystemClient(String serverUrl) {
        this.serverUrl = serverUrl;
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    public DispatchResponse sendRequest(ElevatorRequest request) throws IOException {
        
        String jsonBody = gson.toJson(request);    //Παίρνω το αντικείμενο ElevatorRequest και το κάνει String
        RequestBody body = RequestBody.create(jsonBody, JSON);  //Βάζω αυτό το String μέσα στον «φάκελο» αποστολής του OkHttp
        Request httpRequest = new Request.Builder()
                .url(serverUrl + "/api/v1/central/requests")
                .post(body)
                .build();

        try (Response response = httpClient.newCall(httpRequest).execute()) {
            String responseBody = response.body() != null ? response.body().string() : "{}";
            return gson.fromJson(responseBody, DispatchResponse.class);
        }
    }
}
