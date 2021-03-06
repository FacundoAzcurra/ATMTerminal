package com.solvd.atm;

import com.solvd.atm.bin.Account;
import com.solvd.atm.bin.Employee;
import com.solvd.atm.bin.Services;
import com.solvd.atm.bin.User;
import com.solvd.atm.service.IAccountsService;
import com.solvd.atm.service.IUserService;
import com.solvd.atm.service.jdbc.AccountsServiceImpl;
import com.solvd.atm.service.jdbc.UserServiceImpl;
import com.solvd.atm.util.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.solvd.atm.service.Json.Json.jsonService;

public class App {
    private static final Logger log = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        log.info("Welcome to CCA ATM terminal:");
        while (true) {
            log.info("Select the desired operation");
            log.info("// 1. Withdraw. // 2.Deposit. // 3.Check Account. // 4.Pay bills. // 5. Create new bank Account // 6. Take shift to be attended by an employee // 7. Exit //");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                        log.info("Enter your id: ");
                        int accountId = sc.nextInt();
                        log.info("Enter the amount to withdraw.");
                        int withdraw = sc.nextInt();
                        IAccountsService accountsDAO = new AccountsServiceImpl();
                        double accountBalance = accountsDAO.getAccount(accountId).getBalance();
                        String fullName1 = accountsDAO.getAccount(accountId).getFullName();
                        int cardNumber = accountsDAO.getAccount(accountId).getCardNumber();
                        int userId = accountsDAO.getAccount(accountId).getUserId();
                        int bankId = accountsDAO.getAccount(accountId).getBankId();
                        Account account1 = new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId);
                        if (accountBalance >= withdraw) {
                            accountBalance = accountBalance - withdraw;
                            log.info("You have 10 seconds to collect the money.");
                            accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                            log.info("Your new balance is: " + accountBalance);
                        } else {
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
                        IAccountsService accountsDAO = new AccountsServiceImpl();
                        double accountBalance = accountsDAO.getAccount(accountId).getBalance();
                        String fullName1 = accountsDAO.getAccount(accountId).getFullName();
                        int cardNumber = accountsDAO.getAccount(accountId).getCardNumber();
                        int userId = accountsDAO.getAccount(accountId).getUserId();
                        int bankId = accountsDAO.getAccount(accountId).getBankId();
                        Account account1 = new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId);

                        if (deposit >= 0) {
                            accountBalance = accountBalance + deposit;
                            accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                            log.info("Your new balance is: " + accountBalance);
                        } else {
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
                        IAccountsService accountsDAO = new AccountsServiceImpl();
                        log.info("This is your current account status: ");
                        log.info(accountsDAO.getAccount(accountId).toString());
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ConnectException e) {
                        log.error(e);
                    }
                    break;
                case 4:
                    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                        log.info("Enter your id: ");
                        int accountId = sc.nextInt();
                        IAccountsService accountsDAO = new AccountsServiceImpl();
                        double accountBalance = accountsDAO.getAccount(accountId).getBalance();
                        String fullName1 = accountsDAO.getAccount(accountId).getFullName();
                        int cardNumber = accountsDAO.getAccount(accountId).getCardNumber();
                        int userId = accountsDAO.getAccount(accountId).getUserId();
                        int bankId = accountsDAO.getAccount(accountId).getBankId();
                        log.info("Select the bill you want to pay");
                        log.info("// 1. Rent bill // 2. Electric bill // 3. Gas bill // 4.Health Insurance bill //");
                        int bill = sc.nextInt();
                        switch (bill) {
                            case 1:
                                Random random = new Random();
                                int amount = random.nextInt(400);
                                Services rent = new Services(1, "Real Estate", amount, accountId);
                                log.info("The amount of the bill will be : " + rent.getAmount());
                                log.info("If you want to pay press '1', If not press '2'");
                                int paymentaccept = sc.nextInt();
                                if (paymentaccept == 1) {
                                    if (accountBalance >= amount) {
                                        accountBalance = accountBalance - amount;
                                        log.info(rent.toString());
                                        log.info("Your new balance is : " + accountBalance);
                                        accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                                    }
                                } else {
                                    log.info("Payment canceled.");
                                }
                                break;
                            case 2:
                                Random random1 = new Random();
                                int amount1 = random1.nextInt(400);
                                Services electricbill = new Services(2, "Edesur", amount1, accountId);
                                log.info("The amount of the bill will be : " + electricbill.getAmount());
                                log.info("If you want to pay press '1', If not press '2'");
                                int paymentaccept1 = sc.nextInt();
                                if (paymentaccept1 == 1) {
                                    if (accountBalance >= amount1) {
                                        accountBalance = accountBalance - amount1;
                                        log.info(electricbill.toString());
                                        log.info("Your new balance is : " + accountBalance);
                                        accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                                    }
                                } else {
                                    log.info("Payment canceled.");
                                }
                                break;
                            case 3:
                                Random random2 = new Random();
                                int amount2 = random2.nextInt(400);
                                Services gasbill = new Services(3, "Metrogas", amount2, accountId);
                                log.info("The amount of the bill will be : " + gasbill.getAmount());
                                log.info("If you want to pay press '1', If not press '2'");
                                int paymentaccept2 = sc.nextInt();
                                if (paymentaccept2 == 1) {
                                    if (accountBalance >= amount2) {
                                        accountBalance = accountBalance - amount2;
                                        log.info(gasbill.toString());
                                        log.info("Your new balance is : " + accountBalance);
                                        accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                                    }
                                } else {
                                    log.info("Payment canceled.");
                                }
                                break;
                            case 4:
                                Random random3 = new Random();
                                int amount3 = random3.nextInt(400);
                                Services health = new Services(1, "Galeno", amount3, accountId);
                                log.info("The amount of the bill will be : " + health.getAmount());
                                log.info("If you want to pay press '1', If not press '2'");
                                int paymentaccept3 = sc.nextInt();
                                if (paymentaccept3 == 1) {
                                    if (accountBalance >= amount3) {
                                        accountBalance = accountBalance - amount3;
                                        log.info(health.toString());
                                        log.info("Your new balance is : " + accountBalance);
                                        accountsDAO.update(new Account(accountId, accountBalance, fullName1, cardNumber, userId, bankId));
                                    }
                                } else {
                                    log.info("Payment canceled.");
                                }
                                break;
                            default:
                                throw new IllegalStateException("Not valid option.");
                        }
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ConnectException e) {
                        log.error(e);
                    }
                    break;
                case 5:
                    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
                        log.info("Creating new account process started...");
                        log.info("Enter your full name without spaces(firstNamelastName) : ");
                        String fullName = sc.next();
                        log.info("Enter your id: ");
                        int idAccounts = sc.nextInt();
                        log.info("Enter your desired username: ");
                        String username = sc.next();
                        log.info("Enter your desired password: ");
                        String pass = sc.next();
                        double accountBalance = 0;
                        Random random = new Random();
                        int cardNumber = random.nextInt(4000000);
                        User newUser = new User(idAccounts, username, pass);
                        IUserService userDAO = new UserServiceImpl();
                        userDAO.create(newUser);
                        IAccountsService accountsDAO = new AccountsServiceImpl();
                        int userId = idAccounts;
                        int bankId = 1;
                        accountsDAO.create(new Account(idAccounts, accountBalance, fullName, cardNumber, userId, bankId));
                        log.info(accountsDAO.getAccount(idAccounts).toString());
                    } catch (SQLException e) {
                        log.error(e);
                    } catch (ConnectException e) {
                        log.error(e);
                    }
                    break;
                case 6:
                    File file = new File("src/main/resources/json/employee.json");
                    List<Employee> employeeList = jsonService(file);
                    Random ran = new Random();
                    Employee randomEmployee = employeeList.get(ran.nextInt(employeeList.size()));
                    log.info("Wait in the office area, shortly you will be attended by " + randomEmployee.getFirstName());
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}