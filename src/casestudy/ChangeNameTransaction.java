package casestudy;

public class ChangeNameTransaction extends ChangeEmployeeTransaction {
    private String itsName;

    public ChangeNameTransaction(int empId, String name) {
        super(empId);
        itsName = name;
    }

    @Override
    void change(Employee e) {
        e.setName(itsName);
    }
}
