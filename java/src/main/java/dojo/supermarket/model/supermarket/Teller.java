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
// @Assign: Mohammad
public class Teller {

    // TODO: Check its usage. In case, make it singleton
    private final SupermarketCatalog catalog;
    private final OfferHandler offerHandler;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
        this.offerHandler = new OfferHandler();
    }

    // TODO: Move the offer creation to OfferHandler
    public void addSpecialOffer(SpecialOfferType offerType, Product product, double reductionParameter) {
//        this.offers.put(product, new Offer(offerType, product, price));
        offerHandler.addOffer(offerType, product, reductionParameter);
    }

    // TODO: Feature envy.
    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        receipt.fillOriginalItems(theCart, this.catalog);

        List<Discount> discounts = offerHandler.applyOffers(theCart, this.catalog);
        for(Discount discount: discounts){
            if (discount != null)
                receipt.addDiscount(discount);
        }

//        theCart.handleOffers(receipt, this.offers, this.catalog);

        return receipt;
    }

}
