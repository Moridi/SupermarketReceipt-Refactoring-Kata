package dojo.supermarket.model;

public class NForAmountOffer extends Offer {
    private int offerChunkSize;

    public NForAmountOffer(SpecialOfferType offerType, Product product, double argument, int offerChunkSize) {
        super(offerType, product, argument);
        this.offerChunkSize = offerChunkSize;
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        int quantityAsInt = (int) productQuantity;
        Discount discount = null;
        if (quantityAsInt >= offerChunkSize) {
            double unitPrice = catalog.getUnitPrice(this.getProduct());
            double originalPrice = unitPrice * productQuantity;
            double extraNoDiscountPrice = quantityAsInt % this.offerChunkSize * unitPrice;
            double completeChunksDiscountedPrice = this.argument * quantityAsInt / this.offerChunkSize;

            double discountTotal = originalPrice - (completeChunksDiscountedPrice + extraNoDiscountPrice);
            String description = this.offerChunkSize + " for " + this.argument;
            discount = new Discount(this.getProduct(), description, -discountTotal);
        }

        return discount;
    }

}
