package com.gdu.notice01.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.notice01.domain.NoticeDTO;
import com.gdu.notice01.repository.NoticeDAO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeDAO noticeDAO;

	@Override
	public List<NoticeDTO> getNoticeList() {
		return noticeDAO.getNoticeList();
	}

	@Override
	public NoticeDTO getNotice(HttpServletRequest request) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("notice_no"));		// 상세보기할 게시글이 없을 수도 있기 때문에 => null일 수도 있다 => null처리 해줘야함
		int notice_no = Integer.parseInt(opt.orElse("0"));
		return noticeDAO.getNotice(notice_no);
	}

	@Override
	public int addNotice(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setGubun(Integer.parseInt(request.getParameter("gubun")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		return noticeDAO.addNotice(notice);
	}

	@Override
	public int modifyNotice(HttpServletRequest request) {
		NoticeDTO notice = new NoticeDTO();
		notice.setGubun(Integer.parseInt(request.getParameter("gubun")));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		notice.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
		return noticeDAO.modifyNotice(notice);
	}

	@Override
	public int removeNotice(HttpServletRequest request) {
		Optional<String> opt = Optional.ofNullable(request.getParameter("notice_no"));
		int notice_no = Integer.parseInt(opt.orElse("0"));
		return noticeDAO.removeNotice(notice_no);
	}

}
