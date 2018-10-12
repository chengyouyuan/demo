package com.springcloud.common.web;

/**
 * @author 魏冰
 * @date 2017年3月22日 上午9:59:16
 * @Description 手机请求接口Header参数
 * @version 1.0
 */
public class RequestHeaders {

	protected String version; // 客户端版本version，例：1.0.0
	protected String token;// 登陆成功后，server返回的登陆令牌token
	protected String os;// 手机系统版本（Build.VERSION.RELEAS）例：4.4，4.5
	protected String platform;// 请求来源，例：android/ios/h5
	protected String model;// 机型信息（Build.MODEL），例：Redmi Note 3
	protected String channel;// 渠道信息，例：(yingyongbao、appstore)
	protected String net;// APP当前网络状态，例：wifi，mobile；部分接口可以根据用户当前的网络状态，下发不同数据策略，如：wifi则返回高清图，mobile情况则返回缩略图
	protected String grp;// APP唯一标识，需要区分开每个APP来源(门店版：winretailsaler；业代版：winretailsr)
	protected String lang;// 语言(中国：zh)
	protected String imei;// 设备唯一标示
	protected String timestamp;// 时间戳
	protected String signature;// 签名
	protected String did;// 设备唯一标示
	protected String userid; // 用户唯一标识

	public RequestHeaders() {
		super();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "RequestHeaders [version=" + version + ", token=" + token + ", os=" + os + ", platform=" + platform
				+ ", model=" + model + ", channel=" + channel + ", net=" + net + ", grp=" + grp + ", lang=" + lang
				+ ", imei=" + imei + ", timestamp=" + timestamp + ", signature=" + signature + ", did=" + did
				+ ", userid=" + userid + "]";
	}

}
