package com.api.project.blog.payload;

import java.util.List;

public class PostResponse {
   
	List<PostDto> content;
	private int pageNo;
	private int pageSuze;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
	public PostResponse() {
		//TODO Auto-generated constructor stub
	}

	public PostResponse(List<PostDto> content, int pageNo, int pageSuze, long totalElements, int totalPages,
			boolean last) {
		super();
		this.content = content;
		this.pageNo = pageNo;
		this.pageSuze = pageSuze;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSuze() {
		return pageSuze;
	}

	public void setPageSuze(int pageSuze) {
		this.pageSuze = pageSuze;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLast() {
		return last;
	}

	public void setLast(boolean last) {
		this.last = last;
	}
	
	
	
	
}
