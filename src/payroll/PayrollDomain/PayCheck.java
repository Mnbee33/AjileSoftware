package payroll.PayrollDomain;

import java.time.LocalDate;

public interface PayCheck {
    void setGrossPay(double grossPay);

    void setDeductions(double deductions);

    void netPay(double netPay);

    double getGrossPay();

    double getDeductions();

    double getNetPay();

    LocalDate getPayPeriodEndDate();

    String getField(String str);

    LocalDate getPayPeriodStartDate();
}
