/**
 * 
 */
package com.excel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author Administrator 2017年12月27日
 *
 */
@Entity
@Table(name="item")
public class ItemName {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "itemName",length = 100)
	private String itemName;

	@Column(name = "productNumber",length = 100)
	private String productNumber;

	@Column(name = "productName",length = 100)
	private String productName;

	@Column(name = "productInfo",length = 100)
	private String productInfo;

	@ManyToOne
	@JoinColumn(name = "prodId")
	private Prod prod;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "itemName")
	private List<ItemParameter> itemParameterList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public Prod getProd() {
		return prod;
	}

	public void setProd(Prod prod) {
		this.prod = prod;
	}

	public List<ItemParameter> getItemParameterList() {
		return itemParameterList;
	}

	public void setItemParameterList(List<ItemParameter> itemParameterList) {
		this.itemParameterList = itemParameterList;
	}

	@Override
	public String toString() {
		return "ItemName{" +
				"id=" + id +
				", itemName='" + itemName + '\'' +
				", productNumber='" + productNumber + '\'' +
				", productName='" + productName + '\'' +
				", productInfo='" + productInfo + '\'' +
				", prod=" + prod +
				", itemParameterList=" + itemParameterList +
				'}';
	}
}
