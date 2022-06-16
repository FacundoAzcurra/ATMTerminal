package com.solvd.atm;

import com.solvd.atm.DAO.IAccountsDAO;
import com.solvd.atm.DAO.impl.AccountsImpl;
import com.solvd.atm.bin.Account;
import com.solvd.atm.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        log.info("Welcome to CCA ATM terminal. Please enter your ID number :");

        int accountId = sc.nextInt();
        try (Connection conn = ConnectionPool.getInstance().getConnection()) {
            IAccountsDAO accountsDAO = new AccountsImpl();
            log.info(accountsDAO.getObject(accountId));
            if (conn != null) {
                log.info("Logging in...");
            } else {
                log.info("Connection error.");
                log.info("Closing connection...");
            }
        } catch (SQLException e) {
            log.error(e);
        } catch (ConnectException e) {
            log.error(e);
        }

        log.info("Select the desired operation");
        log.info("// 1. Withdraw. // 2.Deposit. // 3.Check Account. // 4.Pay bills. // 5.Exit. // 6. Create new bank Account //");

        int choice = sc.nextInt();
        switch (choice){
            case 1:
                log.info("Enter the amount to withdraw.");
                int withdraw = sc.nextInt();
                try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    double accountBalance = accountsDAO.getObject(accountId).getBalance();
                    String fullName1 = accountsDAO.getObject(accountId).getFullName();
                    int cardNumber = accountsDAO.getObject(accountId).getCardNumber();
                    Account account1 = new Account(accountId,accountBalance,fullName1,cardNumber);
                    if(accountBalance >= withdraw){
                        accountBalance = accountBalance - withdraw;
                        log.info("You have 10 seconds to collect the money.");
                        accountsDAO.update(new Account(accountId,accountBalance,fullName1,cardNumber));
                        log.info("Your new balance is: "+accountBalance);
                    }else{
                        log.info("Insufficient balance.");
                        log.info("No changes were made.");
                    }
                } catch (SQLException e) {
                    log.error(e);
                } catch (ConnectException e) {
                    log.error(e);
                }
                break;

            case 2:
                log.info("Enter the amount to deposit.");
                int deposit = sc.nextInt();
                try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    double accountBalance = accountsDAO.getObject(accountId).getBalance();
                    String fullName1 = accountsDAO.getObject(accountId).getFullName();
                    int cardNumber = accountsDAO.getObject(accountId).getCardNumber();
                    Account account1 = new Account(accountId,accountBalance,fullName1,cardNumber);

                    if(deposit >= 0){
                    accountBalance = accountBalance + deposit;
                    accountsDAO.update(new Account(accountId,accountBalance,fullName1,cardNumber));
                    log.info("Your new balance is: "+accountBalance);
                    }else{
                        log.info("Invalid operation.");
                    }
                } catch (SQLException e) {
                    log.error(e);
                } catch (ConnectException e) {
                    log.error(e);
                }
                break;
            case 3:

            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
}
}