package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.ArrayList;
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

    public Product getProduct() {
        return this.product;
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

    public List<Discount> apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        // TODO: Check the following code validity in case of
        //  shoppingCart does not have this.getProduct() in its container
        //  Use shoppingCart.hasKey(this.getProduct()) for checking purposes.
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        int quantityAsInt = (int) productQuantity;

        if(quantityAsInt > 2)
            return new ArrayList<Discount>() {{add(getDiscount(catalog, productQuantity, quantityAsInt));}};
        return null;
    }
}
