package com.wanghuan.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wanghuan.model.sys.EmailEntity;
import com.wanghuan.model.sys.PageResult;
import com.wanghuan.service.sys.EmailService;
import com.wanghuan.utils.SecurityAuthenUtil;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/emails")
	public PageResult emailsList(@RequestParam("pageSize") int pageSize, @RequestParam("page") int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(emailService.emailsList(pageSize, page * pageSize));
		pageResult.setTotalCount(emailService.emailsSize(pageSize, page * pageSize));
		return pageResult;
	}

	@PostMapping("/email")
	public EmailEntity insertEntity(@RequestBody EmailEntity emailEntity) {
		emailEntity.setUsername(SecurityAuthenUtil.getLoginName());
		emailService.insertEntity(emailEntity);
		return emailEntity;
	}

}
