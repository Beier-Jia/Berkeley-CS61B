import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the Flik class. */
public class FlikTest {
    /** Tests the Flik.isSameNumber method. */
    @Test
    public void testIsSameNumber() {
        int a = 128;
        int b = 128;
        int c = 500;
        assertTrue(Flik.isSameNumber(a, b));
        assertTrue(!Flik.isSameNumber(a, c));
    }
}

