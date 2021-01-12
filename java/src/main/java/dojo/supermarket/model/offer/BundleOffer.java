package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Test this class
public class BundleOffer extends Offer {

    private final Map<Product, Integer> bundleProducts;
    private final double discount;

    public BundleOffer(SpecialOfferType offerType,
                       Map<Product, Integer> bundleProducts,
                       double discount) {
        super(offerType);
        this.bundleProducts = bundleProducts;
        this.discount = discount;
    }

    private boolean bundleItemExists(Map<Product, Integer> offerableCartProducts,
                                     Map.Entry<Product, Integer> bundleItem,
                                     ShoppingCart shoppingCart) {
        if (shoppingCart.hasProduct(bundleItem.getKey())) {
            int productQuantity = shoppingCart.getIntegerItemQuantity(bundleItem.getKey());
            if (bundleItem.getValue() <= productQuantity) {
                offerableCartProducts.put(bundleItem.getKey(), productQuantity);
                return true;
            }
        }
        return false;
    }

    private int getCompleteBundles(Map<Product, Integer> offerableCartProducts) {
        int completeBundles = Integer.MAX_VALUE;

        for (Map.Entry<Product, Integer> bundleItem : offerableCartProducts.entrySet()) {
            int completeBundlesPerItem = bundleItem.getValue() / this.bundleProducts.get(bundleItem.getKey());
            if (completeBundlesPerItem < completeBundles)
                completeBundles = completeBundlesPerItem;
        }
        return completeBundles;
    }

    private List<Discount> getOfferDiscounts(Map<Product, Integer> offerableCartProducts,
                                             SupermarketCatalog catalog) {
        int completeBundles = getCompleteBundles(offerableCartProducts);
        List<Discount> offerDiscounts = new ArrayList<Discount>();

        for (Map.Entry<Product, Integer> bundleItem : offerableCartProducts.entrySet()) {
            double unitPrice = catalog.getUnitPrice(bundleItem.getKey());

            int numOfProductInTheCompleteBundles = bundleProducts.get(bundleItem.getKey()) * completeBundles;
            double discountAmount = numOfProductInTheCompleteBundles * unitPrice * this.discount / 100.0;

            String description = discountAmount + " for a bundle offer";
            offerDiscounts.add(new Discount(bundleItem.getKey(), description, -discountAmount));
        }

        return offerDiscounts;
    }

    @Override
    public List<Discount> apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        Map<Product, Integer> offerableCartProducts = new HashMap<Product, Integer>();

        for (Map.Entry<Product, Integer> bundleItem : bundleProducts.entrySet()) {
            if (!bundleItemExists(offerableCartProducts, bundleItem, shoppingCart))
                return null;
        }
        return getOfferDiscounts(offerableCartProducts, catalog);
    }
}
