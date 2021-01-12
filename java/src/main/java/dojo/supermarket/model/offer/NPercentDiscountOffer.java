package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

public class NPercentDiscountOffer extends Offer{
    public NPercentDiscountOffer(SpecialOfferType offerType, Product product, double price) {
        super(offerType, product, price);
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        double unitPrice = catalog.getUnitPrice(this.getProduct());
        double discountAmount = productQuantity * unitPrice * this.price / 100.0;
        String description = this.price + "% off";
        return new Discount(this.getProduct(), description, -discountAmount);
    }
}
