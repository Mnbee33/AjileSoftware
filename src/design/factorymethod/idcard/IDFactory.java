package design.factorymethod.idcard;

import design.factorymethod.framework.Factory;
import design.factorymethod.framework.Product;

import java.util.HashMap;
import java.util.Map;

public class IDFactory extends Factory {
    private Map<Integer, IDCard> owners = new HashMap<>();
    private int lastSerialNo;

    @Override
    protected Product createProduct(String owner) {
        lastSerialNo++;
        return new IDCard(owner, lastSerialNo);
    }

    @Override
    protected void registerProduct(Product p) {
        IDCard card = (IDCard) p;
        owners.put(card.serialNo(), card);
    }
}
