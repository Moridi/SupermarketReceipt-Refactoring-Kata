package dojo.supermarket.model.offer;

import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

public class Offer {
    private SpecialOfferType offerType;

    public Offer(SpecialOfferType offerType) {
        this.offerType = offerType;
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        return null;
    }
}
