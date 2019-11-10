package casestudy.Main;

import casestudy.PayApplication.PayrollApplication;
import casestudy.PayrollDatabase.GlobalDatabase;
import casestudy.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import casestudy.PayrollDomain.Employee;

public class Main {
    private static final String PATH = "src/casestudy/src/transaction.txt";

    public static void main(String[] args) {
        GlobalDatabase.database = new PayrollDatabaseImplementation();

        PayrollApplication payrollApplication = new PayrollApplication();
        payrollApplication.setSource(PATH);
        payrollApplication.execute();

        Employee e = GlobalDatabase.database.getEmployee(1);
        System.out.println(e.getName());
    }
}
