package com.hpedu.web.core.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.web.core.order.pojo.ArticleImg;
import com.hpedu.web.core.order.pojo.Banner;
import com.hpedu.web.core.order.pojo.Order;

import java.util.List;
import java.util.Map;
/**
 * 订单service
 * */
public interface OrderService  extends IService<Order> {
	/**
	 * 查询该用户所有购买记录
	 * */
	List<Order> findAllOrderByUserId(String uid);
	/**
	 * 订单查看(分页)
	 * 
	 * */
	/*Page<Order>  findOrderListByPage(Map<String, String> map,int pageNo,int pageSize) ;*/
	List<Order>  findOrderListByPage(int pageno,int pagesize) ;
	int findOrderListCount();
	String getMaxOrderNoByOrderNoPre(String prev);
	/**
	 *检查用户是否购买过某视频
	 * 
	 * */
	int getIsBuyVideoByVid(String vid,String vclassify,String uid );
	/**
	 *新增订单
	 * 
	 * */
	int insertOrder(Order o);
	/**
	 *修改订单支付状态
	 * 
	 * */
	int updateOrderPayStatus(String orderNo);
	/**
	 *修改订单支付价格
	 * 
	 * */
	int updateOrderPayMoney(String orderId,String money,String orderNo,String bodyDes);
	/**
	 *根据订单id查询订单
	 * 
	 * */
	Order findOrderByOid(String oid);
	/**
	 *根据用户id、vid、vclassify查询是否存在未支付订单
	 * 
	 * */
	List<Order> findOrderByParams(String uid,String vid,String vclassify,String oisPay);
	/**
	 *根据订单号查询订单
	 * 
	 * */
	Order findOrderByOrderNo(String orderNo);
	/**
	 *  查询所有首页轮播图
	 * 
	 * */
    List<Banner> selectAllBanner();	
    /**
	 *  新增首页轮播图
	 * 
	 * */
    int insertBanner(Banner b);
    /**
   	 *  删除首页轮播图
   	 * 
   	 * */
    int deleteBanner(String  bid);
    /**
   	 *  修改首页轮播图顺序
   	 * 
   	 * */
    int updateBannerSort(String  bid,String sort);
    /**
   	 *  根据id查询首页轮播图
   	 * 
   	 * */
       Banner findBannerByBid(String  bid);	
       /**
      	 *  修改首页轮播图
      	 * 
      	 * */
       int updateBanner(Banner b);
       
       
       /**
   	 * 批量新增轮播图文章图片
   	 * 
   	 * */
   	int  insertArticleImgs(List<ArticleImg> list);
   	/**
   	 * 批量删除轮播图文章图片
   	 * 
   	 * */
   	int  deleteArticleImgs(String[] list);
   	
   	List<ArticleImg> selectArticleImgByBid(String bid);
   	
   	/**
   	 * 获取所有网站端的轮播图
   	 * @return
   	 */
	List<Banner> selectAllWebBanner();

	/**
	 * 修改订单
	 * @param order
	 */
	int updateOrder(Map<String,Object> order);

	/**
	 * 获取 微信支付 二维码 url map
	 * @param vid
	 * @param vclassify
	 * @param uid
	 * @return
	 */
	Map<String, Object> getWePayCodeImgMap(String vid, String vclassify, String uid);
}
