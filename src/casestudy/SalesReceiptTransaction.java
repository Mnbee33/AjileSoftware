package casestudy;

public class SalesReceiptTransaction implements Transaction {
    private long itsDate;
    private double itsAmount;
    private int itsEmpId;

    public SalesReceiptTransaction(long date, double amount, int empId) {
        itsDate = date;
        itsAmount = amount;
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.getEmployee(itsEmpId);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof CommissionedClassification) {
                CommissionedClassification cc = (CommissionedClassification) pc;
                cc.addSaleReceipt(new SaleReceipt(itsDate, itsAmount));
            } else {
                throw new RuntimeException("Tried to add salereceipt to non-commissioned employee.");
            }
        } else {
            throw new RuntimeException("No such employee.");
        }
    }
}