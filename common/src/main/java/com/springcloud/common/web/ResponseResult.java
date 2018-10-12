package com.springcloud.common.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * WinRetailResponse -- REST API的统一数据结构，用WinRetailResponse来封装。返回代码为200，或者0时为正常，其他为情况为错误情况
 * 
 */
public class ResponseResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1917760115313349704L;
	private int code = 200; // 错误码,默认为200
	private String message = StringUtils.EMPTY; // 消息
	private int subCode = ResponseSubCode.CODE_OK;
	private String subMessage = StringUtils.EMPTY;
	private T data; // 数据对象
	private long timestamp; // 时间戳
	
	public static final ObjectMapper MAPPER = new ObjectMapper();
	//将Json反序列化为泛型List
	@Deprecated
	public Object getListData(Class<?>... elementClasses) throws JsonParseException, JsonMappingException, IOException{
		String jsonData = MAPPER.writeValueAsString(data);
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class,elementClasses);
		return MAPPER.readValue(jsonData, javaType);
	}

	public String getMessage() {
		return message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}
	
	public int getSubCode() {
		return subCode;
	}

	public void setSubCode(int subCode) {
		this.subCode = subCode;
	}

	public String getSubMessage() {
		return subMessage;
	}

	public void setSubMessage(String subMessage) {
		this.subMessage = subMessage;
	}

	public long getTimestamp() {
		long millis = System.currentTimeMillis();
		if (timestamp == 0) {
			timestamp = millis;
		}
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ResponseResult [subCode=" + subCode + ", subMessage=" + subMessage 
				+ ", data=" + data + ", timestamp=" + timestamp + "]";
	}
	/*public String toString() {
		return "ResponseResult [code=" + code + ", message=" + message + ", subCode=" + subCode + ", subMessage="
				+ subMessage + ", data=" + data + ", timestamp=" + timestamp + "]";
	}*/

	public ResponseResult() {
	}

	public ResponseResult(int subCode) {
		this.subCode = subCode;
	}

	public ResponseResult(int subCode, T data) {
		this.subCode = subCode;
		this.data = data;
	}
	
	public ResponseResult(T data) {
		this.data = data;
	}
}
