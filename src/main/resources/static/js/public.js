$(function(){

    //点击返回顶部
	function gotoTop(min_height){ 
		$("#gotoTop").click(//定义返回顶部点击向上滚动的动画 
			function(){$('html,body').animate({scrollTop:0},700); 
		}); 
		//获取页面的最小高度，无传入值则默认为600像素 
		min_height ? min_height = min_height : min_height = 600; 
		min_height = 0;
		//为窗口的scroll事件绑定处理函数 
		$(window).scroll(function(){ 
			//获取窗口的滚动条的垂直位置 
			var s = $(window).scrollTop(); 
			//当窗口的滚动条的垂直位置大于页面的最小高度时，让返回顶部元素渐现，否则渐隐 
			if( s > min_height){ 
			$("#gotoTop").fadeIn(100); 
			}else{ 
			$("#gotoTop").fadeOut(200); 
			}; 
		}); 
	}; 
	gotoTop(); 
})
