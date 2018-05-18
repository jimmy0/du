package com.alibaba.dubbo.demo.bean;

/**
 * @author 爱不留
 * @date 2018年5月10日 上午9:54:52
 **/
public class Valid {
	private Boolean success;
	private Integer code;
	private String msg;
	private Object data;
	private Object other;

	public static Valid SUCCESS = new Valid(true, "操作成功");
	public static Valid FAILED = new Valid(false, "操作失败");

	public Valid() {

	}

	public Valid(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}

	public Valid(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	public Valid(Boolean success, Integer code, String msg, Object data) {
		super();
		this.success = success;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Valid(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Valid(Boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getOther() {
		return other;
	}

	public void setOther(Object other) {
		this.other = other;
	}
	
	
	
}
