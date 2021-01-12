package dojo.supermarket.model.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductQuantities {
    private final Map<Product, Double> productQuantitiesMap = new HashMap<>();
    private final List<ProductQuantity>  productQuantityList = new ArrayList<>();

    public List<ProductQuantity> getItems() {
        return productQuantityList;
    }

    public void add(Product product, double quantity) {
        productQuantityList.add(new ProductQuantity(product, quantity));
        if (productQuantitiesMap.containsKey(product)) {
            productQuantitiesMap.put(product, productQuantitiesMap.get(product) + quantity);
        } else {
            productQuantitiesMap.put(product, quantity);
        }
    }

    public double getItemQuantities(Product product) {
        return productQuantitiesMap.get(product);
    }
}
