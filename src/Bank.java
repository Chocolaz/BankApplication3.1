import java.sql.*;

public class Bank {
    private String bankName;

    public Bank() {

    }
    public Bank(String name) {
        this.bankName = name;
    }
    public String getBankName(){
        return this.bankName;
    }
    public void listAccount(){
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                System.out.println(results.getString(1)+" "+results.getString(2)+ " "
                +results.getString(3) + " " +results.getString(4));
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void openAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "INSERT INTO account(accountID, accountName, accountBalance, accountType) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4, account.getAccountType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "delete from account where accountID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void depositMoney(Account account, double amount) {
        account.deposit(amount);
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accountBalance = ? where accountID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
        System.out.println(account.getBalance());
        Connection con = BankConnection.connect();
        String sql = "UPDATE account set accountBalance = ? where accountID = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        };
    }

    public Account getAccount(int accountNumber, String accountType) {
        Connection con = BankConnection.connect();
        String sql = "select * from account where accountID=? AND accountType=?";
        Account account = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, accountNumber);
            preparedStatement.setString(2, accountType);
            ResultSet result = preparedStatement.executeQuery();

            result.next();
                String accountName = result.getString(2);
                double balance = result.getDouble(3);

                if (accountType.equals("SavingsAccount")) {
                    account = new SavingAccount(accountNumber, accountName, balance);
                }
                else if(accountType.equals("CurrentAccount")) {
                    account = new CurrentAccount(accountNumber, accountName, balance);
                }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return account;
    }

}
