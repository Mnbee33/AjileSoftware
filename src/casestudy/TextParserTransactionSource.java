package casestudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextParserTransactionSource implements TransactionSource {
    private BufferedReader reader;

    public TextParserTransactionSource(String path) {
        try {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Transaction getTransaction() {
        try {
            String commandLine = reader.readLine();
            String[] commands = commandLine.split(" ");
            return parse(commands);
        } catch (
                IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Transaction parse(String[] commands) {
        String transaction = commands[0];
        switch (transaction) {
            case "AddEmp":
                return addEmp(commands);
            default:
                return null;
        }
    }

    private Transaction addEmp(String[] commands) {
        int empId = Integer.parseInt(commands[1]);
        String name = commands[2];
        String address = commands[3];
        String type = commands[4];

        switch (type) {
            case "H":
                double hourlyRate = Double.parseDouble(commands[5]);
                return new AddHourlyEmployee(empId, name, address, hourlyRate);
            default:
                return null;
        }
    }
}
