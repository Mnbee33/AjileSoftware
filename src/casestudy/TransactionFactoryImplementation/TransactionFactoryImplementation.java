package casestudy.TransactionFactoryImplementation;

import casestudy.PayrollFactory.PayrollFactory;
import casestudy.TransactionApplication.Transaction;
import casestudy.TransactionFactory.TransactionFactory;

import java.time.LocalDate;

public class TransactionFactoryImplementation implements TransactionFactory {
    private PayrollFactory itsPayrollFactory;

    public TransactionFactoryImplementation(PayrollFactory pf) {
        itsPayrollFactory = pf;
    }

    @Override
    public Transaction makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate) {
        return new AddCommissionedEmployee(empId, name, address, salary, commissionRate, itsPayrollFactory);
    }

    @Override
    public Transaction makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate) {
        return new AddHourlyEmployee(empId, name, address, hourlyRate, itsPayrollFactory);
    }

    @Override
    public Transaction makeAddSalariedEmployee(int empId, String name, String address, double salary) {
        return new AddSalariedEmployee(empId, name, address, salary, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeAddressTransaction(int empId, String address) {
        return new ChangeAddressTransaction(empId, address, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeCommissionedTransaction(int empId, double salary, double rate) {
        return new ChangeCommissionedTransaction(empId, salary, rate, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeDirectTransaction(int empId, String bank, long account) {
        return new ChangeDirectTransaction(empId, bank, account, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeHoldTransaction(int empId) {
        return new ChangeHoldTransaction(empId, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeHourlyTransaction(int empId, double rate) {
        return new ChangeHourlyTransaction(empId, rate, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeMailTransaction(int empId, String address) {
        return new ChangeMailTransaction(empId, address, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeMemberTransaction(int empId, int memberId, double dues) {
        return new ChangeMemberTransaction(empId, memberId, dues, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeNameTransaction(int empId, String name) {
        return new ChangeNameTransaction(empId, name, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeSalariedTransaction(int empId, double salary) {
        return new ChangeSalariedTransaction(empId, salary, itsPayrollFactory);
    }

    @Override
    public Transaction makeChangeUnaffiliatedTransaction(int empId) {
        return new ChangeUnaffiliatedTransaction(empId, itsPayrollFactory);
    }

    @Override
    public Transaction makeDeleteEmployeeTransaction(int empId) {
        return new DeleteEmployeeTransaction(empId);
    }

    @Override
    public Transaction makePaydayTransaction(LocalDate payDate) {
        return new PaydayTransaction(payDate, itsPayrollFactory);
    }

    @Override
    public Transaction makeSalesReceiptTransaction(LocalDate date, double amount, int empId) {
        return new SalesReceiptTransaction(date, amount, empId);
    }

    @Override
    public Transaction makeServiceChargeTransaction(int memberId, LocalDate date, double amount) {
        return new ServiceChargeTransaction(memberId, date, amount);
    }

    @Override
    public Transaction makeTimeCardTransaction(LocalDate date, double hours, int empId) {
        return new TimeCardTransaction(date, hours, empId);
    }
}
