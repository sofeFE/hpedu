package com.hpedu.web.core.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.ResultBean;
import com.hpedu.util.codeUtil.BaseUtil;
import com.hpedu.util.codeUtil.DateUtil;
import com.hpedu.util.codeUtil.FontImage;
import com.hpedu.util.codeUtil.UUIDUtil;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.exam.service.ExamService;
import com.hpedu.web.core.shiro.ShiroUtils;
import com.hpedu.web.core.user.dao.UserMapper;
import com.hpedu.web.core.user.pojo.*;
import com.hpedu.web.core.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private MyBatisBase myBatisBase;

    @Override
    public User findUserByPhone(String userPhone) {

        User user = baseMapper.findUserByPhone(userPhone);
        return user;
    }

    @Override
    public User backUserLogin(Map<String, String> maps) {

        User user = baseMapper.backUserLogin(maps);
        return user;
    }

    @Override
    public void addUser(User user) {

        baseMapper.addUser(user);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void updateUserNews(User user) {

        baseMapper.updateUserNews(user);
    }

    @Override
    public int getIsExitsByPhone(String phoneNo) {

        return baseMapper.getIsExitsByPhone(phoneNo);
    }

    @Override
    public User findUserByUid(String uid) {

        return baseMapper.findUserByUid(uid);
    }

    @Override
    public Page<User> searchUserList(Map<String, String> map, int pageNo,
                                     int pageSize) {
        Page<User> page = myBatisBase.queryPage("com.hpedu.web.core.user.dao.UserMapper.searchUserList",
                "com.hpedu.web.core.user.dao.UserMapper.searchUserListCount", map, pageNo, pageSize);
        return page;
    }

    @Override
    public List<User> searchUserList(Map<String, String> map) {

        return baseMapper.searchUserList(map);
    }

    @Override
    public int deleteUserById(String uid) {

        return baseMapper.deleteUserById(uid);
    }

    @Override
    public int getIsExitsByName(String userName) {

        return baseMapper.getIsExitsByName(userName);
    }

    @Override
    public List<UserLevel> selectAllLevel() {

        return baseMapper.selectAllLevel();
    }

    @Override
    public UserLevel selectLevelById(String ulid) {

        return baseMapper.selectLevelById(ulid);
    }

    @Override
    public void insertLevel(UserLevel ul) {

        baseMapper.insertLevel(ul);
    }

    @Override
    public void updateLevel(UserLevel ul) {

        baseMapper.updateLevel(ul);
    }

    @Override
    public int deleteLevel(String ulid) {

        return baseMapper.deleteLevel(ulid);
    }

    @Override
    public void updateLearnTotalTime(String uid, Long addtime) {

        baseMapper.updateLearnTotalTime(uid, addtime);
    }

    @Override
    public void insertLearnTimeByDay(UserLearn learn) {

        baseMapper.insertLearnTimeByDay(learn);
    }

    @Override
    public String selectIsExitUserLearn(String uid, String vid, String vclassify) {

        return baseMapper.selectIsExitUserLearn(uid, vid, vclassify);
    }

    @Override
    public void updateLearnTimeByDay(UserLearn learn) {

        baseMapper.updateLearnTimeByDay(learn);
    }

    @Override
    public int getLearnVideoTotalCount(String userId) {

        return baseMapper.getLearnVideoTotalCount(userId);
    }

    @Override
    public List<Map> findlearnListByPage(String userId, int pageno, int pagesize) {

        int skip = (pageno - 1) * pagesize;
        return baseMapper.findlearnListByPage(userId, skip, pagesize);
    }

    @Override
    public List<Map> getLearnTimeByUserId(String userId, String dateStr) {

        return baseMapper.getLearnTimeByUserId(userId, dateStr);
    }

    @Override
    public List<String> getYearByUserId(String userId) {

        return baseMapper.getYearByUserId(userId);
    }

    @Override
    public List<Map> selectVidoeTestAll(Map<String, Object> map) {

        return baseMapper.selectVidoeTestAll(map);
    }

    @Override
    public void insertUnitTest_Choose(List<UnitTest_Choose> list) {

        baseMapper.insertUnitTest_Choose(list);
    }


    @Override
    public void updateUnitTest_Choose(List<UnitTest_Choose> list) {

        baseMapper.updateUnitTest_Choose(list);
    }


    @Override
    public void delUnitTest_Choose(String[] list) {

        baseMapper.delUnitTest_Choose(list);
    }

    @Override
    public void insertUserScore(UserScore us) {

        baseMapper.insertUserScore(us);
    }

    @Override
    public void insertErrorExam(List<ErrorExam> list) {

        baseMapper.insertErrorExam(list);
    }

    @Override
    public void insertUnitTest(UnitTest u) {

        baseMapper.insertUnitTest(u);
    }

    @Override
    public void updateUnitTest(UnitTest u) {

        baseMapper.updateUnitTest(u);
    }

    @Override
    public void delUnitTest(String id) {

        baseMapper.delUnitTest(id);
    }

    @Override
    public List<Map> selectVidoeTestByUtid(String utid) {

        return baseMapper.selectVidoeTestByUtid(utid);
    }

    @Override
    public List<Map> getTotalUnitTests(String vid, int utype) {

        return baseMapper.getTotalUnitTests(vid, utype);
    }

    @Override
    public void updateUserScore(UserScore us) {

        baseMapper.updateUserScore(us);
    }

    @Override
    public void updateErrorExam(List<ErrorExam> list) {

        baseMapper.updateErrorExam(list);
    }

    @Override
    public Page searchUnitTestList(Map<String, Object> map, int pageno,
                                   int pagesize) {

        int skip = (pageno - 1) * pagesize;
        map.put("skip", skip);
        map.put("limit", pagesize);
        
        List<Map> list = baseMapper.getUnitTestDetail(map);
        
        Page pages = new Page();
        pages.setResult(list);
        pages.setPageNo(pageno);
        pages.setPageSize(pagesize);
        pages.setTotalCount(baseMapper.searchUnitTestCount(map));
        return pages;
    }

    @Override
    public List<Map> getJDTScoresDetail(String usid) {

        return baseMapper.getJDTScoresDetail(usid);
    }

    @Override
    public List<RightMenu> selectRightMenu(String checkVal) {

        return baseMapper.selectRightMenu(checkVal);
    }

    @Override
    public void updatePwdByPhone(String phnone, String pwd) {

        baseMapper.updatePwdByPhone(phnone, pwd);
    }

    @Override
    public void updateYQCodeByUserId(String uid, String yqcode, String yqCodeUrl) {

        baseMapper.updateYQCodeByUserId(uid, yqcode, yqCodeUrl);
    }

    @Override
    public String[] getNewYQCode(String uploadAbsolutePath) {

        String yqcode = String.valueOf(new Date().getTime());//随机生成邀请码

        //生成邀请码图片 
        String path = BaseUtil.getServerPath("yqCode", "", uploadAbsolutePath);

        String yqCodeUrl = "";
//		String yqCodeUrl="/yqCode/";
        try {
            yqCodeUrl += FontImage.getImage(yqcode, path);
        } catch (IOException e) {
            throw new RuntimeException("生成邀请码异常");
        }
        return new String[]{yqcode, yqCodeUrl};
    }

    @Override
    public User getUserByYQCode(String yqcode) {

        return baseMapper.getUserByYQCode(yqcode);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public String insertUser(String ycode, User user, String code, String uploadAbsolutePath) {
        String res = "";
        if (ycode != null && code != null && ycode.equals(code)) {
            user.setUid(UUIDUtil.getUUID());
            String salt = RandomStringUtils.randomAlphanumeric(20);

//				user.setPassWord(new MD5(user.getPassWord()).compute_upper());
            user.setPassWord(ShiroUtils.sha256(user.getPassWord(), salt));
            //若使用码不为空，更新邀请人的vip时间
            User invateUser = null;//邀请人
            if (!StringUtils.isEmpty(user.getUsedCode())) {
                invateUser = getUserByYQCode(user.getUsedCode());
            }
            int type = user.getType();
            int status = 0;//未审核

            if (type == 0 || invateUser != null) {//普通用户
                status = 1;
            }
            user.setStatus(status);
            user.setRegTime(new Date());
            if (invateUser != null) {//使用邀请码注册
                user.setIsVip("1");
                user.setFreeType("1月");
                user.setEndTime(DateUtil.addMonth(new Date(), 1));//被邀请人延长1个月
            } else {
                user.setIsVip("0");
            }
            user.setLearnTime(0l);
            //生成自己的邀请码
            String[] codes = getNewYQCode(uploadAbsolutePath);
            user.setYqCode(codes[0]);
            user.setYqCodeUrl(codes[1]);
            addUser(user);
            //被邀请人延长半个月
            upadteInvator(invateUser);
        } else {
            res = "手机验证码不正确";
        }
        return res;
    }

    /**
     * 被邀请人延长半个月
     *
     * @param invateUser
     * @
     */
    private void upadteInvator(User invateUser) {
        if (invateUser != null) {
            String endTimeStr = invateUser.getEndTime();
            Date endTime = null;
            if (endTimeStr == null) {
                endTime = new Date();
            } else {
                endTime = DateUtil.string2Date(endTimeStr, "yyyy-MM-dd");
            }
            invateUser.setEndTime(DateUtil.addDate(endTime, 15));//到期时间延长半个月
            invateUser.setStatus(1);
            invateUser.setIsVip("1");
            updateUserNews(invateUser);
        }
    }

    @Cacheable(cacheNames = {"visitorNum"}, key = "'number'", sync = true)//,key ="'#root.caches[0].number'"
    @Override
    public int getVisitorNum() {
        return baseMapper.getVisitorNum();
    }


    @Override
    public void updateVisitorNum(int i) {

        baseMapper.updateVisitorNum(i);
    }

    @Override
    public ResultBean updateLearn(User u, String vid, String vclassify, String time) {

        Long realTime = Long.parseLong(time.substring(0, time.indexOf(".")));
        try {
            updateLearnTotalTime(u.getUid(), realTime);
            u.setLearnTime(u.getLearnTime() + realTime);
        } catch (Exception e) {
            log.error("修改学生【uid:" + u.getUid() + "】总学习时间失败", e);
            return ResultBean.failed("修改学生【uid:" + u.getUid() + "】总学习时间失败");
        }
        //修改当天的学习记录
        try {
            String ulid = selectIsExitUserLearn(u.getUid(), vid, vclassify);
            UserLearn ul = new UserLearn();
            if (ulid == null) {//新增学习记录
                ul.setLearnTime(realTime);
                ul.setUlid(UUIDUtil.getUUID());
                ul.setUserid(u.getUid());
                ul.setVctype(Integer.parseInt(vclassify));
                ul.setVid(vid);
                insertLearnTimeByDay(ul);
            } else {//修改学习记录
                ul.setLearnTime(realTime);
                ul.setUlid(ulid);
                updateLearnTimeByDay(ul);
            }
        } catch (Exception e) {
            log.error("修改学生【uid:" + u.getUid() + "】当天学习时间失败：", e);
            return ResultBean.failed("修改学生【uid:" + u.getUid() + "】当天学习时间失败：");
        }
        return ResultBean.ok();
    }

    @Autowired
    ExamService examService;
    
    @Override
    @Transactional(readOnly = false,rollbackFor = {Exception.class})
    public ResultBean addUserScore(User user, UserScore stuScore, List<Map> jdtAnswerList) {

        try {
            stuScore.setUsid(UUIDUtil.getUUID());
            stuScore.setUid(user.getUid());

            if (stuScore.getIsHasJDT() == 0) {//只有选择题,直接记录总分?
                stuScore.setGotScore(stuScore.getScore());
                stuScore.setIsDone(1);
            }

            insertUserScore(stuScore);

            if (stuScore.getIsHasJDT() != 0) {
                // 批量新增学生简答题答案
    
                if (jdtAnswerList.size() > 0) {
                    List<ErrorExam> eelist = new ArrayList<ErrorExam>();
                    for (Map<String, String> map : jdtAnswerList) {
                        if (map != null) {
                            String jdt_answer = map.get("jdt_answer");
                            String jdt_id = map.get("jdt_id");
    
                            ErrorExam ee = new ErrorExam();
                            ee.setEeid(UUIDUtil.getUUID());
                            ee.setSort(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                            ee.setStuAnswer(jdt_answer);
                            ee.setUsid(stuScore.getUsid());
                            ee.setUtid(jdt_id);
                            eelist.add(ee);
                        }
                    }
                    insertErrorExam(eelist);
                }
            }
            if (stuScore.getUtype() == 4) {// 科目小测验--更新 参加测验人数
                examService.updateLearnCount(stuScore.getVid());
            }
        } catch (Exception e) {
            log.error("后台更新测验成绩失败,{}" , e.getMessage());
           return ResultBean.failed("后台更新测验成绩失败"  );
        }
        return ResultBean.ok();
    }
}
