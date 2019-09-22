package casestudy;

public abstract class ChangeAffiliationTransaction extends ChangeEmployeeTransaction {
    public ChangeAffiliationTransaction(int empId) {
        super(empId);
    }

    @Override
    void change(Employee e) {
        recordMembership(e);
        e.setAffiliation(getAffiliation());
    }

    abstract void recordMembership(Employee e);

    abstract Affiliation getAffiliation();
}
