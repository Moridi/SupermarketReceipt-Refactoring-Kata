package dojo.supermarket.model;

public class NForLessMOffer extends Offer{
    // Buy two toothbrushes, get one free.
    private int buyAmount;  // = 2
    private int freeAmount;  // = 1

    public NForLessMOffer(SpecialOfferType offerType, Product product, double argument, int buyAmount, int freeAmount) {
        super(offerType, product, argument);
        this.buyAmount = buyAmount;
        this.freeAmount = freeAmount;
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        int chunkSize = (buyAmount + freeAmount);  // N
        int mustPayAmount = (chunkSize - freeAmount); // M
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        int quantityAsInt = (int) productQuantity;
        if(quantityAsInt > 2) {
            double unitPrice = catalog.getUnitPrice(this.getProduct());

            int CompleteChunks = quantityAsInt / chunkSize;
            double originalPrice = productQuantity * unitPrice;
            double mustBuyPrice = CompleteChunks * mustPayAmount * unitPrice;
            double extraPrice = quantityAsInt % chunkSize * unitPrice;
            double discountAmount = originalPrice - (mustBuyPrice + extraPrice);
            String description = chunkSize + " for " + mustPayAmount;
            return new Discount(this.getProduct(), description, -discountAmount);
        }
        return null;
    }
}
