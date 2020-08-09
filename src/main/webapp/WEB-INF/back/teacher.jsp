<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/back/pintuer.css">
    <link rel="stylesheet" href="css/back/admin.css">
    <script src="libs/jquery.js"></script>
    <script src="libs/pintuer.js"></script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
    <div class="padding border-bottom">
        <button type="button" class="button border-yellow"><span class="icon-plus-square-o"></span> 添加内容</button>
    </div>
    <table class="table table-hover text-center">
        <tr>
            <th>序号</th> 
            <th>头像</th>
            <th>姓名</th>
            <th>科目</th>
            <th>简介</th>
            <th>未批改试卷数</th>
            <th width="31%">操作</th>
        </tr>
        <c:forEach items="${teacher}" var="t" varStatus="tt">
            <tr id="trss_${t.tid}" >
                <td>${tt.index+1}</td>
                <td><img src="${t.timgUrl}" alt="" width="160" height="130"/></td>
                <td id="tname_${t.tid}">${t.tname}</td>
                <td>${t.subject}</td>
                <td>${t.tintro}</td>
                <td>${t.nums}</td>
                <td>
                    <div class="button-group">
                        <input type="hidden" class="tech" value="${t.tid}">
                        <input type="hidden" value="${t.sort}" id="hide_sort_${t.tid}">
                        <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
                        <a class="button border-main border-up2"><span class="icon-sort-up"></span> 上移</a>
                        <a class="button border-main border-up3"><span class="icon-sort-down"></span> 下移</a>
                        <a class="button border-red"><span class="icon-trash-o"></span> 删除</a>
                            <%--  <c:if test="${t.nums==0}">
                                   <a class="button border-main" style="background:#EEEEEE;"><span class="icon-edit"></span>批改试卷</a>
                             </c:if>
                              <c:if test="${t.nums>0}">
                               <a class="button border-main border-up4" ><span class="icon-edit"></span> 批改试卷</a>
                              </c:if> --%>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        $('.border-up').on("click", function () {
            var id = $(this).siblings(".tech").val();
            window.location.href = "${pageContext.request.contextPath}/back/teacherUp.html?id=" + id;
        });
        $('.border-red').on('click', function () {
            var id = $(this).siblings(".tech").val();
            <%--window.location.href = "${pageContext.request.contextPath}/back-authc/deleteTeacher?id=" + id;--%>
            $.ajax({
                url:'back-authc/deleteTeacher',
                type:'post',
                dataType:'json',
                data:{id:id},
                success:function(result){
                    if(result.code == 0) {
                        $("#trss_"+id).attr("hidden","hidden");
                    }else{
                       console.log("删除教师失败");
                    }
                    
                }
            });
            
        });
        $('.border-yellow').on('click', function () {
            window.location.href = "${pageContext.request.contextPath}/back/addTeacher.html";
        });
        $(".border-up2").on("click", function () {// 上移
            var id = $(this).siblings(".tech").val();
            changeSort(0, id);
        });
        $(".border-up3").on("click", function () { // 下移
            var id = $(this).siblings(".tech").val();
            changeSort(1, id);
        });
    });

    //上移/下移调整顺序
    function changeSort(type, id) {
        var cur = $("#trss_" + id);
        var change_obj = type == 0 ? cur.prev() : cur.next();
        var change_tr_id = change_obj.attr("id");
        if (change_tr_id) {
            var cur_sort = $("#hide_sort_" + id).val();//当前的顺序值
            var change_id = change_tr_id.replace("trss_", "");
            var new_sort = $("#hide_sort_" + change_id).val();//交换的顺序值
            //修改库顺序
            $.post("${pageContext.request.contextPath}/back-authc/changeTeacherSort",
                {id1: id, sort1: new_sort, id2: change_id, sort2: cur_sort},
                function (res) {//resultBean

                    if (res.code == "0") {
                        //修改页面顺序值
                        $("#hide_sort_" + change_id).val(cur_sort);
                        $("#hide_sort_" + id).val(new_sort)
                        //调整表格行位置
                        if (type == 0) {
                            change_obj.before(cur);
                        } else {
                            change_obj.after(cur);
                        }
                    } else {
                        alert(res.msg);
                    }
                });
        } else {
            alert(type == 0 ? "已经是第一个，不可再上移！" : "已经是最后一个，不可再下移！");
        }
    }
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>