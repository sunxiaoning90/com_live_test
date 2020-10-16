package com.live.test.javaee.springboot.notice;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("notice")
public class NoticeController {

	@Autowired
	NoticeService noticeService;

	@PostMapping(value = "/createNotice")
	public String createNotice(@RequestBody Map<String, String> map) {

//		String userId = map.get("userId");
//		return r;

		// 直接访问本地Service
		return noticeService.createNotice(map);
	}

}
