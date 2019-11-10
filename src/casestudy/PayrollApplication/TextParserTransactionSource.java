package casestudy.PayrollApplication;

import casestudy.Transactions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TextParserTransactionSource implements TransactionSource {
    private String path;

    public TextParserTransactionSource(String path) {
        this.path = path;
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
                return new AddHourlyEmployee(empId, name, address, salary);
            case "S":
                return new AddSalariedEmployee(empId, name, address, salary);
            case "C":
                double commissionRate = Double.parseDouble(commands[6]);
                return new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
            default:
                return null;
        }
    }

    private Transaction delEmp(String[] command) {
        int empId = Integer.parseInt(command[1]);
        return new DeleteEmployeeTransaction(empId);
    }

    private Transaction timeCard(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double hour = Double.parseDouble(commands[3]);
        return new TimeCardTransaction(date, hour, empId);
    }

    private Transaction salesReceipt(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double amount = Double.parseDouble(commands[3]);
        return new SalesReceiptTransaction(date, amount, empId);
    }

    private Transaction serviceCharge(String[] commands) {
        int memberId = Integer.parseInt(commands[1]);
        LocalDate date = LocalDate.parse(commands[2]);
        double amount = Double.parseDouble(commands[3]);
        return new ServiceChargeTransaction(memberId, date, amount);
    }

    private Transaction chgEmp(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        String type = commands[2];
        switch (type) {
            case "Name":
                String name = commands[3];
                return new ChangeNameTransaction(empId, name);
            case "Address":
                String address = commands[3];
                return new ChangeAddressTransaction(empId, address);
            case "Hourly":
                double hourlyRate = Double.parseDouble(commands[3]);
                return new ChangeHourlyTransaction(empId, hourlyRate);
            case "Salaried":
                double salary = Double.parseDouble(commands[3]);
                return new ChangeSalariedTransaction(empId, salary);
            case "Commissioned":
                double commissionedSalary = Double.parseDouble(commands[3]);
                double commissionedRate = Double.parseDouble(commands[4]);
                return new ChangeCommissionedTransaction(empId, commissionedSalary, commissionedRate);
            case "Hold":
                return new ChangeHoldTransaction(empId);
            case "Direct":
                String bank = commands[3];
                long account = Long.parseLong(commands[4]);
                return new ChangeDirectTransaction(empId, bank, account);
            case "Mail":
                String mailAddress = commands[3];
                return new ChangeMailTransaction(empId, mailAddress);
            case "Member":
                int memberId = Integer.parseInt(commands[3]);
                double dues = Double.parseDouble(commands[5]);
                return new ChangeMemberTransaction(empId, memberId, dues);
            case "NoMember":
                return new ChangeUnaffiliatedTransaction(empId);
            default:
                return null;
        }
    }

    private Transaction payDay(String[] commands) {
        LocalDate payDate = LocalDate.parse(commands[1]);
        return new PaydayTransaction(payDate);
    }
}
