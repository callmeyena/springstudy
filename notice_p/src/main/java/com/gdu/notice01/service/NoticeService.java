package com.gdu.notice01.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.gdu.notice01.domain.NoticeDTO;

public interface NoticeService {
	
	public List<NoticeDTO> getNoticeList();
	public NoticeDTO getNotice(HttpServletRequest request);
	public int addNotice(HttpServletRequest request);
	public int modifyNotice(HttpServletRequest request);
	public int removeNotice(HttpServletRequest request);
	

}
