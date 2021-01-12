package dojo.supermarket.model.offer;

import dojo.supermarket.model.*;
import dojo.supermarket.model.product.Product;

public class Offer {
    SpecialOfferType offerType;
    private final Product product;
    // TODO: Encapsulation
    double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        return null;
    }
}
