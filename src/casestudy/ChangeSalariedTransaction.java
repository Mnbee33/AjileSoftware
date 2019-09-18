package casestudy;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;

    public ChangeSalariedTransaction(int empId, double salary) {
        super(empId);
        itsSalary = salary;
    }

    @Override
    PaymentSchedule getSchedule() {
        return new MonthlySchedule();
    }

    @Override
    PaymentClassification getClassification() {
        return new SalariedClassification(itsSalary);
    }
}
