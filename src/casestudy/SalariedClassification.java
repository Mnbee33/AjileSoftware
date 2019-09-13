package casestudy;

public class SalariedClassification implements PaymentClassification {
    private double itsSalary;

    public SalariedClassification(double salary) {
        itsSalary = salary;
    }

    public double getSalary() {
        return itsSalary;
    }
}
