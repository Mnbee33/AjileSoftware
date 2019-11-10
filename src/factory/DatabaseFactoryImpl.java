package factory;

public class DatabaseFactoryImpl implements DatabaseFactory {
    @Override
    public Database createDatabase() {
        return new DatabaseImpl();
    }
}
