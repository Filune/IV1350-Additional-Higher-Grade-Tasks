package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.utility.Total;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit version 5.9.3 is used.
 */
public class TotalRevenueViewTest {

    private TotalRevenueView totalRevenueView;
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private PrintStream originalOut;

    @BeforeEach
    public void prepareTest() {
        totalRevenueView = new TotalRevenueView();
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        originalOut = System.out;
    }

    @AfterEach
    public void cleanup() { 
        totalRevenueView = null;
        outputStream = null;
        printStream = null;
        originalOut = null;
    }

    @Test
    public void testUpdateTotalIncome() {
        // Arrange
        System.setOut(printStream);
        Total mockTotal = new Total();
    
        // Act
        totalRevenueView.updateTotalIncome(mockTotal);
        System.out.flush();
        System.setOut(originalOut);
        String expectedOutput = "DISPLAY FOR TotalRevenueView: 0.0\n";
    
        // Assert
        assertEquals(expectedOutput, outputStream.toString(), "Display for TotalRevenueView not correctly output.");
    }
    
}
