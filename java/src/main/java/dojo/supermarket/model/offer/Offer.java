package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

public class Offer {
    SpecialOfferType offerType;
    private final Product product;
    // TODO: Encapsulation

    public Offer(SpecialOfferType offerType, Product product) {
        this.offerType = offerType;
        this.product = product;
    }

    Product getProduct() {
        return this.product;
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        return null;
    }
}
