package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BundleOffer extends Offer {

    private final Bundles bundleProducts;
    private final double discount;

    public BundleOffer(SpecialOfferType offerType,
                       Map<Product, Integer> bundleProducts,
                       double discount) {
        super(offerType);
        this.bundleProducts = new Bundles(bundleProducts);
        this.discount = discount;
    }

    private boolean bundleItemExists(Map.Entry<Product, Integer> bundleItem,
                                     ShoppingCart shoppingCart) {
        if (shoppingCart.hasProduct(bundleItem.getKey())) {
            int productQuantity = shoppingCart.getIntegerItemQuantity(bundleItem.getKey());
            if (bundleItem.getValue() <= productQuantity) {
                return true;
            }
        }
        return false;
    }

    private int getCompleteBundles(Bundles existedBundles) {
        int completeBundles = Integer.MAX_VALUE;

        for (Map.Entry<Product, Integer> bundleItem : existedBundles.getBundleProducts()) {
            int completeBundlesPerItem = bundleItem.getValue() /
                    this.bundleProducts.getBundleItemCount(bundleItem.getKey());
            if (completeBundlesPerItem < completeBundles)
                completeBundles = completeBundlesPerItem;
        }
        return completeBundles;
    }

    private List<Discount> getOfferDiscounts(Bundles existedBundles,
                                             SupermarketCatalog catalog) {
        int completeBundles = getCompleteBundles(existedBundles);
        List<Discount> offerDiscounts = new ArrayList<Discount>();

        for (Map.Entry<Product, Integer> bundleItem : existedBundles.getBundleProducts()) {
            double unitPrice = catalog.getUnitPrice(bundleItem.getKey());

            int numOfProductInTheCompleteBundles =
                    this.bundleProducts.getBundleItemCount(bundleItem.getKey()) * completeBundles;
            double discountAmount = numOfProductInTheCompleteBundles * unitPrice * this.discount / 100.0;

            String description = discountAmount + " for a bundle offer";
            offerDiscounts.add(new Discount(bundleItem.getKey(), description, -discountAmount));
        }

        return offerDiscounts;
    }

    @Override
    public List<Discount> apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        Bundles existedBundles = new Bundles();

        for (Map.Entry<Product, Integer> bundleItem : this.bundleProducts.getBundleProducts()) {
            if (!bundleItemExists(bundleItem, shoppingCart))
                return null;

            int productQuantity = shoppingCart.getIntegerItemQuantity(bundleItem.getKey());
            existedBundles.addBundle(bundleItem.getKey(), productQuantity);
        }
        return getOfferDiscounts(existedBundles, catalog);
    }
}
