package B_Money;
public class Currency {
    private String name;
    private Double rate;

    /**
     * New Currency
     * The rate argument of each currency indicates that Currency's "universal" exchange rate.
     * Imagine that we define the rate of each currency in relation to some universal currency.
     * This means that the rate of each currency defines its value compared to this universal currency.
     *
     * @param name The name of this Currency
     * @param rate The exchange rate of this Currency
     */
    Currency (String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }

    /** Convert an amount of this Currency to its value in the general "universal currency"
     * (As mentioned in the documentation of the Currency constructor)
     *
     * @param amount An amount of cash of this currency.
     * @return The value of amount in the "universal currency"
     */
    public Integer universalValue(Integer amount) {
        // Check if the amount is null or negative
        if (amount == null || amount < 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid amount");
        }

        // Convert the amount to the universal value based on the exchange rate
        double universalAmount = amount * rate;

        // Convert the result to an integer
        return (int) universalAmount;
    }

    /** Get the name of this Currency.
     * @return name of Currency
     */
    public String getName() {
        return this.name = name;
    }

    /** Get the rate of this Currency.
     *
     * @return rate of this Currency
     */
    public Double getRate() {
        return this.rate = rate;
    }

    /** Set the rate of this currency.
     *
     * @param rate New rate for this Currency
     */
    public void setRate(Double rate) {
        if (rate == null || rate < 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid rate");
        }

        // Set the new exchange rate
        this.rate = rate;
    }

    /** Convert an amount from another Currency to an amount in this Currency
     *
     * @param amount Amount of the other Currency
     * @param othercurrency The other Currency
     */
    public Integer valueInThisCurrency(Integer amount, Currency othercurrency) {
        // Check if the amount or otherCurrency is null, or if the amount is negative
        if (amount == null || this.rate == null || amount < 0) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid amount or currency");
        }

        // Convert the amount to this currency based on the exchange rates
        double convertedAmount = amount * (rate / this.rate);

        // Convert the result to an integer
        return (int) convertedAmount;
    }
}
