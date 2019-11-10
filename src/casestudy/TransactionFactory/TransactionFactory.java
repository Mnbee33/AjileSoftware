package casestudy.TransactionFactory;

import casestudy.TransactionApplication.Transaction;

import java.time.LocalDate;

public interface TransactionFactory {

    Transaction makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate);

    Transaction makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate);

    Transaction makeAddSalariedEmployee(int empId, String name, String address, double salary);

    Transaction makeChangeAddressTransaction(int empId, String address);

    Transaction makeChangeHoldTransaction(int empId);

    Transaction makeChangeDirectTransaction(int empId, String bank, long account);

    Transaction makeChangeMailTransaction(int empId, String address);

    Transaction makeChangeNameTransaction(int empId, String name);

    Transaction makeChangeHourlyTransaction(int empId, double rate);

    Transaction makeChangeSalariedTransaction(int empId, double salary);

    Transaction makeChangeCommissionedTransaction(int empId, double salary, double rate);

    Transaction makeChangeMemberTransaction(int empId, int memberId, double dues);

    Transaction makeChangeUnaffiliatedTransaction(int empId);

    Transaction makeDeleteEmployeeTransaction(int empId);

    Transaction makePaydayTransaction(LocalDate payDate);

    Transaction makeSalesReceiptTransaction(LocalDate date, double amount, int empId);

    Transaction makeServiceChargeTransaction(int memberId, LocalDate date, double amount);

    Transaction makeTimeCardTransaction(LocalDate date, double hours, int empId);
}
