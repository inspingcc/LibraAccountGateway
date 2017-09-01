package com.insping.libra.http.net;

import com.insping.common.utils.JsonUtil;

public class ReturnObject {
	int code;
	int status;
	String data;

	public ReturnObject() {
		code = 200;
		status = 0;
		data = "";
	}

	public void success(int code) {
		this.code = code;
	}

	/**
	 * 成功,并设置data数据
	 * @param data
	 */
	public void success(String data) {
		this.data = data;
	}

	public void success(int code, String data) {
		this.code = code;
		this.data = data;
	}

	public void fail(int code) {
		this.code = code;
	}

	public void fail(String data) {
		this.code = 666;
		this.data = data;
	}

	public void fail(int code, String data) {
		this.code = code;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return JsonUtil.ObjectToJsonString(this);
	}

}
