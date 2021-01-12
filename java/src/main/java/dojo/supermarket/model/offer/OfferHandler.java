package dojo.supermarket.model.offer;


import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OfferHandler {
    List<Offer> offers = new ArrayList<>();

    public List<Discount> applyOffers(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        List<Discount> discounts = new ArrayList<>();
        for (Offer offer : offers) {
            List<Discount> offerDiscounts = offer.apply(shoppingCart, catalog);
            if (offerDiscounts != null) {
                discounts.addAll(offerDiscounts);
            }
        }
        return discounts;
    }

    public void addNormalOffer(SpecialOfferType offerType, Product product, double reductionParameter) {
        // TODO: Define these magic values
        if(offerType == SpecialOfferType.FiveForAmount){
            this.offers.add(new NForAmountOffer(offerType, product, reductionParameter, 5));
        } else if(offerType == SpecialOfferType.TwoForAmount){
            this.offers.add(new NForAmountOffer(offerType, product, reductionParameter, 2));
        } else if(offerType == SpecialOfferType.TenPercentDiscount){
            this.offers.add(new NPercentDiscountOffer(offerType, product, reductionParameter));
        } else if(offerType == SpecialOfferType.ThreeForTwo){
            this.offers.add(new NForLessMOffer(offerType, product,2, 1));
        }
    }

    public void addBundleOffer(Map<Product, Integer> bundleProducts, double discount) {
        this.offers.add(new BundleOffer(SpecialOfferType.BundleDiscount, bundleProducts, discount));
    }

}