package casestudy;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {

    public ChangeClassificationTransaction(int empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        e.setSchedule(getSchedule());
        e.setClassification(getClassification());
    }

    abstract PaymentSchedule getSchedule();

    abstract PaymentClassification getClassification();
}
