package dojo.supermarket.model.supermarket;

import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.offer.Offer;
import dojo.supermarket.model.offer.OfferHandler;
import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.receipt.Receipt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private final OfferHandler offerHandler;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
        this.offerHandler = new OfferHandler();
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double reductionParameter) {
        offerHandler.addNormalOffer(offerType, product, reductionParameter);
    }

    public void addBundleOffer(Map<Product, Integer> bundleProducts, double reductionParameter) {
        offerHandler.addBundleOffer(bundleProducts, reductionParameter);
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        receipt.fillOriginalItems(theCart, this.catalog);

        List<Discount> discounts = offerHandler.applyOffers(theCart, this.catalog);
        receipt.fillDiscountItems(discounts);

        return receipt;
    }
}
