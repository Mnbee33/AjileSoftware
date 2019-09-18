package design.factorymethod;

import design.factorymethod.framework.Factory;
import design.factorymethod.framework.Product;
import design.factorymethod.idcard.IDFactory;

public class Main {
    public static void main(String[] args) {
        Factory factory = new IDFactory();
        Product card1 = factory.create("結城浩");
        Product card2 = factory.create("とむら");
        Product card3 = factory.create("佐藤花子");

        card1.use();
        card2.use();
        card3.use();
    }
}
