package com.gdu.notice.domain;

public class NoticeDTO {

	private int notice_no;
	private int gubun;
	private String title;
	private String content;
	
	public NoticeDTO() {
		
	}
	public NoticeDTO(int notice_no, int gubun, String title, String content) {
		super();
		this.notice_no = notice_no;
		this.gubun = gubun;
		this.title = title;
		this.content = content;
	}
	
	public int getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}
	public int getGubun() {
		return gubun;
	}
	public void setGubun(int gubun) {
		this.gubun = gubun;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
