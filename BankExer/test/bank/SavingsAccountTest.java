package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/** JUnit tests for SavingsAccount operations and main method. */
class SavingsAccountTest {

    /** The SavingsAccount instance used in tests. */
    private SavingsAccount account;

    /** Valid amount for deposit tests. */
    private static final int VALID_DEPOSIT = 1000;

    /** Zero amount for deposit tests. */
    private static final int ZERO_DEPOSIT = 0;

    /** Negative amount for deposit tests. */
    private static final int NEGATIVE_DEPOSIT = -500;

    /** Amount that can be withdrawn successfully. */
    private static final int SUFFICIENT_WITHDRAW = 500;

    /** Amount larger than balance to trigger insufficient funds. */
    private static final int INSUFFICIENT_WITHDRAW = 1500;

    /** Negative amount for withdrawal tests. */
    private static final int NEGATIVE_WITHDRAW = -100;

    /** Deposit amount used when account is frozen. */
    private static final int DEPOSIT_WHEN_FROZEN = 200;

    /** Withdrawal amount after unfreezing the account. */
    private static final int WITHDRAW_AFTER_UNFREEZE = 100;

    /** Name of the account owner for tests. */
    private static final String OWNER_NAME = "Juan Dela Cruz";

    @BeforeEach
    void setUp() {
        account = new SavingsAccount(OWNER_NAME);
        //CHECKSTYLE WARNING IS PRESENT
    }

    @Test
    void testInitialBalance() {
        int expectedBalance = 0;
        System.out.println("Initial balance: " + account.getBalance());
        assertEquals(expectedBalance, account.getBalance());

        boolean frozen = false;
        assertEquals(frozen, account.isFrozen());

        String owner = OWNER_NAME;
        System.out.println("Owner: " + account.getOwnerName());
        assertEquals(owner, account.getOwnerName());
    }

    @Test
    void testValidDeposit() throws Exception {
        account.deposit(VALID_DEPOSIT); //CHECKSTYLE WARNING IS PRESENT
        int expectedBalance = VALID_DEPOSIT; //CHECKSTYLE WARNING IS PRESENT
        System.out.println("Balance after valid deposit: "
                           + account.getBalance());
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void testDepositZeroAmount() {
        Exception exception = assertThrows(
            Exception.class,
            () -> account.deposit(ZERO_DEPOSIT) //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Deposit zero amount exception: "
                           + exception.getMessage());
        String expectedMessage = "Deposit amount must be positive.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDepositNegativeAmount() {
        Exception exception = assertThrows(
            Exception.class,
            () -> account.deposit(NEGATIVE_DEPOSIT)
            //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Deposit negative amount exception: "
                           + exception.getMessage());
        String expectedMessage = "Deposit amount must be positive.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testWithdrawSufficientBalance() throws Exception {
        account.deposit(VALID_DEPOSIT); //CHECKSTYLE WARNING IS PRESENT
        account.withdraw(SUFFICIENT_WITHDRAW); //CHECKSTYLE WARNING IS PRESENT
        int expectedBalance = VALID_DEPOSIT - SUFFICIENT_WITHDRAW;
        //CHECKSTYLE WARNING IS PRESENT
        System.out.println("Balance after withdrawal: "
                           + account.getBalance());
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() throws Exception {
        account.deposit(VALID_DEPOSIT); //CHECKSTYLE WARNING IS PRESENT
        Exception exception = assertThrows(
            Exception.class,
            () -> account.withdraw(INSUFFICIENT_WITHDRAW)
            //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Withdraw insufficient balance exception: "
                           + exception.getMessage());
        String expectedMessage = "Insufficient balance.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(
            Exception.class,
            () -> account.withdraw(NEGATIVE_WITHDRAW)
            //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Withdraw negative amount exception: "
                           + exception.getMessage());
        String expectedMessage = "Withdrawn amount must be positive.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testDepositWhenFrozen() throws Exception {
        account.freezeAccount();
        Exception exception = assertThrows(
            Exception.class,
            () -> account.deposit(DEPOSIT_WHEN_FROZEN)
            //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Deposit when frozen exception: "
                           + exception.getMessage());
        String expectedMessage = "Account is frozen. Cannot deposit.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testWithdrawWhenFrozen() throws Exception {
        account.deposit(VALID_DEPOSIT); //CHECKSTYLE WARNING IS PRESENT
        account.freezeAccount();
        Exception exception = assertThrows(
            Exception.class,
            () -> account.withdraw(WITHDRAW_AFTER_UNFREEZE)
            //CHECKSTYLE WARNING IS PRESENT
        );
        System.out.println("Withdraw when frozen exception: "
                           + exception.getMessage());
        String expectedMessage = "Account is frozen. Cannot withdraw.";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testUnfreezeAndWithdraw() throws Exception {
        account.deposit(VALID_DEPOSIT); //CHECKSTYLE WARNING IS PRESENT
        account.freezeAccount();
        account.unfreezeAccount();
        account.withdraw(WITHDRAW_AFTER_UNFREEZE);
        //CHECKSTYLE WARNING IS PRESENT
        int expectedBalance = VALID_DEPOSIT - WITHDRAW_AFTER_UNFREEZE;
        //CHECKSTYLE WARNING IS PRESENT
        System.out.println("Balance after unfreeze & withdrawal: "
                           + account.getBalance());
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    void testFreezeStatus() {
        boolean frozen = false;
        System.out.println("Frozen status before: " + account.isFrozen());
        assertEquals(frozen, account.isFrozen());
        account.freezeAccount();
        frozen = true;
        System.out.println("Frozen status after: " + account.isFrozen());
        assertEquals(frozen, account.isFrozen());
    }

    @Test
    void testMain() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outContent);
        PrintStream originalOut = System.out;
        System.setOut(printStream);

        SavingsAccount.main(new String[0]);

        String output = outContent.toString();
        System.out.println("Main output:\n" + output);
        assertTrue(output.contains("=== Test Cases ==="));
        assertTrue(output.contains("Owner: " + OWNER_NAME));
        //CHECKSTYLE WARNING IS PRESENT
        assertTrue(output.contains("Balance: 1000"));

        System.setOut(originalOut);
    }
}
