$(function () {
    //  常规子菜单
    $(".routine-menu").hover(function () {
        $(".routine-sunmenu").show();
    }, function () {
        $(".routine-sunmenu").hide();
    })
    $(".routine-sunmenu").hover(function () {
        $(".routine-sunmenu").show();
    }, function () {
        $(".routine-sunmenu").hide();
    });
    //竞赛菜单
    $(".competition-menu").hover(function () {
        $(".competition-sunmenu").show();
    }, function () {
        $(".competition-sunmenu").hide();
    })
    $(".competition-sunmenu").hover(function () {
        $(".competition-sunmenu").show();
    }, function () {
        $(".competition-sunmenu").hide();
    });
    //小测验
    $(".quiz-menu").hover(function () {
        $(".quiz-sunmenu").show()
    }, function () {
        $(".quiz-sunmenu").hide()
    })
    $(".quiz-sunmenu").hover(function () {
        $(".quiz-sunmenu").show();
    }, function () {
        $(".quiz-sunmenu").hide();
    });
    // 图片banner轮播
    var mySwiper = new Swiper(".banner.swiper-container", {
        paginationClickable: true,
        autoplay: 2000,
        loop: true,
        loopedSlides: 2
    });
    // 小轮播
    var MySwiper = new Swiper(".active.swiper-container", {
        nextButton: '.swiper-button-next',
        prevButton: '.swiper-button-prev',
        autoplay:3600,
        autoplayDisableOnInteraction:false,
        onSlideChangeStart: function (swiper) {
            var _index = swiper.activeIndex;
            var _this = $(".pic li:eq("+_index+")");
            // num = _index;
            $('.pic img').css({'border':'none'});
            $(_this).children().css({'border':'2px solid #4b8a6b'});
        }
    });
    // 点击切换内容
    $(".pic li").on("click", function () {
        var _index = $(this).index()
        // console.log(_index)
        MySwiper.slideTo(_index)
    });
    // 滑过图片显示介绍
    $(".student ul>li").mouseover(function () {
        var ind = $(this).index()
        // console.log(ind)
        $(this).css("border", "3px solid green").siblings().css("border", "0")
        $(".class-pic").eq(ind).show().parents("li").siblings().children(".class-pic").hide()
        //点击显示内容
        $(".trainee").on("click", "li", function () {
            var id = $(this).attr("id");
            var name = $(this).attr("name");
            if(!name){
            	showStu(id);
            }else{
            	showTeacher(id);
            }
            
        })
    });
    $(".student ul>li").mouseleave(function () {
        $(".class-pic").hide()
        $(this).css("border", 0)
    })
});