package com.alibaba.dubbo.demo.model;

/**
 * @author 爱不留
 * @date 2018年5月10日 上午9:53:04
 **/
public class Person {

	private String id;
	private String name;
	private Integer age;

	public Person() {
		super();
	}

	public Person(String id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
