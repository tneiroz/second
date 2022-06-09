package me.eun.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageMaker {
	
	private Criteria criteria;
	private int startPage;
	private int endPage;
	
	private int displayPageNum=10;
	// 한페이지당 보여질 페이지 갯수 
	
	public PageMaker(Criteria criteria) {
		this.criteria = criteria;
		endPage = (int) Math.ceil(criteria.getPage()/(double)displayPageNum) * displayPageNum;
		startPage = endPage - displayPageNum + 1;
		//(올림) (5/10) * 10 = 10		10-10 +1 = 1
		//(올림) (14/10) * 10 = 20	    20-10 +1 = 11
		
	}
}
