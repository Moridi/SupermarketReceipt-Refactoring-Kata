package dojo.supermarket.model;

public class NPercentDiscountOffer extends Offer{
    public NPercentDiscountOffer(SpecialOfferType offerType, Product product, double argument) {
        super(offerType, product, argument);
    }

    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        double productQuantity = shoppingCart.getItemQuantity(this.getProduct());
        double unitPrice = catalog.getUnitPrice(this.getProduct());
        double discountAmount = productQuantity * unitPrice * this.argument / 100.0;
        String description = this.argument + "% off";
        return new Discount(this.getProduct(), description, -discountAmount);
    }
}
