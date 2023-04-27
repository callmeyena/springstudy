package com.gdu.notice01.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.notice01.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	@GetMapping("/notice/list.do")											// index로 부터 온 mapping 값
	public String list(Model model) {										// Model 객체는 jsp로 forward해줄 수 있다.
		model.addAttribute("noticeList", noticeService.getNoticeList());
		return "/notice/list"; 												// list.jsp경로로 ㄱㄱ
	}
	
	@GetMapping("/notice/write.do")
	public String write() {
		return "/notice/write";
	}
	
	@PostMapping("/notice/add.do")
	public String add(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("addResult", noticeService.addNotice(request));
		return "redirect:/notice/list.do";
	}
	
	@GetMapping("/notice/detail.do")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("notice", noticeService.getNotice(request));
		return "/notice/detail";
	}
	
	@PostMapping("/notice/modify.do")
	public String modify(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("modifyResult", noticeService.modifyNotice(request));
		return "redirect:/notice/detail.do?notice_no=" + request.getParameter("notice_no");
	}
	
	@GetMapping("/notice/remove.do")
	public String remove(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", noticeService.removeNotice(request));
		return "redirect:/notice/list.do";
	}
	
	


}
