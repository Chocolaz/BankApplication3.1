public class CurrentAccount implements Account{
    private int accountNumber;
    private String accountName;
    private double balance;
    private double minimum;
    private String accountType = "CurrentAccount";

    public CurrentAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.minimum = 1000;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getMinimum() {
        return minimum;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    @Override
    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }
    @Override
    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
    @Override
    public String getAccountType() {
        return accountType;
    }
}
