package com.insping.libra.sock.net.response;

import com.insping.libra.proto.ResGeneral.GeneralData;
import com.insping.libra.sock.net.codec.data.LibraHead;
import com.insping.libra.sock.net.codec.data.LibraMessage;
import com.insping.libra.sock.net.codec.data.UserInfo;
import com.insping.libra.world.LibraConfig;

public class GeneralResponse {
	public static final byte RESP_SUCC = 0;
	public static final byte RESP_FAIL = 1;

	UserInfo userInfo = new UserInfo();
	GeneralData.Builder builder = GeneralData.newBuilder();
	
	public GeneralResponse() {
		builder.setResultCode(RESP_SUCC);
	}
	
	public GeneralResponse(UserInfo userInfo, LibraMessage message) {
		this.userInfo = userInfo;
		builder.setProtocolID(message.getHead().getProtocolID());
		builder.setResultCode(RESP_SUCC);
	}

	public void fail() {
		builder.setResultCode(RESP_FAIL);
	}

	public void fail(String des) {
		builder.setResultCode(RESP_FAIL);
		builder.setDesc(des);
	}

	public boolean isSucc() {
		return builder.getResultCode() == RESP_SUCC;
	}

	public void setDesc(String desc) {
		builder.setDesc(desc);
	}

	public void modifyUserInfo(long uid) {
		this.userInfo.setUid(uid);
	}

	public LibraMessage result() throws Exception {
		LibraHead head = new LibraHead();
		head.setDestServerID(1);
		head.setSrcServerID(LibraConfig.SERVER_ID);
		head.setProtocolID(1);
		head.setUserInfo(userInfo);
		return new LibraMessage(head, builder.build());
	}
}
