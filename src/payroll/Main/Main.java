package payroll.Main;

import payroll.PayApplication.PayrollApplication;
import payroll.PayrollDatabase.GlobalDatabase;
import payroll.PayrollDatabaseImplementation.PayrollDatabaseImplementation;
import payroll.PayrollDomain.Employee;

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
