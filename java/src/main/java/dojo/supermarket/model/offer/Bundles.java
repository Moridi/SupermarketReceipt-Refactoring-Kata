package dojo.supermarket.model.offer;

import dojo.supermarket.model.product.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Bundles {
    private final Map<Product, Integer> bundleProducts;

    public Bundles() {
        bundleProducts = new HashMap<Product, Integer>();
    }

    public Bundles(Map<Product, Integer> bundleProducts) {
        this.bundleProducts = bundleProducts;
    }

    public void addBundle(Product product, int quantity) {
        this.bundleProducts.put(product, quantity);
    }

    public int getBundleItemCount(Product product) {
        return this.bundleProducts.get(product);
    }

    public Set<Map.Entry<Product, Integer>> getBundleProducts() {
        return bundleProducts.entrySet();
    }

}
