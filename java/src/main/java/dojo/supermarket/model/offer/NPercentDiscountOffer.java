package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NPercentDiscountOffer extends Offer{
    public static final double MAX_PERCENTAGES = 100.0;
    public static final String DISCOUNT_POSTFIX = "% off";
    private final double discount;
    private final Product product;

    public NPercentDiscountOffer(SpecialOfferType offerType,
                                 Product product, double discount) {
        super(offerType);
        this.product = product;
        this.discount = discount;
    }

    public List<Discount> apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.product);
        double unitPrice = catalog.getUnitPrice(this.product);
        double discountAmount = productQuantity * unitPrice * this.discount / MAX_PERCENTAGES;
        String description = this.discount + DISCOUNT_POSTFIX;
        return Arrays.asList(new Discount(this.product, description, -discountAmount));
    }
}
