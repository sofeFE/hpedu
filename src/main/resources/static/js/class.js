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
    $(".competition-menu").hover(
        function () {
            $(".competition-sunmenu").show();
        }, 
        function () {
            $(".competition-sunmenu").hide();
        }
    )
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
    
    //个人信息悬浮框
    $(".logo").hover(function () {
        $(".userinfo-login").show()
    }, function () {
        $(".userinfo-login").hide()
    })
    $(".userinfo-login").hover(function () {
        $(".userinfo-login").show();
    }, function () {
        $(".userinfo-login").hide();
    });
    
    
    
    
});