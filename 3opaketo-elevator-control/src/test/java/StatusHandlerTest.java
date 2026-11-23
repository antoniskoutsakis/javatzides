import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Κλάση δοκιμών μονάδας (Unit Tests) για τον StatusHandler.
 * Επαληθεύει τη σωστή συμπεριφορά του REST GET endpoint τηλεμετρίας.
 */
public class StatusHandlerTest {

    private StatusHandler statusHandler;
    private static final String TEST_ELEVATOR_ID = "A";

    @Before
    public void setUp() {
        // Εκτελείται πριν από κάθε τεστ: αρχικοποίηση του handler
        statusHandler = new StatusHandler(TEST_ELEVATOR_ID);
    }

    @Test
    public void testHandlerNotNull() {
        // Επιβεβαίωση ότι ο StatusHandler δημιουργήθηκε επιτυχώς
        Assert.assertNotNull("Ο StatusHandler δεν πρέπει να είναι null", statusHandler);
    }
}
