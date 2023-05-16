public class SavingAccount implements Account {
    private int AccountNumber;
    private String accountName;
    private double balance;
    private final String accountType = "SavingsAccount";

    public SavingAccount(int accountNumber, String accountName, double balance) {
        AccountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return AccountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public void deposit(double amount) {
        this.balance = this.balance - amount;
    }

    @Override
    public void withdraw(double amount) {
        this.balance = this.balance + amount;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public String getAccountType() {
        return this.accountType;
    }


}
