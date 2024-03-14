import org.example.Polynomial;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private static Polynomial polynomial;
    private static int numberOfTests = 0;
    private static int numberOfTestsPassed = 0;

    @BeforeClass
    public static void print(){
        System.out.println("Initializing calculator");
    }

    @Before
    public void init() {
      //  System.out.println("Test number " + numberOfTests + " finished");
        polynomial = new Polynomial();
    }

    @AfterClass
    public static void finalMethod() {
        polynomial = null;
        System.out.println(numberOfTests + " tests have been executed and " + numberOfTestsPassed + " have passed");
    }

    @Before
    public void increment(){
        numberOfTests++;
    }

    @Test
    public void add() {
        polynomial.addTerms(2, 3.0);
        polynomial.addTerms(1, 4.0);
        polynomial.addTerms(0, 5.0);

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addTerms(3, 2.0);
        polynomial2.addTerms(1, -1.0);

        Polynomial result = polynomial.add(polynomial2);

        Polynomial expected = new Polynomial();
        expected.addTerms(3, 2.0);
        expected.addTerms(2, 3.0);
        expected.addTerms(1, 3.0);
        expected.addTerms(0, 5.0);

        assertEquals(expected.toString(), result.toString());
        System.out.println("Test add finished successfully");
        numberOfTestsPassed++;
    }

    @Test
    public void sub() {
        polynomial.addTerms(2, 3.0);
        polynomial.addTerms(1, 4.0);
        polynomial.addTerms(0, 5.0);

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addTerms(3, 2.0);
        polynomial2.addTerms(1, -1.0);

        Polynomial result = polynomial.sub(polynomial2);

        Polynomial expected = new Polynomial();
        expected.addTerms(3, -2.0);
        expected.addTerms(2, 3.0);
        expected.addTerms(1, 5.0);
        expected.addTerms(0, 5.0);

        assertEquals(expected.toString(), result.toString());
        System.out.println("Test sub finished successfully");
        numberOfTestsPassed++;

    }

    @Test
    public void multiply() {
        polynomial.addTerms(2, 3.0);
        polynomial.addTerms(1, 4.0);

        Polynomial polynomial2 = new Polynomial();
        polynomial2.addTerms(1, 2.0);
        polynomial2.addTerms(0, 1.0);

        Polynomial result = polynomial.multiply(polynomial2);

        Polynomial expected = new Polynomial();
        expected.addTerms(3, 6.0);
        expected.addTerms(2, 11.0);
        expected.addTerms(1, 4.0);

        assertEquals(expected.toString(), result.toString());
        System.out.println("Test multiply finished successfully");
        numberOfTestsPassed++;

    }

    @Test
    public void divideWithRemainder() {
        polynomial.addTerms(2, 6.0);
        polynomial.addTerms(1, 7.0);
        polynomial.addTerms(0, 4.0);

        Polynomial divisor = new Polynomial();
        divisor.addTerms(1, 2.0);
        divisor.addTerms(0, 1.0);

        Polynomial[] result = polynomial.divideWithRemainder(divisor);

        Polynomial expectedQuotient = new Polynomial();
        expectedQuotient.addTerms(1, 3.0);
        expectedQuotient.addTerms(0, 2.0);

        Polynomial expectedRemainder = new Polynomial();
        expectedRemainder.addTerms(0, 2.0);

        assertEquals(expectedQuotient.toString(), result[0].toString());
        assertEquals(expectedRemainder.toString(), result[1].toString());
        System.out.println("Test division finished successfully");
        numberOfTestsPassed++;

    }

    @Test
    public void derivative() {
        polynomial.addTerms(3, 3.0);
        polynomial.addTerms(2, 2.0);
        polynomial.addTerms(1, 4.0);

        Polynomial result = polynomial.derivative();

        Polynomial expected = new Polynomial();
        expected.addTerms(2, 9.0);
        expected.addTerms(1, 4.0);
        expected.addTerms(0,4.0);

        assertEquals(expected.toString(), result.toString());
        System.out.println("Test derivative finished successfully");
        numberOfTestsPassed++;

    }

    @Test
    public void integral() {
        polynomial.addTerms(2, 2.0);
        polynomial.addTerms(1, 3.0);
        polynomial.addTerms(0, 4.0);

        Polynomial result = polynomial.integral();

        Polynomial expected = new Polynomial();
        expected.addTerms(3, 0.6666666666666666);
        expected.addTerms(2, 1.5);
        expected.addTerms(1, 4.0);

        assertEquals(expected.toString(), result.toString());
        System.out.println("Test integral finished successfully");
        numberOfTestsPassed++;

    }
}
