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
@Table(name="prod")
public class Prod {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "barcodeNumber",length = 40)
	private String barcodeNumber;

	@Column(name = "productName",length = 100)
	private String productName;

	@Column(name = "productInfo",length = 100)
	private String productInfo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "prod")
	private List<ItemName> itemNameList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcodeNumber() {
		return barcodeNumber;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
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

	public List<ItemName> getItemNameList() {
		return itemNameList;
	}

	public void setItemNameList(List<ItemName> itemNameList) {
		this.itemNameList = itemNameList;
	}

	@Override
	public String toString() {
		return "Prod{" +
				"id=" + id +
				", barcodeNumber='" + barcodeNumber + '\'' +
				", productName='" + productName + '\'' +
				", productInfo='" + productInfo + '\'' +
				", itemNameList=" + itemNameList +
				'}';
	}
}
