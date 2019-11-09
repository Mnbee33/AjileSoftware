package casestudy;

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
}
