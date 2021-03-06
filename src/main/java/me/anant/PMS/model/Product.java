package me.anant.PMS.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Product implements Serializable {
	@Id
	@GeneratedValue
	long productId;

	@NotBlank(message="O produto deve ter um nome!")
	@Size(min = 3, message="O produto deve ter no mínimo três letras ou caracteres!")
	String productName;
	
	@NotNull
	@Min(value=0, message="O preço do produto não pode ser um valor negativo.")
	float productPrice;
	
	@NotNull
	@Min(value=0, message="A quantidade do produto não pode ser negativa!")
	@Max(value=50, message="A quantidade do produto pode ser no máximo 50 unidades.")
	int productQty;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    Set<OrderProduct> orderProduct;

	@ManyToOne(fetch = FetchType.LAZY)
    ProductCategory category;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(long productId) {
		super();
		this.productId = productId;
	}

	public Product(String productName, float productPrice, int productQty, ProductCategory category) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQty = productQty;
		this.category = category;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQty() {
		return productQty;
	}

	public void setProductQty(int productQty) {
		this.productQty = productQty;
	}

	public Set<OrderProduct> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(Set<OrderProduct> orderProduct) {
		this.orderProduct = orderProduct;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
}
