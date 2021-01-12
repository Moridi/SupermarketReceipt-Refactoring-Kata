package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnit;
import dojo.supermarket.model.receipt.Receipt;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;
import dojo.supermarket.model.supermarket.Teller;
import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BundleOfferTest {

    private SupermarketCatalog catalog;
    private Teller teller;
    private ShoppingCart theCart;
    private Product toothbrush;
    private Product toothpaste;


    @BeforeEach
    public void setUp() {
        catalog = new FakeCatalog();
        teller = new Teller(catalog);
        theCart = new ShoppingCart();

        toothpaste = new Product("toothpaste", ProductUnit.Each);
        catalog.addProduct(toothpaste, 0.99);
        toothbrush = new Product("toothbrush", ProductUnit.Each);
        catalog.addProduct(toothbrush, 0.99);
    }

    private void addItemToCart(Product product, int number) {
        for (int i = 0; i < number; i++) {
            theCart.addItem(product);
        }
    }

    private void fillCart(int numberOfPastes, int numberOfBrushes) {
        this.addItemToCart(toothpaste, numberOfPastes);
        this.addItemToCart(toothbrush, numberOfBrushes);
    }

    private void addNormalBundleOffer() {
        Map<Product, Integer> bundleProducts = new HashMap<>();
        bundleProducts.put(toothbrush, 1);
        bundleProducts.put(toothpaste, 1);
        teller.addBundleOffer(bundleProducts, 50.0);
    }


    @Test
    public void normalBundleOfferTest() {
        addNormalBundleOffer();
        this.fillCart(1, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Assertions.assertEquals(
                50.0 / 100.0 * (
                        catalog.getUnitPrice(toothbrush) +
                        catalog.getUnitPrice(toothpaste)),
                receipt.getTotalPrice(),
                1e-2
        );
    }

    @Test
    public void NonCompleteBundleOfferTest () {
        addNormalBundleOffer();
        this.fillCart(2, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Assertions.assertEquals(
                50.0 / 100.0 * (
                        catalog.getUnitPrice(toothbrush) +
                                catalog.getUnitPrice(toothpaste)) +
                        catalog.getUnitPrice(toothpaste),
                receipt.getTotalPrice(),
                1e-2
        );
    }

    @Test
    public void extendedBundleOfferTest() {
        addExtendedBundleOffer();
        this.fillCart(2, 1);
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Assertions.assertEquals(
                50.0 / 100.0 * (
                        catalog.getUnitPrice(toothbrush) +
                                2 * catalog.getUnitPrice(toothpaste)),
                receipt.getTotalPrice(),
                1e-2
        );
    }

    private void addExtendedBundleOffer() {
        Map<Product, Integer> bundleProducts = new HashMap<>();
        bundleProducts.put(toothbrush, 1);
        bundleProducts.put(toothpaste, 2);
        teller.addBundleOffer(bundleProducts, 50.0);
    }

    @Test
    public void multipleBundleOfferTest() {
        this.addMultipleBundleOffers();
        this.fillCart(4, 4);
        Receipt receipt = teller.checksOutArticlesFrom(this.theCart);
        Assertions.assertEquals(
                40.0 / 100.0 *
                        (
                                2 * catalog.getUnitPrice(toothbrush) +
                                3 * catalog.getUnitPrice(toothpaste)
                        ) +
                        2 * catalog.getUnitPrice(toothbrush) + catalog.getUnitPrice(toothpaste),
                receipt.getTotalPrice(),
                1e-2
        );
    }

    private void addMultipleBundleOffers() {
        Map<Product, Integer> bundleProducts1 = new HashMap<>();
        bundleProducts1.put(toothbrush, 2);
        bundleProducts1.put(toothpaste, 3);
        teller.addBundleOffer(bundleProducts1, 60.0);
    }

}
