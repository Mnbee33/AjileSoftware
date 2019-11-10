package casestudy.ClassificationTransactions;

import casestudy.Classifications.HourlyClassification;
import casestudy.Classifications.TimeCard;
import casestudy.PayrollDatabase.PayrollDatabase;
import casestudy.PayrollDomain.Employee;
import casestudy.PayrollDomain.PaymentClassification;
import casestudy.TransactionApplication.Transaction;

import java.time.LocalDate;

public class TimeCardTransaction implements Transaction {
    private LocalDate itsDate;
    private double itsHours;
    private int itsEmpId;

    public TimeCardTransaction(LocalDate date, double hours, int empId) {
        itsDate = date;
        itsHours = hours;
        itsEmpId = empId;
    }

    @Override
    public void execute() {
        Employee e = PayrollDatabase.getEmployee(itsEmpId);
        if (e != null) {
            PaymentClassification pc = e.getClassification();
            if (pc instanceof HourlyClassification) {
                HourlyClassification hc = (HourlyClassification) pc;
                hc.addTimeCard(new TimeCard(itsDate, itsHours));
            } else {
                throw new RuntimeException("Tried to add timecard to non-hourly employee.");
            }
        } else {
            throw new RuntimeException("No such employee.");
        }
    }
}
