package dojo.supermarket.model;


import java.util.ArrayList;
import java.util.List;

public class OfferHandler {
    List<Offer> offers;

    public List<Discount> applyOffers(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        List<Discount> discounts = new ArrayList<>();
        for (Offer offer : offers) {
//            Discount discount = offer.apply(shoppingCart, catalog);
//            if (discount != null) {
//                discounts.add(discount);
//            }
        }
        return discounts;
    }

}