package dojo.supermarket.model;

import dojo.supermarket.model.offer.SpecialOfferType;
import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductUnit;
import dojo.supermarket.model.receipt.Receipt;
import dojo.supermarket.model.shoppingCart.ShoppingCart;
import dojo.supermarket.model.supermarket.SupermarketCatalog;
import dojo.supermarket.model.supermarket.Teller;
import org.junit.Assert;
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
    private Product rice;
    private Product apples;
    private Product cherryTomatoes;
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
        rice = new Product("rice", ProductUnit.Each);
        catalog.addProduct(rice, 2.99);
        apples = new Product("apples", ProductUnit.Kilo);
        catalog.addProduct(apples, 1.99);
        cherryTomatoes = new Product("cherry tomato box", ProductUnit.Each);
        catalog.addProduct(cherryTomatoes, 0.69);
    }

    private void fillCart() {
        theCart.addItem(toothbrush);
        theCart.addItem(toothbrush);
    }


    @Test
    public void normalBundleOfferTest() {
        Map<Product, Integer> bundleProducts = new HashMap<>();
        bundleProducts.put(toothbrush, 1);
        bundleProducts.put(toothpaste, 1);
        teller.addBundleOffer(bundleProducts, 50.0);
        this.fillCart();
        Receipt receipt = teller.checksOutArticlesFrom(theCart);
        Assertions.assertEquals(receipt.getTotalPrice(),
                50.0 / 100.0 * (catalog.getUnitPrice(toothbrush) + catalog.getUnitPrice(toothpaste)));
    }

}
