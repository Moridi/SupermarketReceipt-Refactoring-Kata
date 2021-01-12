package dojo.supermarket.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductQuantities {
    private final List<ProductQuantity> productQuantityList = new ArrayList<>();

    public List<ProductQuantity> getItems() {
        return productQuantityList;
    }

    public void add(Product product, double quantity) {
        productQuantityList.add(new ProductQuantity(product, quantity));
    }

    public double getItemQuantities(Product product) {
        double amount = 0;
        for (ProductQuantity productQuantity: productQuantityList) {
            if (productQuantity.getProduct().equals(product)) {
                amount += productQuantity.getQuantity();
            }
        }
        return amount;
    }

    public boolean hasKey(Product product) {
        for (ProductQuantity productQuantity: productQuantityList) {
            if (productQuantity.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
}
