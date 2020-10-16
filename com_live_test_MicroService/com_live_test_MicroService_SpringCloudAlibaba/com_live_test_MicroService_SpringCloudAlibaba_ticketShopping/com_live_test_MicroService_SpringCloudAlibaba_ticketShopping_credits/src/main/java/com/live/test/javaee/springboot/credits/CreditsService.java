package com.live.test.javaee.springboot.credits;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.live.test.javaee.springboot.app.App;

@Service
public class CreditsService {

	public String createCredits(Map<String, String> map) {
		String userId = map.get("userId");
		String ticketId = map.get("ticketId");
		String pcs = map.get("pcs");

		String r = new StringBuffer("响应来自:" + App.APP_NAME_ALIAS + ",增加积分成功：").append("userId:").append(userId)
				.append(",ticketId:").append(ticketId).append(",pcs:").append(pcs).toString();
		System.out.println(r);
		return r;

		// rpc调用远端服务
	}

}
