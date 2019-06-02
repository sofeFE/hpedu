/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hpedu.web.core.shiro.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.PageUtils;
import com.hpedu.web.core.shiro.pojo.SysLogEntity;

import java.util.Map;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysLogService extends IService<SysLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

}
