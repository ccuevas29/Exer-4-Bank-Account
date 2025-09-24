package bank;
//CODE REVIEW BY KYLE
/** Abstract bank account with basic deposit, withdraw, and freeze logic. */
public abstract class AbstractBankAccount implements BankAccount {

    /** Current balance of the account. */
    private double balance;

    /** Flag indicating if the account is frozen. */
    private boolean isFrozen;

    /**
     * Creates a new bank account with balance = 0 and account not frozen.
     */
    public AbstractBankAccount() {
        this.balance = 0;
        this.isFrozen = false;
    }

    /**
     * Deposits money into the account.
     * @param amount the amount to deposit, must be positive
     * @throws Exception if frozen or deposit amount is invalid
     */
    @Override
    public void deposit(final double amount) throws Exception {
        if (isFrozen) {
            throw new Exception(
                "Account is frozen. Cannot deposit."
            );
        }
        if (amount <= 0) {
            throw new Exception(
                "Deposit amount must be positive."
            );
        }
        balance += amount;
        System.out.println("Deposited: Php " + amount);
    }

    /**
     * Withdraws money from the account.
     * @param amount must be positive and ≤ balance
     * @throws Exception if frozen, invalid, or insufficient balance
     */
    @Override
    public void withdraw(final double amount) throws Exception {
        if (isFrozen) {
            throw new Exception(
                "Account is frozen. Cannot withdraw."
            );
        }
        if (amount <= 0) {
            throw new Exception(
                "Withdrawn amount must be positive."
            );
        }
        if (amount > balance) {
            throw new Exception(
                "Insufficient balance."
            );
        }
        balance -= amount;
        System.out.println("Withdrawn: Php " + amount);
    }

    /**
     * Returns the current balance of the account.
     * @return the account balance
     */
    @Override
    public double getBalance() {
        return balance;
    }

    /**
     * Checks if the account is currently frozen.
     * @return true if frozen, false otherwise
     */
    @Override
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Freezes the account, preventing deposits and withdrawals.
     */
    public void freezeAccount() {
        isFrozen = true;
        System.out.println("Account has been frozen.");
    }

    /**
     * Unfreeze the account, allowing deposits and withdrawals.
     */
    public void unfreezeAccount() {
        isFrozen = false;
        System.out.println("Account has been unfrozen.");
    }

    /**
     * Protected helper method for subclasses to directly modify balance.
     * @param newBalance the new balance to set
     */
    protected void setBalance(final double newBalance) {
        this.balance = newBalance;
    }
}
