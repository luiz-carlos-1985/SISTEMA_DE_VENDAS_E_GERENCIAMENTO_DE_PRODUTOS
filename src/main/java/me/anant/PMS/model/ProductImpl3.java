
package me.anant.PMS.model;


public class ProductImpl3 extends Product {

    public ProductImpl3() {
    }

    public ProductImpl3(long productId) {
        super(productId);
    }

    public ProductImpl3(String productName, float productPrice, int productQty, ProductCategory category) {
        super(productName, productPrice, productQty, category);
    }
    
}
