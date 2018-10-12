package com.springcloud.common.web;

/**
 * @author 魏冰
 * @date 2017年3月23日 下午7:16:05
 * @Description API响应码
 * @version 1.0
 */
public class ResponseCode {

	/**
	 * OK
	 */
	public static final int CODE_OK = 200;

	/**
	 * 服务器内部错误
	 */
	public static final int CODE_INTERNAL_SERVER_ERROR = 500;

	private ResponseCode() {
		super();
	}

}
