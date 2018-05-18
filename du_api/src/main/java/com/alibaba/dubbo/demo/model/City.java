package com.alibaba.dubbo.demo.model;

/**
 * @author 爱不留
 * @date 2018年5月10日 下午1:51:01
 **/
public class City {

	private String id;
	private String name;
	private Integer ln;

	public City() {
		super();
	}

	public City(String id, String name, Integer ln) {
		super();
		this.id = id;
		this.name = name;
		this.ln = ln;
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

	public Integer getLn() {
		return ln;
	}

	public void setLn(Integer ln) {
		this.ln = ln;
	}

}
