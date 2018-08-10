/**
 * 
 */
package com.excel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Administrator 2017年12月27日
 *
 */
@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	//登录账号
	@Column(name = "name",length = 16)
	private String name;

	@Column(name = "sex",length = 16)
	private String sex;

	@Column(name = "age",length = 16)
	private String age;


	public User(String name, String sex, String age) {
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}


}
