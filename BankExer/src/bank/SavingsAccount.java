package bank;

/** Savings account with an owner; supports deposit, withdraw, and freeze. */
public class SavingsAccount extends AbstractBankAccount {
    /** The name of the account owner. */
    private String accountOwner;

    /** Valid deposit amount. */
    private static final int DEPOSIT_VALID_AMOUNT = 1000;

    /** Zero deposit amount. */
    private static final int DEPOSIT_ZERO_AMOUNT = 0;

    /** Negative deposit amount. */
    private static final int DEPOSIT_NEGATIVE_AMOUNT = -500;

    /** Sufficient withdrawal amount. */
    private static final int WITHDRAW_SUFFICIENT_AMOUNT = 500;

    /** Insufficient withdrawal amount. */
    private static final int WITHDRAW_INSUFFICIENT_AMOUNT = 1500;

    /** Negative withdrawal amount. */
    private static final int WITHDRAW_NEGATIVE_AMOUNT = -100;

    /** Deposit when frozen. */
    private static final int DEPOSIT_WHEN_FROZEN = 200;

    /** Withdraw after unfreeze. */
    private static final int WITHDRAW_AFTER_UNFREEZE = 100;


    /**
     * Creates a new savings account for the given owner.
     * @param owner the name of the account owner
     */
    public SavingsAccount(final String owner) {
        super();
        this.accountOwner = owner;
    }

    /**
     * Returns the account owner's name.
     * @return the owner's name
     */
    public String getOwnerName() {
        return accountOwner;
    }

    /**
     * Runs demonstration test cases for SavingsAccount.
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        try {
            System.out.println("=== Test Cases ===\n");

            // Case 1
            System.out.println("Case #1: Create a savings account");
            SavingsAccount acc1 = new SavingsAccount("Juan Dela Cruz");
            System.out.println("Owner: " + acc1.getOwnerName());
            System.out.println();

            // Case 2
            System.out.println("Case #2: Deposit with valid amount");
            acc1.deposit(DEPOSIT_VALID_AMOUNT);
            System.out.println("Balance: " + acc1.getBalance());
            System.out.println();

            // Case 3
            System.out.println("Case #3: Deposit with zero amount");
            try {
                acc1.deposit(DEPOSIT_ZERO_AMOUNT);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 4
            System.out.println("Case #4: Deposit with negative amount");
            try {
                acc1.deposit(DEPOSIT_NEGATIVE_AMOUNT);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 5
            System.out.println("Case #5: Withdraw with sufficient funds");
            acc1.withdraw(WITHDRAW_SUFFICIENT_AMOUNT);
            System.out.println("Balance: " + acc1.getBalance());
            System.out.println();

            // Case 6
            System.out.println("Case #6: Withdraw with insufficient funds");
            try {
                acc1.withdraw(WITHDRAW_INSUFFICIENT_AMOUNT);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 7
            System.out.println("Case #7: Withdraw with negative amount");
            try {
                acc1.withdraw(WITHDRAW_NEGATIVE_AMOUNT);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 8
            System.out.println("Case #8: Deposit when frozen");
            acc1.freezeAccount();
            try {
                acc1.deposit(DEPOSIT_WHEN_FROZEN);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 9
            System.out.println("Case #9: Withdraw when frozen");
            try {
                acc1.withdraw(WITHDRAW_AFTER_UNFREEZE);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();

            // Case 10
            System.out.println("Case #10: Unfreeze account and withdraw");
            acc1.unfreezeAccount();
            acc1.withdraw(WITHDRAW_AFTER_UNFREEZE);
            System.out.println("Balance: " + acc1.getBalance());
            System.out.println();

            // Case 11
            System.out.println("Case #11: Check frozen status");
            System.out.println("Frozen? " + acc1.isFrozen());
            acc1.freezeAccount();
            System.out.println("Frozen? " + acc1.isFrozen());
            System.out.println();

            // Case 12
            System.out.println("Case #12: Multiple transactions");
            SavingsAccount acc2 = new SavingsAccount("Maria");
            acc2.deposit(DEPOSIT_VALID_AMOUNT); 
            acc2.withdraw(WITHDRAW_SUFFICIENT_AMOUNT);
            acc2.withdraw(WITHDRAW_AFTER_UNFREEZE);
            System.out.println("Maria's balance: " + acc2.getBalance());
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
