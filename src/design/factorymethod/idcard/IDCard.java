package design.factorymethod.idcard;

import design.factorymethod.framework.Product;

public class IDCard implements Product {
    private String owner;

    IDCard(String owner) {
        System.out.println(String.format("%sのカードを作ります。", owner));
        this.owner = owner;
    }

    String getOwner() {
        return owner;
    }

    @Override
    public void use() {
        System.out.println(String.format("%sのカードを使います。", owner));
    }
}
