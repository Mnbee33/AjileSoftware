package design.factorymethod.idcard;

import design.factorymethod.framework.Factory;
import design.factorymethod.framework.Product;

import java.util.ArrayList;
import java.util.List;

public class IDFactory extends Factory {
    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product p) {
        owners.add(((IDCard) p).getOwner());
    }
}
