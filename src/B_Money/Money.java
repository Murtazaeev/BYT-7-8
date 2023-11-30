package B_Money;

public class Money implements Comparable {
    private int amount;
    private Currency currency;

    /**
     * New Money
     *
     * @param amount   The amount of money
     * @param currency The currency of the money
     */
    Money (Integer amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    /**
     * Return the amount of money.
     * @return Amount of money in Double type.
     */
    public Integer getAmount() {
        return this.amount = amount;
    }

    /**
     * Returns the currency of this Money.
     * @return Currency object representing the currency of this Money
     */
    public Currency getCurrency() {
        return this.currency = currency;
    }

    /**
     * Returns the amount of the money in the string form "(amount) (currencyname)", e.g. "10.5 SEK".
     * Recall that we represent decimal numbers with integers. This means that the "10.5 SEK" mentioned
     * above is actually represented as the integer 1050
     *  @return String representing the amount of Money.
     */
    public String toString() {
        double decimalAmount = amount / 100.0;

        // Format the string in the "(amount) (currencyname)" format
        return String.format("%.2f %s", decimalAmount, currency);
    }

    /**
     * Gets the universal value of the Money, according the rate of its Currency.
     * @return The value of the Money in the "universal currency".
     */
    public Integer universalValue() {
        double universalAmount = amount * currency.getRate();
        // Convert the result to an integer, assuming the universal value is an integer
        return (int) universalAmount;
    }

    /**
     * Check to see if the value of this money is equal to the value of another Money of some other Currency.
     * @param other The other Money that is being compared to this Money.
     * @return A Boolean indicating if the two monies are equal.
     */
    public Boolean equals(Money other) {
        // Check if the other object is null or not an instance of Money
        if (other == null || !(other instanceof Money)) {
            return false;
        }

        // Cast the other object to Money
        Money otherMoney = (Money) other;

        // Check if the amount and currency are equal
        return this.amount == otherMoney.amount && this.currency.equals(otherMoney.currency);

    }

    /**
     * Adds a Money to this Money, regardless of the Currency of the other Money.
     * @param other The Money that is being added to this Money.
     * @return A new Money with the same Currency as this Money, representing the added value of the two.
     * (Remember to convert the other Money before adding the amounts)
     */
    public Money add(Money other) {
        if (other == null || !(other instanceof Money)) {
            // Handle invalid input
            return null;
        }

        // Cast the other object to Money
        Money otherMoney = (Money) other;

        // Check if the currencies are the same
        if (!this.currency.equals(otherMoney.currency)) {
            // Convert the otherMoney to the same currency
            int convertedAmount = (int) (otherMoney.amount * otherMoney.currency.getRate() / this.currency.getRate());

            // Perform the addition with the converted amount
            return new Money(this.amount + convertedAmount, this.currency);
        }

        // Currencies are the same, perform the addition directly
        return new Money(this.amount + otherMoney.amount, this.currency);

    }

    /**
     * Subtracts a Money from this Money, regardless of the Currency of the other Money.
     * @param other The money that is being subtracted from this Money.
     * @return A new Money with the same Currency as this Money, representing the subtracted value.
     * (Again, remember converting the value of the other Money to this Currency)
     */
    public Money sub(Money other) {
        if (other == null || !(other instanceof Money)) {
            // Handle invalid input
            return null;
        }

        // Cast the other object to Money
        Money otherMoney = (Money) other;

        // Check if the currencies are the same
        if (!this.currency.equals(otherMoney.currency)) {
            // Convert the otherMoney to the same currency
            int convertedAmount = (int) (otherMoney.amount * otherMoney.currency.getRate() / this.currency.getRate());

            // Perform the subtraction with the converted amount
            return new Money(this.amount - convertedAmount, this.currency);
        }

        // Currencies are the same, perform the subtraction directly
        return new Money(this.amount - otherMoney.amount, this.currency);

    }

    /**
     * Check to see if the amount of this Money is zero or not
     * @return True if the amount of this Money is equal to 0.0, False otherwise
     */
    public Boolean isZero() {
        return amount == 0;
    }
    /**
     * Negate the amount of money, i.e. if the amount is 10.0 SEK the negation returns -10.0 SEK
     * @return A new instance of the money class initialized with the new negated money amount.
     */
    public Money negate() {
        int negatedAmount = -amount;
        return new Money(negatedAmount, this.currency);
    }

    /**
     * Compare two Monies.
     * compareTo is required because the class implements the Comparable interface.
     * (Remember the universalValue method, and that Integers already implement Comparable).
     * Also, since compareTo must take an Object, you will have to explicitly downcast it to a Money.
     * @return 0 if the values of the monies are equal.
     * A negative integer if this Money is less valuable than the other Money.
     * A positive integer if this Money is more valuiable than the other Money.
     */
    public int compareTo(Object other) {
        // Check if the other object is null or not an instance of Money
        if (other == null || !(other instanceof Money)) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid argument type");
        }

        // Cast the other object to Money
        Money otherMoney = (Money) other;

        // Compare the universal values of the two Money objects
        return Integer.compare(this.universalValue(), otherMoney.universalValue());

    }
}