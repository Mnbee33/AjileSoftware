package casestudy.TransactionFactoryImplementation;

import casestudy.TransactionFactory.TransactionFactory;

import java.time.LocalDate;

public class TransactionFactoryImplementation implements TransactionFactory {

    @Override
    public AddCommissionedEmployee makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate) {
        return new AddCommissionedEmployee(empId, name, address, salary, commissionRate);
    }

    @Override
    public AddHourlyEmployee makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
        return new AddHourlyEmployee(empId, name, address, hourlyRate);
    }

    @Override
    public AddSalariedEmployee makeAddSalariedEmployee(int empId, String name, String address, double salary) {
        return new AddSalariedEmployee(empId, name, address, salary);
    }

    @Override
    public ChangeAddressTransaction makeChangeAddressTransaction(int empId, String address) {
        return new ChangeAddressTransaction(empId, address);
    }

    @Override
    public ChangeCommissionedTransaction makeChangeCommissionedTransaction(int empId, double salary, double rate) {
        return new ChangeCommissionedTransaction(empId, salary, rate);
    }

    @Override
    public ChangeDirectTransaction makeChangeDirectTransaction(int empId, String bank, long account) {
        return new ChangeDirectTransaction(empId, bank, account);
    }

    @Override
    public ChangeHoldTransaction makeChangeHoldTransaction(int empId) {
        return new ChangeHoldTransaction(empId);
    }

    @Override
    public ChangeHourlyTransaction makeChangeHourlyTransaction(int empId, double rate) {
        return new ChangeHourlyTransaction(empId, rate);
    }

    @Override
    public ChangeMailTransaction makeChangeMailTransaction(int empId, String address) {
        return new ChangeMailTransaction(empId, address);
    }

    @Override
    public ChangeMemberTransaction makeChangeMemberTransaction(int empId, int memberId, double dues) {
        return new ChangeMemberTransaction(empId, memberId, dues);
    }

    @Override
    public ChangeNameTransaction makeChangeNameTransaction(int empId, String name) {
        return new ChangeNameTransaction(empId, name);
    }

    @Override
    public ChangeSalariedTransaction makeChangeSalariedTransaction(int empId, double salary) {
        return new ChangeSalariedTransaction(empId, salary);
    }

    @Override
    public ChangeUnaffiliatedTransaction makeChangeUnaffiliatedTransaction(int empId) {
        return new ChangeUnaffiliatedTransaction(empId);
    }

    @Override
    public DeleteEmployeeTransaction makeDeleteEmployeeTransaction(int empId) {
        return new DeleteEmployeeTransaction(empId);
    }

    @Override
    public PaydayTransaction makePaydayTransaction(LocalDate payDate) {
        return new PaydayTransaction(payDate);
    }

    @Override
    public SalesReceiptTransaction makeSalesReceiptTransaction(LocalDate date, double amount, int empId) {
        return new SalesReceiptTransaction(date, amount, empId);
    }

    @Override
    public ServiceChargeTransaction makeServiceChargeTransaction(int memberId, LocalDate date, double amount) {
        return new ServiceChargeTransaction(memberId, date, amount);
    }

    @Override
    public TimeCardTransaction makeTimeCardTransaction(LocalDate date, double hours, int empId) {
        return new TimeCardTransaction(date, hours, empId);
    }
}
