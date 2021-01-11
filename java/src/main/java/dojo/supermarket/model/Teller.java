package dojo.supermarket.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// @Assign: Mohammad
public class Teller {

    // TODO: Check its usage. In case, make it singleton
    private final SupermarketCatalog catalog;
    private OfferHandler offerHandler;
    private Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
        this.offerHandler = new OfferHandler();
    }

    // TODO: Move the offer creation to OfferHandler
    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, new Offer(offerType, product, argument));
//        OfferHandler.addOffer(offerType, product, argument)
    }

    // TODO: Feature envy.
    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        receipt.fillOriginalItems(theCart, this.catalog);

//        OfferHandler.applyOffers(theCart, this.catalog);

        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }

}
