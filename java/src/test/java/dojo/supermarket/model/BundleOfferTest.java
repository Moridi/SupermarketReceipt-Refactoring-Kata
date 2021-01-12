package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnit;
import dojo.supermarket.model.receipt.Receipt;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;
import dojo.supermarket.model.supermarket.Teller;
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

    private void fillCart() {
        theCart.addItem(toothpaste);
        theCart.addItem(toothbrush);
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
        this.fillCart();
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Assertions.assertEquals(
                50.0 / 100.0 * (
                        catalog.getUnitPrice(toothbrush) +
                        catalog.getUnitPrice(toothpaste)),
                receipt.getTotalPrice(),
                1e-2
        );
    }

//    @Test
//    public void extendedBundleOfferTest () {
//
//    }

}
