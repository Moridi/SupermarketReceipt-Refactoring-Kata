package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.shoppingCart.Discount;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;

import java.util.Map;

public class BundleOffer extends Offer {

    private final Map<Product, Integer> bundleProducts;
    private final double reductionParameter;

    public BundleOffer(SpecialOfferType offerType,
                       Map<Product, Integer> bundleProducts,
                       double reductionParameter) {
        super(offerType);
        this.bundleProducts = bundleProducts;
        this.reductionParameter = reductionParameter;
    }

    @Override
    public Discount apply(ShoppingCart shoppingCart, SupermarketCatalog catalog) {
        return null;
    }
}
