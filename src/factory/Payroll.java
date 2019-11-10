package factory;

public class Payroll {
    private DatabaseFactory databaseFactory;

    public void execute() {
        Database db = databaseFactory.createDatabase();
        db.update("From Payroll");
    }

    void setFactory(DatabaseFactory databaseFactory) {
        this.databaseFactory = databaseFactory;
    }
}
