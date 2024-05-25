package org.example.fitnessstudiomanagement.Data;

import org.example.fitnessstudiomanagement.Enums.MembershipType;
import org.example.fitnessstudiomanagement.Model.Account;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class Database {
    private static Database database;
    private final HashSet<Account> accounts;
    private final String URL = "jdbc:sqlite:accounts.db";
    private Connection connection;


    private final String CREATE_TABLE_SQL = """
            CREATE TABLE IF NOT EXISTS accounts (
                id INTEGER PRIMARY KEY,
                name TEXT,
                address TEXT,
                telNumber TEXT,
                birthDate DATE,
                pass TEXT,
                checkinStat INTEGER,
                membershipType TEXT,
                expDate TEXT
            );""";


    private Database() {
        this.accounts = new HashSet<>();
        try{
            connect();
            createTable();
            getAllAccounts();
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void addAccount(Account account) {
        try {
            connect();
            insertAccount(account);
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.accounts.add(account);
    }

    public void removeAccount(Account account) {
        try {
            deleteAccount(account.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.accounts.remove(account);
    }

    public List<Account> getAccountsAsList() {
        return new ArrayList<>(accounts);
    }

    public Account findAccountByUsername(String username) {
        Optional<Account> optionalAccount = accounts.stream()
                .filter(account -> account.getName().equals(username))
                .findFirst();
        System.out.println(optionalAccount);
        return optionalAccount.orElse(null);
    }





    private void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connected to database");
        }
    }
    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Connection closed.");
        }
    }
    private void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE_SQL);
    }

    private void insertAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts (name, address, telNumber, birthDate, pass, checkinStat, membershipType, expDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, account.getName());
        preparedStatement.setString(2, account.getAddress());
        preparedStatement.setString(3, account.getTelNumber());
        preparedStatement.setDate(4, java.sql.Date.valueOf(account.getBirtDate()));
        preparedStatement.setString(5, account.getPass());
        preparedStatement.setInt(6, account.getCheckinNr());
        preparedStatement.setString(7, account.getMembership().name());
        preparedStatement.setString(8, account.getMembership().getExpirationDate().toString());
        preparedStatement.executeUpdate();
        System.out.println("Account inserted successfully.");
    }

    public void updateCheckinStat(String username, int newCheckinStat) throws SQLException {
        connect(); // Ensure connection is established
        String sql = "UPDATE accounts SET checkinStat = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, newCheckinStat);
            preparedStatement.setString(2, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("CheckinStat updated successfully for " + username);
            } else {
                System.out.println("No account found with username: " + username);
            }
        } finally {
            closeConnection(); // Close connection after operation
        }
    }

    public void updateMembership(String username, MembershipType membershipType) throws SQLException {
        connect();
        String sql = "UPDATE accounts SET membershipType = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, membershipType.name());
            preparedStatement.setString(2, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("MembershipType updated successfully for " + username);
            } else {
                System.out.println("No account found with username: " + username);
            }
        } finally {
            closeConnection();
        }


        connect();
        String sql2 = "UPDATE accounts SET expDate = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql2)) {
            preparedStatement.setString(1, LocalDate.now().toString());
            preparedStatement.setString(2, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("MembershipType updated successfully for " + username);
            } else {
                System.out.println("No account found with username: " + username);
            }
        } finally {
            closeConnection();
        }
    }


    private void getAllAccounts() {
        try {
            connect(); // Ensure connection is established

            String sql = "SELECT * FROM accounts";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    String telNumber = resultSet.getString("telNumber");
                    LocalDate birthDate = resultSet.getDate("birthDate").toLocalDate();
                    String pass = resultSet.getString("pass");
                    int checkinStat = resultSet.getInt("checkinStat");
                    String membershipType = resultSet.getString("membershipType");
                    MembershipType membershipTypeEnum = MembershipType.valueOf(membershipType);
                    String expDate = resultSet.getString("expDate");
                    System.out.println(expDate);
                    Account account = new Account(name, address, telNumber, birthDate, pass, membershipTypeEnum, expDate);
                    account.setCheckinNr(checkinStat);
                    this.accounts.add(account);
                }
            }
            System.out.println(this.accounts);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(); // Close connection after operation

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void deleteAccount(String username) throws SQLException {
        connect(); // Ensure connection is established
        String sql = "DELETE FROM accounts WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account deleted successfully: " + username);
            } else {
                System.out.println("No account found with username: " + username);
            }
        } finally {
            closeConnection(); // Close connection after operation
        }
    }


}
