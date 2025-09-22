package bank;

/** Basic bank account operations. */
public interface BankAccount {
    /** Deposit amount into the account.
     * @param amount the amount to deposit
     * @throws Exception if deposit is invalid
     */
    void deposit(double amount) throws Exception;

    /** Withdraw amount from the account.
     * @param amount the amount to withdraw
     * @throws Exception if withdrawal is invalid
     */
    void withdraw(double amount) throws Exception;

    /** Get current balance.
     * @return the current balance
     */
    double getBalance();

    /** Check if account is frozen.
     * @return true if the account is frozen, false otherwise
     */
    boolean isFrozen();
}
