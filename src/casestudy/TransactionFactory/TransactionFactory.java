package casestudy.TransactionFactory;

import casestudy.TransactionFactoryImplementation.*;

import java.time.LocalDate;

public interface TransactionFactory {

    AddCommissionedEmployee makeAddCommissionedEmployee(int empId, String name, String address, double salary, double commissionRate);

    AddHourlyEmployee makeAddHourlyEmployee(int empId, String name, String address, double hourlyRate);

    AddSalariedEmployee makeAddSalariedEmployee(int empId, String name, String address, double salary);

    ChangeAddressTransaction makeChangeAddressTransaction(int empId, String address);

    ChangeCommissionedTransaction makeChangeCommissionedTransaction(int empId, double salary, double rate);

    ChangeDirectTransaction makeChangeDirectTransaction(int empId, String bank, long account);

    ChangeHoldTransaction makeChangeHoldTransaction(int empId);

    ChangeHourlyTransaction makeChangeHourlyTransaction(int empId, double rate);

    ChangeMailTransaction makeChangeMailTransaction(int empId, String address);

    ChangeMemberTransaction makeChangeMemberTransaction(int empId, int memberId, double dues);

    ChangeNameTransaction makeChangeNameTransaction(int empId, String name);

    ChangeSalariedTransaction makeChangeSalariedTransaction(int empId, double salary);

    ChangeUnaffiliatedTransaction makeChangeUnaffiliatedTransaction(int empId);

    DeleteEmployeeTransaction makeDeleteEmployeeTransaction(int empId);

    PaydayTransaction makePaydayTransaction(LocalDate payDate);

    SalesReceiptTransaction makeSalesReceiptTransaction(LocalDate date, double amount, int empId);

    ServiceChargeTransaction makeServiceChargeTransaction(int memberId, LocalDate date, double amount);

    TimeCardTransaction makeTimeCardTransaction(LocalDate date, double hours, int empId);
}
