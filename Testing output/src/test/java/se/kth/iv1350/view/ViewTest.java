package se.kth.iv1350.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.LedgerSystem;
import se.kth.iv1350.integration.Printer;
import se.kth.iv1350.startup.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit version 5.9.3 is used.
 */
public class ViewTest {

    private View view;
    private Controller controller;
    private ByteArrayOutputStream outputStream;
    PrintStream printStream;

    @BeforeEach
    public void prepareTest() {
        controller = new Controller(new Printer(), new LedgerSystem());
        view = new View(controller);
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        System.setOut(printStream);
    }

    @AfterEach
    public void cleanup() {
        controller = null;
        view = null;
        outputStream = null;
        printStream = null;
        System.out.flush();
        System.setOut(System.out);
    }

    @Test
    public void testStartSaleMessage() {
        // Arrange
        String startSaleMessage = "---Cashier starts a new sale---";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(startSaleMessage), "Assertion failed for startSaleMessage.");
    }

    @Test
    public void testEnterItemMessage() {
        // Arrange
        String enterItemMessage = "---Cashier enters item identifier---";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(enterItemMessage), "Assertion failed for enterItemMessage.");
    }

    @Test
    public void testDatabaseOfflineMessage() {
        // Arrange
        LocalDateTime saleTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm:ss");
        String formattedSaleTimeForGoods = saleTime.format(formatter);
        String databaseOffline = formattedSaleTimeForGoods + ", ERROR: Failed to scan good. Please try again.";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(databaseOffline), "Assertion failed for databaseOfflineMessage.");
    }

    @Test
    public void testInvalidGoodScannedMessage() {
        // Arrange
        LocalDateTime saleTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm:ss");
        String formattedSaleTimeForGoods = saleTime.format(formatter);
        String invalidGoodScanned = formattedSaleTimeForGoods + ", ERROR: Invalid good has been scanned: Applef";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(invalidGoodScanned), "Assertion failed for invalidGoodScannedMessage.");
    }

    @Test
    public void testScannedGoodsMessage() {
        // Arrange
        String scannedGoodsMessage = String.format("Good: %-15s Price: %-15s VAT: %-15s", "Apple", "30.0", "2.0") + "\n"
        + "Quantity: 1.0" + "\n" + "Running total: 30.0" + "\n" + "Total with VAT: 32.0" + "\n\n"
        + String.format("Good: %-15s Price: %-15s VAT: %-15s", "Hamburger", "65.0", "10.0") + "\n"
        + "Quantity: 1.0" + "\n" + "Running total: 95.0" + "\n" + "Total with VAT: 107.0" + "\n\n"
        + String.format("Good: %-15s Price: %-15s VAT: %-15s", "Cucumber", "10.0", "2.0") + "\n"
        + "Quantity: 1.0" + "\n" + "Running total: 105.0" + "\n" + "Total with VAT: 119.0" + "\n\n";
    
        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(scannedGoodsMessage), "Assertion failed: goods not printed correctly.");
    }
    

    @Test
    public void testDisplayTotalVATMessage() {
        // Arrange
        String displayTotalVATMessage = "---Cashier displays total with VAT---";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(displayTotalVATMessage), "Assertion failed for displayTotalVATMessage.");
    }

    @Test
    public void testTotalWithVATMessage() {
        // Arrange
        String totalWithVAT = "Total with VAT: 119.0";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(totalWithVAT), "Assertion failed for totalWithVATMessage.");
    }

    @Test
    public void testEnterPaidAmountMessage() {
        // Arrange
        String enterPaidAmountMessage = "---Cashier enters the paid amount---";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(enterPaidAmountMessage), "Assertion failed for enterPaidAmountMessage.");
    }

    @Test
    public void testSaleTimeInReceiptMessage() {
        // Arrange
        LocalDateTime saleTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        String formattedSaleTimeForReceipt = saleTime.format(formatter);
        String saleTimeInReceipt = formattedSaleTimeForReceipt;

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(saleTimeInReceipt), "Assertion failed for saleTimeInReceiptMessage.");
    }

    @Test
    public void testGoodsInReceiptMessage() {
        // Arrange
        String goodsInReceipt = String.format("Good: %-15s Price: %-15s VAT: %-15s", "Hamburger", "65.0", "10.0") +
                "\tQuantity: 1.0" + "\n" +
                String.format("Good: %-15s Price: %-15s VAT: %-15s", "Apple", "30.0", "2.0") +
                "\tQuantity: 1.0" + "\n" +
                String.format("Good: %-15s Price: %-15s VAT: %-15s", "Cucumber", "10.0", "2.0") +
                "\tQuantity: 1.0" + "\n" +
                "Price: 105.0\nVAT: 14.0\nGrand total: 119.0";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(goodsInReceipt), "Assertion failed: goodsInReceipt not printed correctly.");
    }

    @Test
    public void testTotalRevenueViewDisplayMessage() {
        // Arrange
        String TotalRevenueViewDisplay = "DISPLAY FOR TotalRevenueView: 119.0";
        Main.main(null);

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(TotalRevenueViewDisplay), "Assertion failed for TotalRevenueViewDisplayMessage.");
    }

    @Test
    public void testEnteredAmountMessage() {
        // Arrange
        String enteredAmount = "Entered amount is: 3000.0";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(enteredAmount), "Assertion failed for enteredAmountMessage.");
    }

    @Test
    public void testChangeMessage() {
        // Arrange
        String change = "Change for customer: 2881.0";

        // Act
        view.sample();
        String actualOutput = outputStream.toString();

        // Assert
        assertTrue(actualOutput.contains(change), "Assertion failed for changeMessage.");
    }

}
