package se.kth.iv1350.integration;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.model.ObserverTemplate;
import se.kth.iv1350.utility.LogHandler;
import se.kth.iv1350.utility.Total;

/**
 * A class that extends the ObserverTemplate and writes the total income to a file.
 */
public class TotalRevenueFileOutput extends ObserverTemplate {
    private static final String FILE_NAME = "Process-Sale-TotalRevenue.log";
    private LogHandler logger = LogHandler.getInstance();

    /**
     * Writes the total income to a file along with a timestamp.
     *
     * @param totalPriceOfSale The total price of the sale.
     * @throws Exception thrown if an error occurs while writing the total income to the file.
     */
    @Override
    protected void doShowTotalIncome(Total totalPriceOfSale) throws Exception {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String content = String.format(
            "[%s] Total Income: %s%n", timestamp, totalPriceOfSale.getTotalPriceWithTax().getAmount());

        PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true));
        writer.println(content);
        writer.flush();
        writer.close();
    }

    /**
     * Handles errors that occur while writing the total income to the file by logging the exception.
     *
     * @param exc The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception exc) {
        logger.logException(exc);
    }
}