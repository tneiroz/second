package me.eun.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	int page;
	int perPageNum;
	
	String type; //제목 , 내용 , 작성자 
	String keyword;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getpageStart() {
		return (this.page-1)*perPageNum;
	}
	
	// 제목: T 내용: C 작성자 W
	// 제목+내용: TC
	// 내용+작성자 : CW
	public String [] getTypeCollection() {//#{typeCollection} boardmapper.xml에 있는 typecollection 이용
		return this.type !=null ? type.split("") : new String[] {};
		
	}
}
