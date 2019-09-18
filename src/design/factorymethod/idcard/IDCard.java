package design.factorymethod.idcard;

import design.factorymethod.framework.Product;

public class IDCard implements Product {
    private String owner;
    private int serialNo;

    IDCard(String owner, int serialNo) {
        this.owner = owner;
        this.serialNo = serialNo;
        System.out.println(String.format("%sを作ります。", this));
    }

    String getOwner() {
        return owner;
    }

    int serialNo() {
        return serialNo;
    }

    @Override
    public void use() {
        System.out.println(String.format("%sを使います。", this));
    }

    @Override
    public String toString() {
        return String.format("No.%d %sのカード", serialNo, owner);
    }
}
