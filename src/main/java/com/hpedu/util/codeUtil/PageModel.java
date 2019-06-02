package com.hpedu.util.codeUtil;

import java.util.List;
public class PageModel {

	//结果集
	private List list ;
	
	//查询总记录数
	private int totalRecords ;
	
	//每页多少条数据
	private int pageSize ;
	
	//第几页
	private int pageNo ;
	
	/**
	 * 总页数
	 * @return
	 */
	public int getTotalPages(){
		if(pageSize>0){
			return (totalRecords + pageSize -1) / pageSize ;
		}else{
			return 0;
		}
	}
	
	/**
	 * 取得首页
	 * @return
	 */
	public int getTopPageNo(){
		return 1 ;
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getPreviousPageNo(){
		if(pageNo <= 1){
			return 1 ;
		}
		return pageNo - 1 ;
	}
	
	/**
	 * 下一页
	 * @return
	 */
	public int getNextPageNo(){
		if(pageNo >= getBottomPageNo()){
			return getBottomPageNo() ;
		}
		return pageNo + 1 ;
	}
	
	/**
	 * 取得尾页
	 * @return
	 */
	public int getBottomPageNo(){
		return getTotalPages() ;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}
