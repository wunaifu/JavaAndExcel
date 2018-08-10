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
@Table(name="parameter")
public class ItemParameter {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "itemParameter",length = 255)
	private String itemParameter;

	@Column(name = "method",length = 255)
	private String method;

	@Column(name = "tool",length = 255)
	private String tool;

	@Column(name = "detail",length = 255)
	private String detail;

	@ManyToOne
	@JoinColumn(name = "itemNameId")
	private ItemName itemName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ItemName getItemName() {
		return itemName;
	}

	public void setItemName(ItemName itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "ItemParameter{" +
				"id=" + id +
				", itemParameter='" + itemParameter + '\'' +
				", method='" + method + '\'' +
				", tool='" + tool + '\'' +
				", detail='" + detail + '\'' +
				", itemName=" + itemName +
				'}';
	}
}
