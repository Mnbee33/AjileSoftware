package casestudy;

public class ChangeCommissionedTransaction extends ChangeClassificationTransaction {
    private double itsSalary;
    private double itsRate;

    public ChangeCommissionedTransaction(int empId, double salary, double rate) {
        super(empId);
        itsSalary = salary;
        itsRate = rate;
    }

    @Override
    PaymentSchedule getSchedule() {
        return new BiweeklySchedule();
    }

    @Override
    PaymentClassification getClassification() {
        return new CommissionedClassification(itsSalary, itsRate);
    }
}
