package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.Arrays;
import java.util.List;

public class NForLessMOffer extends Offer{
    // Buy two toothbrushes, get one free.
    private final int buyAmount;  // = 2
    private final int freeAmount;  // = 1
    private final Product product;

    public NForLessMOffer(SpecialOfferType offerType, Product product, int buyAmount, int freeAmount) {
        super(offerType);
        this.product = product;
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
    }

    private Discount getDiscount(SupermarketCatalog catalog, double productQuantity, int quantityAsInt) {
        int chunkSize = (buyAmount + freeAmount);  // N
        int mustPayAmount = (chunkSize - freeAmount); // M

        double unitPrice = catalog.getUnitPrice(this.product);
        int CompleteChunks = quantityAsInt / chunkSize;
        double originalPrice = productQuantity * unitPrice;
        double mustBuyPrice = CompleteChunks * mustPayAmount * unitPrice;
        double extraPrice = quantityAsInt % chunkSize * unitPrice;
        double discountAmount = originalPrice - (mustBuyPrice + extraPrice);
        String description = chunkSize + " for " + mustPayAmount;
        return new Discount(this.product, description, -discountAmount);
    }

    public List<Discount> apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.product);
        int quantityAsInt = (int) productQuantity;

        if(quantityAsInt > buyAmount)
            return Arrays.asList(getDiscount(catalog, productQuantity, quantityAsInt));
        return null;
    }
}
