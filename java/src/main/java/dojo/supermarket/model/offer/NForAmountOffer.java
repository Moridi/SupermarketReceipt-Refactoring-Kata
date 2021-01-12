package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

public class NForAmountOffer extends Offer {
    private final int offerChunkSize;
    private final double price;
    private final Product product;

    public NForAmountOffer(SpecialOfferType offerType, Product product,
                           double price, int offerChunkSize) {
        super(offerType);
        this.product = product;
        this.price = price;
        this.offerChunkSize = offerChunkSize;
    }

    public Product getProduct() {
        return this.product;
    }

    private Discount getDiscount(SupermarketCatalog catalog,
                                 double productQuantity, int quantityAsInt) {
        double unitPrice = catalog.getUnitPrice(this.getProduct());
        double originalPrice = unitPrice * productQuantity;
        double extraNoDiscountPrice = quantityAsInt % this.offerChunkSize * unitPrice;
        double completeChunksDiscountedPrice = this.price *
                Math.floor(quantityAsInt / this.offerChunkSize);

        double discountTotal = originalPrice - (completeChunksDiscountedPrice + extraNoDiscountPrice);
        String description = this.offerChunkSize + " for " + this.price;
        return new Discount(this.getProduct(), description, -discountTotal);
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        int quantityAsInt = (int) productQuantity;
        if (quantityAsInt >= offerChunkSize)
            return getDiscount(catalog, productQuantity, quantityAsInt);
        return null;
    }
}
