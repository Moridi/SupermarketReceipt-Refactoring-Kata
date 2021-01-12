package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

public class NForLessMOffer extends Offer{
    // Buy two toothbrushes, get one free.
    private final int buyAmount;  // = 2
    private final int freeAmount;  // = 1

    public NForLessMOffer(SpecialOfferType offerType, Product product,
                          double price, int buyAmount, int freeAmount) {
        super(offerType, product, price);
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
    }

    private Discount getDiscount(SupermarketCatalog catalog, double productQuantity, int quantityAsInt) {

        int chunkSize = (buyAmount + freeAmount);  // N
        int mustPayAmount = (chunkSize - freeAmount); // M

        double unitPrice = catalog.getUnitPrice(this.getProduct());
        int CompleteChunks = quantityAsInt / chunkSize;
        double originalPrice = productQuantity * unitPrice;
        double mustBuyPrice = CompleteChunks * mustPayAmount * unitPrice;
        double extraPrice = quantityAsInt % chunkSize * unitPrice;
        double discountAmount = originalPrice - (mustBuyPrice + extraPrice);
        String description = chunkSize + " for " + mustPayAmount;
        return new Discount(this.getProduct(), description, -discountAmount);
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        int quantityAsInt = (int) productQuantity;

        if(quantityAsInt > 2)
            return getDiscount(catalog, productQuantity, quantityAsInt);
        return null;
    }
}
