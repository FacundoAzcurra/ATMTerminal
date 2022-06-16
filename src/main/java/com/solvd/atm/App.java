package com.solvd.atm;

import com.solvd.atm.DAO.IAccountsDAO;
import com.solvd.atm.DAO.IUserDAO;
import com.solvd.atm.DAO.impl.AccountsImpl;
import com.solvd.atm.DAO.impl.UserImpl;
import com.solvd.atm.bin.Account;
import com.solvd.atm.bin.User;
import com.solvd.atm.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        log.info("Welcome to CCA ATM terminal:");

        log.info("Select the desired operation");
        log.info("// 1. Withdraw. // 2.Deposit. // 3.Check Account. // 4.Pay bills. // 5. Create new bank Account //");

        int choice = sc.nextInt();
        switch (choice){
            case 1:
                try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                    log.info("Enter your id: ");
                    int accountId = sc.nextInt();
                    log.info("Enter the amount to withdraw.");
                    int withdraw = sc.nextInt();
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    double accountBalance = accountsDAO.getObject(accountId).getBalance();
                    String fullName1 = accountsDAO.getObject(accountId).getFullName();
                    int cardNumber = accountsDAO.getObject(accountId).getCardNumber();
                    int userId = accountsDAO.getObject(accountId).getUserId();
                    int bankId = accountsDAO.getObject(accountId).getBankId();
                    Account account1 = new Account(accountId,accountBalance,fullName1,cardNumber,userId,bankId);
                    if(accountBalance >= withdraw){
                        accountBalance = accountBalance - withdraw;
                        log.info("You have 10 seconds to collect the money.");
                        accountsDAO.update(new Account(accountId,accountBalance,fullName1,cardNumber,userId,bankId));
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
                try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                    log.info("Enter your id: ");
                    int accountId = sc.nextInt();
                    log.info("Enter the amount to deposit.");
                    int deposit = sc.nextInt();
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    double accountBalance = accountsDAO.getObject(accountId).getBalance();
                    String fullName1 = accountsDAO.getObject(accountId).getFullName();
                    int cardNumber = accountsDAO.getObject(accountId).getCardNumber();
                    int userId = accountsDAO.getObject(accountId).getUserId();
                    int bankId = accountsDAO.getObject(accountId).getBankId();
                    Account account1 = new Account(accountId,accountBalance,fullName1,cardNumber,userId,bankId);

                    if(deposit >= 0){
                    accountBalance = accountBalance + deposit;
                    accountsDAO.update(new Account(accountId,accountBalance,fullName1,cardNumber,userId,bankId));
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
                try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                    log.info("Enter your id: ");
                    int accountId = sc.nextInt();
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    log.info("This is your current account status: ");
                    log.info(accountsDAO.getObject(accountId).toString());
                } catch (SQLException e) {
                    log.error(e);
                } catch (ConnectException e) {
                    log.error(e);
                }
                break;
            case 4:
                break;
            case 5:
                try(Connection conn = ConnectionPool.getInstance().getConnection()){
                    log.info("Creating new account process started...");
                    log.info("Enter your full name without spaces(firstNamelastName) : ");
                    String fullName = sc.next();
                    log.info("Enter your id: ");
                    int idAccounts = sc.nextInt();
                    log.info("Enter your desired username: ");
                    String user = sc.next();
                    log.info("Enter your desired password: ");
                    String pass = sc.next();
                    double accountBalance = 0;
                    Random random = new Random();
                    int cardNumber = random.nextInt(1000);
                    User newUser = new User(idAccounts,user,pass);
                    IUserDAO userDAO = new UserImpl();
                    userDAO.insert(newUser);
                    IAccountsDAO accountsDAO = new AccountsImpl();
                    int userId = idAccounts;
                    int bankId = random.nextInt(1-2);
                    Account newAccount = new Account(idAccounts,accountBalance,fullName,cardNumber,userId,bankId);

                    accountsDAO.insert(newAccount);
                    log.info(accountsDAO.getObject(idAccounts).toString());
                }catch (SQLException e){
                    log.error(e);
                }catch (ConnectException e){
                    log.error(e);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
}
}
