/**
 * 
 */
package com.excel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Administrator 2017年12月27日
 *
 */
@Entity
@Table(name="product")
public class Product {
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

	@Column(name = "itemName",length = 100)
	private String itemName;

	@Column(name = "itemParameter",length = 255)
	private String itemParameter;

	@Column(name = "method",length = 255)
	private String method;

	@Column(name = "tool",length = 255)
	private String tool;

	@Column(name = "detail",length = 255)
	private String detail;

	public Product() {

	}

	public Product(String barcodeNumber, String productName, String productInfo,
				   String itemName, String itemParameter, String method,
				   String tool, String detail) {
		this.barcodeNumber = barcodeNumber;
		this.productName = productName;
		this.productInfo = productInfo;
		this.itemName = itemName;
		this.itemParameter = itemParameter;
		this.method = method;
		this.tool = tool;
		this.detail = detail;
	}

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemParameter() {
		return itemParameter;
	}

	public void setItemParameter(String itemParameter) {
		this.itemParameter = itemParameter;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", barcodeNumber='" + barcodeNumber + '\'' +
				", productName='" + productName + '\'' +
				", productInfo='" + productInfo + '\'' +
				", itemName='" + itemName + '\'' +
				", itemParameter='" + itemParameter + '\'' +
				", method='" + method + '\'' +
				", tool='" + tool + '\'' +
				", detail='" + detail + '\'' +
				'}';
	}
}
