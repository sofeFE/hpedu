/**
 * 注册或找回密码
 */
var sendMobile;
	var sendMsgBtn;
	//发送短信
	function sendYZM(type){
		var phone = $(".phone").val();
		if(checkMobile(phone)){
			if(type==0){//注册判断手机号是否重复
				$.post(path_ctx+"/user/ckPhoneIsExsits",{phone:phone},function(res){
					if(res=="ok"){
						getShortMsg('ycode',phone,type);
					}else if(res=="exsits"){
						alert("该手机号已注册！");
					}else{
						alert("网站错误，请联系管理员！");
					}
				});
			}else{//找回密码
				getShortMsg('ycode',phone,type);
			}
		}
	}
	//获取短信验证码
	function getShortMsg(btnId,mobile,type){
		//设置按钮不可用(移除click事件)
		$("#"+btnId).removeAttr("onclick");
		$("#"+btnId).css("background","gray");//设置按钮颜色变灰
		 sendMsgBtn=btnId;
		 sendMobile=mobile;
		    //设置定时器（60秒）
	        setMyTimeout(); 
	        //调用短信接口，发送短信
	        $.ajax({
				  type: 'POST',
				  url: path_ctx+"/reg/ycode",
				  data: {tel:mobile,type:type},
				  success: function(res){
					  if(res==1){
						  $("#span_ok").html("发送验证码成功,请您到手机["+mobile+"]查看");
						  setTimeout(function(){
							  $("#span_ok").html("");
						  },5000);
					  }else{
						  alert("短信验证码发送失败，请联系客服！");
					  }
					 
				  }
		  }); 
	}
	//设置定时器
	var count = 60;//60秒
	function setMyTimeout(){
		  if (count > 0) {
		        setTimeout(setMyTimeout, 1000);
		        $("#"+sendMsgBtn).html((count--) + "秒之后点击获取");
		  } else {
		    	count = 60;
		    	//设置按钮可用(重新绑定click事件)
		    	$("#"+sendMsgBtn).click(function(){
		    		getShortMsg(sendMsgBtn,sendMobile);
		    	});
		    	$("#"+sendMsgBtn).css("background","#5CB85C");
		        $("#"+sendMsgBtn).html("获取验证码");
		       
		    }
	}
	
	//手机号验证
	function checkMobile(phone){
		var len=phone.length;
		var flag=false;
		if(len>0 && !phone.match(/^(?:13\d|14\d|15\d|16\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/)){
			alert("手机号码格式不正确！");
		}else if(len==0){
			alert("手机号不能为空！");
		}else{
			flag=true;
		}
		return flag;
	}
	//密码验证
	function checkPwd(){
		var flag=false;
		var a = $('.password').val();
        if(a.length==0){
        	alert("密码不能为空！");
        }else{
        	var b = $('.password2').val();
        	if (a != b) {
      		   alert("两次密码不一致");
      	    }else{
      	    	flag=true;
      	    }
        }
		return flag;
	}
	//手机验证码验证
	function checkPwdYzCode(type){
		var flag=false;
		var a = $('.yanzhengma').val();
        if(a.length==0){
        	alert("验证码不能为空！");
        }else{
        	var phone = $(".phone").val();
        	$.ajax({
        		type:"post",
        		url:path_ctx+"/user/yzCode",
        		data:{code:a,phone:phone,type:type},
        		async:false,
        		success:function(res){
        			if(res=="ok"){
        				flag=true;
        			}else{
        				alert(res);
        			}
        		}
        	});
        }
		return flag;
	}
	//登录验证
	function chekLogin(type){
		var count22=0;
		var phone = $(".phone").val();
		if(checkMobile(phone)){
			if(checkPwdYzCode(type)){
				if(!checkPwd()){
					++count22;
				}
			}else{
				++count22;
			}
		}else{
			++count22;
		}
		var flag=count22==0?true:false;
		if(flag && type==0){//找回密码不需要邀请码 ?? 所以添加 type==0 验证,当注册时 需要验证邀请码
			var yqCode=$("#usedCode").val();
			if(yqCode!=""){
				$.ajax({
					url:path_ctx+"/user/ckYQCodeExsits",
					type:"post",
					data:{yqCode:yqCode},
					async:false,
					success:function(res){
						if(res=="ok"){
							subRegUser();
						}else if(res=="notExsits"){
							alert("邀请码不存在，请检查是否输入错误！");
							flag=false;
						}else{
							alert("网站出错，请联系管理员！");
							flag=false;
						}
					}
				});
			}else{
				subRegUser();
			}
		}
		return flag;
	}
	function subRegUser(){
		$("#rregbutton").val("提交中...");
		$("#rregbutton").attr("disabled","disabled");
		$("#rregbutton").css("background","gray");
	}