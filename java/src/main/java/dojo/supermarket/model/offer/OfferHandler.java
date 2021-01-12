package dojo.supermarket.model.offer;


import dojo.supermarket.model.*;
import dojo.supermarket.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class OfferHandler {
    List<Offer> offers = new ArrayList<>();

    public List<Discount> applyOffers(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        List<Discount> discounts = new ArrayList<>();
        for (Offer offer : offers) {
            Discount discount = offer.apply(shoppingCart, catalog);
            if (discount != null) {
                discounts.add(discount);
            }
        }
        return discounts;
    }

    public void addOffer(SpecialOfferType offerType, Product product, double argument) {
        if(offerType == SpecialOfferType.FiveForAmount){
            this.offers.add(new NForAmountOffer(offerType, product, argument, 5));
        } else if(offerType == SpecialOfferType.TwoForAmount){
            this.offers.add(new NForAmountOffer(offerType, product, argument, 2));
        } else if(offerType == SpecialOfferType.TenPercentDiscount){
            this.offers.add(new NPercentDiscountOffer(offerType, product, argument));
        } else if(offerType == SpecialOfferType.ThreeForTwo){
            this.offers.add(new NForLessMOffer(offerType, product, argument, 2, 1));
        }
    }
}