package dojo.supermarket.model.supermarket;

import dojo.supermarket.model.product.Product;

public interface SupermarketCatalog {
    void addProduct(Product product, double price);

    double getUnitPrice(Product product);

}
