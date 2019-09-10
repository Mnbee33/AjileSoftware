package design.adapter.practice;

import java.io.IOException;

public class Main {
    private static final String DIR = "src/design/adapter/practice/";

    public static void main(String[] args) {
        FileIO f = new FileProperties();
        try {
            // FIXME ファイル名のみ→プロジェクト直下に配置
            f.readFromFile(DIR + "file.txt");
            System.out.println(f.getValue("year"));

            f.setValue("year", "2004");
            f.setValue("month", "4");
            f.setValue("day", "21");
            f.writeToFile(DIR + "newfile.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
