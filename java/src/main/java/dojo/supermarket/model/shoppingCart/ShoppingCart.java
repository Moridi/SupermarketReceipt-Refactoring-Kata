package dojo.supermarket.model.shoppingCart;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantities;
import dojo.supermarket.model.product.ProductQuantity;

import java.util.*;

public class ShoppingCart {
    private final ProductQuantities productQuantities = new ProductQuantities();

    public List<ProductQuantity> getItems() {
        return productQuantities.getItems();
    }

    public void addItem(Product product) {
        productQuantities.add(product, 1.0);
    }

    public double getItemQuantity(Product product) {
        return productQuantities.getItemQuantities(product);
    }


    public void addItemQuantity(Product product, double quantity) {
        productQuantities.add(product, quantity);
    }
}