package design.adapter.practice;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileProperties implements FileIO {
    private Properties prop = new Properties();

    @Override
    public void readFromFile(String filename) throws IOException {
        prop.load(new FileReader(filename));
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        prop.store(new FileWriter(filename), "written by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        prop.setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return prop.getProperty(key);
    }
}
