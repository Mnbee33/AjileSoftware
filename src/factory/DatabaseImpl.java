package factory;

public class DatabaseImpl implements Database {
    @Override
    public void update(String query) {
        System.out.println("update: " + query);
    }
}
