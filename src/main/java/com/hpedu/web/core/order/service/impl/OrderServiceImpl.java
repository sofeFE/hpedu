package com.hpedu.web.core.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.web.core.order.dao.OrderMapper;
import com.hpedu.web.core.order.pojo.ArticleImg;
import com.hpedu.web.core.order.pojo.Banner;
import com.hpedu.web.core.order.pojo.Order;
import com.hpedu.web.core.order.service.OrderService;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.service.ContestVideoService;
import com.hpedu.web.core.video.service.GeneralVideoService;
import com.hpedu.web.core.wxpay.util.WechatPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(readOnly = true , propagation = Propagation.REQUIRED )
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
	@Autowired
	private GeneralVideoService generalVideoService ;
	@Autowired
	private ContestVideoService contestVideoService ;
	@Autowired
	private MyBatisBase myBatisBase ;
	@Autowired
	WechatPayUtil wechatPayUtil ;
	
	@Override
	public List<Order> findOrderListByPage(int pageno, int pagesize)
			 {
		
		int skip=(pageno-1)*pagesize;
		return baseMapper.findOrderListByPage(skip, pagesize);
	}
	@Override
	public int findOrderListCount()  {
		
		return baseMapper.findOrderListCount();
	}
	@Override
	public List<Order> findAllOrderByUserId(String uid)  {
		
		return baseMapper.findAllOrderByUserId(uid);
	}
	@Override
	public String getMaxOrderNoByOrderNoPre(String prev) {
		
		return baseMapper.getMaxOrderNoByOrderNoPre(prev);
	}
	@Override
	public int getIsBuyVideoByVid(String vid, String vclassify, String uid) {
		
		return baseMapper.getIsBuyVideoByVid(vid, vclassify, uid);
	}
	
	@Transactional(readOnly = false)
	@Override
	public int insertOrder(Order o) {
		
		return baseMapper.insertOrder(o);
	}
	@Override
	@Transactional(readOnly =false)
	public int updateOrderPayStatus(String orderNo) {
		
		return baseMapper.updateOrderPayStatus(orderNo);
	}
	@Override
	public Order findOrderByOid(String oid) {
		
		return baseMapper.findOrderByOid(oid);
	}
	@Override
	public List<Order> findOrderByParams(String uid, String vid,
			String vclassify,String oisPay) {
		
		return baseMapper.findOrderByParams(uid, vid, vclassify,oisPay);
	}
	@Override
	public Order findOrderByOrderNo(String orderNo) {
		
		return baseMapper.findOrderByOrderNo(orderNo);
	}
	@Override
	public List<Banner> selectAllBanner() {
		
		return baseMapper.selectAllBanner();
	}
	@Override
	@Transactional(readOnly =false)
	public int insertBanner(Banner b) {
		
		return baseMapper.insertBanner(b);
	}
	@Override
	@Transactional(readOnly =false)
	public int deleteBanner(String bid) {
		
		return baseMapper.deleteBanner(bid);
	}
	@Override
	@Transactional(readOnly =false)
	public int updateBannerSort(String bid, String sort) {
		
		return baseMapper.updateBannerSort(bid, sort);
	}
	@Override
	public Banner findBannerByBid(String bid) {
		
		return baseMapper.findBannerByBid(bid);
	}
	@Override
	@Transactional(readOnly =false)
	public int updateBanner(Banner b) {
		
		return baseMapper.updateBanner(b);
	}
	@Override
	@Transactional(readOnly =false)
	public int updateOrderPayMoney(String orderId, String money,String orderNo,String bodyDes) {
		
		return baseMapper.updateOrderPayMoney(orderId, money,orderNo,bodyDes);
	}
	@Override
	@Transactional(readOnly =false)
	public int insertArticleImgs(List<ArticleImg> list) {
		
		return baseMapper.insertArticleImgs(list);
	}
	@Override
	@Transactional(readOnly =false)
	public int deleteArticleImgs(String[] list) {
		
		return baseMapper.deleteArticleImgs(list);
	}
	@Override
	public List<ArticleImg> selectArticleImgByBid(String bid) {
		
		return baseMapper.selectArticleImgByBid(bid);
	}
	
	@Override
	@Cacheable(value="welcomePage"/*,key="bannerList"*/)
	public List<Banner> selectAllWebBanner() {
		return baseMapper.selectAllWebBanner();
	}

	@Override
	@Transactional(readOnly =false)
	public int updateOrder(Map<String,Object> order) {
		return baseMapper.updateOrder(order) ;
	}

	@Override
	@Transactional(readOnly =false,rollbackFor = {Exception.class})
	public Map<String, Object> getWePayCodeImgMap(String vid, String vclassify, String uid) {
		
			String code_url2 = "";
			Map<String, Object> resMap = new HashMap<String, Object>();
			GeneralVideo g = null;
			ContestVideo c = null;
			if ("0".equals(vclassify)) {//常规
				g = generalVideoService.findGeneralVideoByVid(vid);
			} else {
				c = contestVideoService.findContestVideoById(vid);
			}

			Map<String, Object> killInfo = BaseUtil.createKillInfo(vid, vclassify, uid, g, c);

			String orderPrice = (String) killInfo.get("orderPrice");//当前应支付金额
			//先查询该用户是否已有 未支付的同类订单
			List<Order> olist = findOrderByParams(uid, vid, vclassify, "0");
			Order order = null;
			String orderNo = "";
			String bodyDes = (String) killInfo.get("bodyDes");


			if (olist.size() > 0) {//已存在的订单
				order = olist.get(0);
				if (!orderPrice.equals(order.getOmoney()) || !bodyDes.equals(order.getPayStyle())) {
					order.setOmoney(orderPrice);
					/*可以后续添加支付方式 ,此处  定死为 0 微信.*/
//                order.setPayStyle(0);
					/*2019年4月17日22:10:40 gd 修改 为原订单号*/
					order.setOrderNo(createOrderNumber());//订单编号 
					orderNo = order.getOrderNo() ;
//                order.setOrderNo(orderNo);
					Map<String,Object> map = new HashMap<>();
					map.put("oid",order.getOid());
					map.put("omoney",order.getOmoney());
					map.put("orderNo",order.getOrderNo());
					updateOrder(map);
//                orderService.updateOrderPayMoney(order.getOid(), orderPrice, orderNo, bodyDes);
				} else {
					orderNo = order.getOrderNo();
				}
			} else if (Float.parseFloat(orderPrice) > 0) {//新生成订单
				order = new Order();
				order.setOid(UUIDUtil.getUUID());
				order.setOcreatTime(new Date().getTime());
				order.setOmoney(orderPrice);
				order.setOrderNo(createOrderNumber());
				order.setUid(uid);
				order.setVclassify(vclassify);
				order.setVid(vid);
				order.setPayStyle(0);
				insertOrder(order);
			}
			
			Map<String, String> map = null;
			if (Float.parseFloat(orderPrice) > 0) {
				map = wechatPayUtil.createWeChatOrder(orderNo, order.getOmoney(), bodyDes.toString(), "/order/callBackAfterPay");
				if (map.get("code_url") != null) {
					code_url2 = map.get("code_url");
				}
				
			}

			resMap.put("url", code_url2);
			resMap.put("omoney", orderPrice);
			return resMap;
	}
	
	//根据年月日生成订单号
	private String createOrderNumber() {
		String dateString = BaseUtil.getCurrentDateStr("yyyyMMdd");
		String orderNo = getMaxOrderNoByOrderNoPre(dateString);
		orderNo = StringUtils.isBlank(orderNo) ? dateString + "00000000" : String.valueOf(Long.parseLong(orderNo) + 1);
		return orderNo;
	}

}
