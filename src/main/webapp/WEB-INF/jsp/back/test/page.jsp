<%@ page language="java"  pageEncoding="UTF-8"%>
<div class="pagelist">
	 <em>共${pages.getTotalPages()}页</em>&nbsp &nbsp &nbsp
	 <a onclick="pageSize(1)">首页</a> 
	 <a onclick="pageSize(${pages.getPrePage()})">上一页</a>
	 <a onclick="pageSize(${pages.getNextPage()})">下一页</a>
	 <a onclick="pageSize(${pages.getTotalPages()})">尾页</a>&nbsp &nbsp &nbsp
	 <em>共${pages.totalCount}条</em>&nbsp &nbsp &nbsp
	 <em>当前第${pages.pageNo}页</em>
 </div>