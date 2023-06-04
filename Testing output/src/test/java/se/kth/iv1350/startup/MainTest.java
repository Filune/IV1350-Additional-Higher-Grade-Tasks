package se.kth.iv1350.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit version 5.9.3 is used.
 */
public class MainTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private PrintStream originalOut;

    @BeforeEach
    public void prepareTest() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        originalOut = System.out;
    }

    @AfterEach
    public void cleanup() {
        outputStream = null;
        printStream = null;
        originalOut = null;
    }

    @Test
    public void testMain() {
        // Arrange
        System.setOut(printStream);
        String startSaleMessage = "---Cashier starts a new sale---";
        String enterItemMessage = "---Cashier enters item identifier---";
    
        // Act
        Main.main(new String[]{});
        System.out.flush();
        System.setOut(originalOut);
        String actualOutput = outputStream.toString();
    
        // Assert
        assertTrue(actualOutput.contains(startSaleMessage), "Output message was not printed out correctly.");
        assertTrue(actualOutput.contains(enterItemMessage), "Output message was not printed out correctly.");
    }
}
