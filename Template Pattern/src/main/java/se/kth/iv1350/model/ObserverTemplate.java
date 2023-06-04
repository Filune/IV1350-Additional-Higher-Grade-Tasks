package se.kth.iv1350.model;


import se.kth.iv1350.utility.Total;

/**
 * This abstract class serves as a template for implementing PaymentObservers. 
 * It provides a default implementation for updating total income and handling errors.
 */
public abstract class ObserverTemplate implements PaymentObserver {

    /**
     * Updates the total income with the specified total price of the sale.
     *
     * @param totalPriceOfSale The total price of the sale.
     */
    public void updateTotalIncome(Total totalPriceOfSale) {
        showTotalIncome(totalPriceOfSale);
    }

    private void showTotalIncome(Total totalPriceOfSale) {
        try {
            doShowTotalIncome(totalPriceOfSale);
        } catch (Exception exc) {
            handleErrors(exc);
        }
    }

    /**
     * Performs the actual operation of showing the total income.
     *
     * @param totalPriceOfSale The total price of the sale.
     * @throws Exception thrown if showing total income fails.
     */
    protected abstract void doShowTotalIncome(Total totalPriceOfSale) throws Exception;

    /**
     * Handles errors that occur while showing or writing the total income.
     *
     * @param exc The exception that occurred.
     */
    protected abstract void handleErrors(Exception exc);
}
