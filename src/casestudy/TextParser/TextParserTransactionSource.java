package casestudy.TextParser;

import casestudy.TransactionApplication.Transaction;
import casestudy.TransactionApplication.TransactionSource;
import casestudy.TransactionFactory.TransactionFactory;
import casestudy.TransactionFactoryImplementation.PaydayTransaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TextParserTransactionSource implements TransactionSource {
    private String path;
    private TransactionFactory factory;

    public TextParserTransactionSource(String path, TransactionFactory factory) {
        this.path = path;
        this.factory = factory;
    }

    @Override
    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String commandLine = reader.readLine();
            while (commandLine != null) {
                String[] commands = commandLine.split(" ");
                transactions.add(parse(commands));
                commandLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return transactions;
    }

    private Transaction parse(String[] commands) {
        String transaction = commands[0];
        switch (transaction) {
            case "AddEmp":
                return addEmp(commands);
            case "DelEmp":
                return delEmp(commands);
            case "TimeCard":
                return timeCard(commands);
            case "SalesReceipt":
                return salesReceipt(commands);
            case "ServiceCharge":
                return serviceCharge(commands);
            case "ChgEmp":
                return chgEmp(commands);
            case "Payday":
                return payDay(commands);
            default:
                return null;
        }
    }

    private Transaction addEmp(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        String name = commands[2];
        String address = commands[3];
        String type = commands[4];
        double salary = Double.parseDouble(commands[5]);

        switch (type) {
            case "H":
                return factory.makeAddHourlyEmployee(empId, name, address, salary);
            case "S":
                return factory.makeAddSalariedEmployee(empId, name, address, salary);
            case "C":
                double commissionRate = Double.parseDouble(commands[6]);
                return factory.makeAddCommissionedEmployee(empId, name, address, salary, commissionRate);
            default:
                return null;
        }
    }

    private Transaction delEmp(String[] command) {
        int empId = Integer.parseInt(command[1]);
        return factory.makeDeleteEmployeeTransaction(empId);
    }

    private Transaction timeCard(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double hour = Double.parseDouble(commands[3]);
        return factory.makeTimeCardTransaction(date, hour, empId);
    }

    private Transaction salesReceipt(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double amount = Double.parseDouble(commands[3]);
        return factory.makeSalesReceiptTransaction(date, amount, empId);
    }

    private Transaction serviceCharge(String[] commands) {
        int memberId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double amount = Double.parseDouble(commands[3]);
        return factory.makeServiceChargeTransaction(memberId, date, amount);
    }

    private Transaction chgEmp(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        String type = commands[2];
        switch (type) {
            case "Name":
                String name = commands[3];
                return factory.makeChangeNameTransaction(empId, name);
            case "Address":
                String address = commands[3];
                return factory.makeChangeAddressTransaction(empId, address);
            case "Hourly":
                double hourlyRate = Double.parseDouble(commands[3]);
                return factory.makeChangeHourlyTransaction(empId, hourlyRate);
            case "Salaried":
                double salary = Double.parseDouble(commands[3]);
                return factory.makeChangeSalariedTransaction(empId, salary);
            case "Commissioned":
                double commissionedSalary = Double.parseDouble(commands[3]);
                double commissionedRate = Double.parseDouble(commands[4]);
                return factory.makeChangeCommissionedTransaction(empId, commissionedSalary, commissionedRate);
            case "Hold":
                return factory.makeChangeHoldTransaction(empId);
            case "Direct":
                String bank = commands[3];
                long account = Long.parseLong(commands[4]);
                return factory.makeChangeDirectTransaction(empId, bank, account);
            case "Mail":
                String mailAddress = commands[3];
                return factory.makeChangeMailTransaction(empId, mailAddress);
            case "Member":
                int memberId = Integer.parseInt(commands[3]);
                double dues = Double.parseDouble(commands[5]);
                return factory.makeChangeMemberTransaction(empId, memberId, dues);
            case "NoMember":
                return factory.makeChangeUnaffiliatedTransaction(empId);
            default:
                return null;
        }
    }

    private PaydayTransaction payDay(String[] commands) {
        LocalDate payDate = LocalDate.parse(commands[1]);
        return factory.makePaydayTransaction(payDate);
    }
}
