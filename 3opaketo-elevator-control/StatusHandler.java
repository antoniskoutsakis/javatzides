import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class StatusHandler implements HttpHandler {
    // Η ιδιωτική μεταβλητή που κρατάει το ονομα του ανελκυστήρα(πχ "Α")
    private final String elevatorId;

    //ο κατασκευαστής που λαμβάνει το id κατά την εκκίνηση
    public StatusHandler(String elevatorId) {
        this.elevatorId = elevatorId;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // έλεγχος ότι το αίτημα ειναι αποκλειστικά Get
        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        // Δημιουργία JSON με χρήση Text Blocks τησ java(τριπλα εισαγωγικά)
        String jsonResponse = """
        {
                  "elevatorId": "%s",
                  "operatingState": "NORMAL",
                  "currentFloor": 3,
                  "doorState": "CLOSED",
                  "currentWeightKg": 320.5,
                  "nextStops": [5, 8]
                }
                """.formatted(this.elevatorId);
                // To %s antikahtistatai aytomata apo to this.elevatorId
        // Ορισμός Headers (δηλώνουμε JSON & UTF-8)
        exchange.getResponseHeaders().set("Content-Type", "application/jspn; charset=UTF-8");
        //Μετατροπή του String σε bytes
        byte[] responseBytes = jsonResponse.getBytes(StandardCharsets.UTF_8);
        // Στεκνουμε τον κωδικό 200 (ΟΚ) και το μέγεθοσ των δεδομένων
        exchange.sendResponseHeaders(200, responseBytes.length);
        // Αποστολή μέσω σωλήνα (OutputStream) με χρήση try-with-resources
        try (OutputStream os = exchange.getResponseBody()) {
            OS.write(responseBytes);
        } // εδώ κλείνει το os δλδ αποδεσμεύεται αυτόματα
    }          
}
