
package me.anant.PMS.model;


public class ProductImpl extends Product {

    public ProductImpl() {
    }

    public ProductImpl(long productId) {
        super(productId);
    }

    public ProductImpl(String productName, float productPrice, int productQty, ProductCategory category) {
        super(productName, productPrice, productQty, category);
    }
    
}
