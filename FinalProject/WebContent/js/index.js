$(function(){
	var oDiv = $("#lunbo");
	console.log($(window).width())
	if( $(window).width() <= 1366 ){
				var oImg = $("#lunbo #carousel-example-generic .item img");
				oDiv.css("padding-top","60px");
				oDiv.css("overflow","hidden");
				oImg.css("height","100%");
				oImg.css("width","100%");
				window.addEventListener("resize",function(){
					oImg.css("height","100%");
					oImg.css("width","100%");
				})
	}
	if( $(window).width() > 1366 ){
				var oImg = $("#lunbo #carousel-example-generic .item img");
				// oDiv.css("padding-top","130px");
				oImg.height(oDiv.height());
				oImg.width(oDiv.width());
				window.addEventListener("resize",function(){
					oImg.height(oDiv.height());
					oImg.width(oDiv.width());
				})
	}
//	二维码显示隐藏
// 	$("#header .contact span").hover(function(){
// 		$("#header .contact span .code").show();
// 	},function(){
// 		$("#header .contact span .code").hide();
// 	})
//	滚动条滑动
	$(window).on('scroll',function(){
		var scrollT = document.body.scrollTop || document.documentElement.scrollTop;
		var $top = $("#main ul").offset().top;
		var $span = $("#main ul span");
		var $top2 = $("#main .picture").offset().top;
		var $span2 = $("#main .picture span");
		if( scrollT > $top - 300){
			$span.animate({"right":"0"},500)
		}
		if( scrollT > $top2 - 300){
			$span2.animate({"right":"0"},500)
		}
	})
// //	鼠标移上li
// 	$("#main ol li .mengban").hover(function(){
// 		$(this).css({"background":"rgba(0,0,0,.1)","color":"#fff"})
// 	},function(){
// 		$(this).css({"background":"","color":"#fff"})
// 	})
// //	点击严正声明按钮
// 	$("#about button").click(function(){
// 		window.location.href = "http://www.kawasaki-motors.cn/kawasaki/solemn_statement.jsp"
// 	})
})
