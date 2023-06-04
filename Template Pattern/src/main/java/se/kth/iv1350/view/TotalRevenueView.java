package se.kth.iv1350.view;

import se.kth.iv1350.model.ObserverTemplate;
import se.kth.iv1350.utility.LogHandler;
import se.kth.iv1350.utility.Total;

/**
 * A view class that extends the ObserverTemplate to display the total income.
 */
public class TotalRevenueView extends ObserverTemplate {

    private LogHandler logger = LogHandler.getInstance();
    
    /**
     * Displays the total income based on the specified total price of the sale.
     *
     * @param totalPriceOfSale The total price of the sale.
     * @throws Exception If an error occurs while displaying the total income.
     */
    @Override
    protected void doShowTotalIncome(Total totalPriceOfSale) throws Exception {
        System.out.println("DISPLAY FOR TotalRevenueView: " + totalPriceOfSale.getTotalPriceWithTax().getAmount());
    }

    /**
     * Handles errors that occur while displaying the total income by logging the exception.
     *
     * @param e The exception that occurred.
     */
    @Override
    protected void handleErrors(Exception e) {
        logger.logException(e);
    }
}
