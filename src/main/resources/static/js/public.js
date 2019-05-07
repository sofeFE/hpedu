$(function(){
	    function carousel(a,b,c){
        var bn_id = 0;
        var bn_id2 = 1;
        var speed33 = 3000;
        var qhjg = 1;
        var MyMar33;
        $("#"+b+" li").hide();
        $("#"+b+" li").eq(0).fadeIn("slow");
        if ($("#"+b+" li").length > 1) {
            $("#"+c+" li").eq(0).addClass("nuw");
            function Marquee33() {
                bn_id2 = bn_id + 1;
                if (bn_id2 > $("#"+b+" li").length - 1) {
                    bn_id2 = 0;
                }
                $("#"+b+" li").eq(bn_id).css("z-index", "2");
                $("#"+b+" li").eq(bn_id).fadeOut("slow");
                $("#"+b+" li").eq(bn_id2).fadeIn("slow");
                $("#"+c+" li").removeClass("nuw");
                $("#"+c+" li").eq(bn_id2).addClass("nuw");
                bn_id = bn_id2;
            };
            MyMar33 = setInterval(Marquee33, speed33);
            $("#"+c+" li").click(function() {
                var bn_id3 = $(this).index();
                if (bn_id3 != bn_id && qhjg == 1) {
                    qhjg = 0;
                    $("#"+b+" li").eq(bn_id).css("z-index", "2");
                    $("#"+b+" li").eq(bn_id3).css("z-index", "1");
                    $("#"+b+" li").eq(bn_id).fadeOut("slow", function() {
                        qhjg = 1;
                    });
                    $("#"+b+" li").eq(bn_id3).fadeIn("slow", function() {
                        qhjg = 1;
                    });
                    $("#"+c+" li").removeClass("nuw");
                    $("#"+c+" li").eq(bn_id3).addClass("nuw");
                    bn_id = bn_id3;
                }
            })
            $("#"+c).hover(function() {
                clearInterval(MyMar33);
            }, function() {
                MyMar33 = setInterval(Marquee33, speed33);
            })
        } else {
            $("#"+c).hide();
        }
    };
    var carousel1 = "carousel1";
    var carousel_img1 = "carousel_img1";
    var carousel_but1 = "carousel_but1";
    carousel(carousel1,carousel_img1,carousel_but1);
    var carousel2 = "carousel2";
    var carousel_img2 = "carousel_img2";
    var carousel_but2 = "carousel_but2";
    carousel(carousel2,carousel_img2,carousel_but2);
    var carousel3 = "carousel3";
    var carousel_img3 = "carousel_img3";
    var carousel_but3 = "carousel_but3";
    carousel(carousel3,carousel_img3,carousel_but3);
    
//  常规子菜单
    $(".routine-menu").hover(function(){
    	$(".routine-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".routine-sunmenu").show();
    },function(){
    	$(".routine-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".routine-sunmenu").hide();
    })
    $(".routine-sunmenu").hover(function(){
    	$(".routine-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".routine-sunmenu").show();
    },function(){
    	$(".routine-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".routine-sunmenu").hide();
    });
    
    //竞赛菜单
    $(".competition-menu").hover(function(){
    	$(".competition-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".competition-sunmenu").show();
    },function(){
    	$(".competition-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".competition-sunmenu").hide();
    })
    
    $(".competition-sunmenu").hover(function(){
    	$(".competition-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".competition-sunmenu").show();
    },function(){
    	$(".competition-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".competition-sunmenu").hide();
    });
    
    //小测验
    $(".quiz-menu").hover(function(){
    	$(".quiz-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".quiz-sunmenu").show();
    },function(){
    	$(".quiz-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".quiz-sunmenu").hide();
    })
    
    $(".quiz-sunmenu").hover(function(){
    	$(".quiz-menu").css({
    		"color":"#5cb85c",
    		"border-bottom":"3px solid #5cb85c",
    		"background" : "rgba(57, 132, 57, 0.18)"
    	});
    	$(".quiz-sunmenu").show();
    },function(){
    	$(".quiz-menu").css({
    		"color":"#000",
    		"border-bottom":"3px solid rgba(0,0,0,0)",
    		"background" : "#fff"
    	});
    	$(".quiz-sunmenu").hide();
    })
    
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
