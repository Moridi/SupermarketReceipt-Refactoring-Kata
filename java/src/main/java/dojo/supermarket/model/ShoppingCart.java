package dojo.supermarket.model;

import dojo.supermarket.model.product.Product;
import dojo.supermarket.model.product.ProductQuantities;
import dojo.supermarket.model.product.ProductQuantity;

import java.util.*;


// @Assign: Zhivar
public class ShoppingCart {

    // TODO: Redundancy
    // TODO: Remove it.
//    private final List<ProductQuantity> items = new ArrayList<>();
    // TODO: Make it primitive type
    // TODO: Move it to a new class
//    Map<Product, double> productQuantities = new HashMap<>();
    private final ProductQuantities productQuantities = new ProductQuantities();


    public List<ProductQuantity> getItems() {
        return productQuantities.getItems();
    }

    public void addItem(Product product) {
        productQuantities.add(product, 1.0);
    }

//    @Note: unused method
//    Map<Product, double> productQuantities() {
//        return productQuantities;
//    }

    public double getItemQuantity(Product product) {
        return productQuantities.getItemQuantities(product);
    }


    public void addItemQuantity(Product product, double quantity) {
        productQuantities.add(product, quantity);
    }
}

// Assign: Refactoring
// TODO: Long method.
// TODO: Check the discount priorities.
// TODO: Encapsulation
//    void handleOffers(Map<Product, Offer> offers) {

//    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
//        for (Product p: productQuantities().keySet()) {
//            double quantity = productQuantities.get(p);
//            if (offers.containsKey(p)) {
//                Offer offer = offers.get(p);
//                double unitPrice = catalog.getUnitPrice(p);
//                int quantityAsInt = (int) quantity;
//                Discount discount = null;
//                int x = 1;
//                if (offer.offerType == SpecialOfferType.ThreeForTwo) {
//                    x = 3;
//
//                } else if (offer.offerType == SpecialOfferType.TwoForAmount) {
//                    x = 2;
//                    if (quantityAsInt >= 2) {
//                        int intDivision = quantityAsInt / x;
//                        double pricePerUnit = offer.argument * intDivision;
//                        double theTotal = (quantityAsInt % 2) * unitPrice;
//                        double total = pricePerUnit + theTotal;
//                        double discountN = unitPrice * quantity - total;
//                        discount = new Discount(p, "2 for " + offer.argument, -discountN);
//                    }
//
//                    //Redundant if
//                } if (offer.offerType == SpecialOfferType.FiveForAmount) {
//                    x = 5;
//                }
//
//
//                int numberOfXs = quantityAsInt / x;
//
//
//
//                if (offer.offerType == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
//                    double discountAmount = quantity * unitPrice - ((numberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
//                    discount = new Discount(p, "3 for 2", -discountAmount);
//                }
//                if (offer.offerType == SpecialOfferType.TenPercentDiscount) {
//                    discount = new Discount(p, offer.argument + "% off", -quantity * unitPrice * offer.argument / 100.0);
//                }
//                if (offer.offerType == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
//                    // Duplicate Code (TwoForAmount)
//                    double discountTotal = unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice);
//                    discount = new Discount(p, x + " for " + offer.argument, -discountTotal);
//                }
//                if (discount != null)
//                    receipt.addDiscount(discount);
//            }
//
//        }
//    }
//}
