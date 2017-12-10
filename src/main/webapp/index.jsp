<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <title>加载中</title>
 <meta http-equiv="content-type" content="text/html; charset=utf-8" />
 <style type="text/css">
  .spinner {
   margin: 300px auto 0 auto;
   width: 50px;
   height: 60px;
   text-align: center;
   font-size: 10px;
  }

  .spinner > div {
   background-color: #FFFFFF;
   height: 100%;
   width: 6px;
   display: inline-block;

   -webkit-animation: stretchdelay 1.2s infinite ease-in-out;
   animation: stretchdelay 1.2s infinite ease-in-out;
  }

  .spinner .rect2 {
   -webkit-animation-delay: -1.1s;
   animation-delay: -1.1s;
  }

  .spinner .rect3 {
   -webkit-animation-delay: -1.0s;
   animation-delay: -1.0s;
  }

  .spinner .rect4 {
   -webkit-animation-delay: -0.9s;
   animation-delay: -0.9s;
  }

  .spinner .rect5 {
   -webkit-animation-delay: -0.8s;
   animation-delay: -0.8s;
  }

  @-webkit-keyframes stretchdelay {
   0%, 40%, 100% { -webkit-transform: scaleY(0.4) }
   20% { -webkit-transform: scaleY(1.0) }
  }

  @keyframes stretchdelay {
   0%, 40%, 100% {
    transform: scaleY(0.4);
    -webkit-transform: scaleY(0.4);
   }  20% {
       transform: scaleY(1.0);
       -webkit-transform: scaleY(1.0);
      }
  }
  .spinner2{
   margin: 10px 0 0 0;
  }
  .spinner2 > h1 {

  }
 </style>
</head>
<body style="background-color: deepskyblue;">
<div class="spinner">
 <div class="rect1"></div>
 <div class="rect2"></div>
 <div class="rect3"></div>
 <div class="rect4"></div>
 <div class="rect5"></div>
</div>
<div class="spinner2" style="color: white; text-align: center;">
 <h1>loading...</h1>
</div>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript">
    $(window).ready(function(){
        //判断是否支持localstorage
        if(!window.localStorage){
            alert("系统不支持你的浏览器版本,请更新或者换到chrome浏览器");
        }else{
            //alert("系统支持localStorage");
            var storage = window.localStorage;
            //判断是否存在token,如果没有跳转到login页面
            if(!storage.getItem("token")){
                //没有token,说明没有登录,或者已注销
                $.ajax({
                    type:'get',
                    url:'<%= request.getContextPath() %>/admin/tologin',
                    dataType:'',
                    async:true ,
                    success:function (data) {
                       //alert("请求成功");//alert(data);
                        window.location.href = "<%= request.getContextPath() %>/admin/tologin";
                    }
                });
            }else{
                //如果token存在,跳转到个人中心.
                //window.location("/admin/tologin");
                window.location.href = "<%= request.getContextPath() %>/admin/index";
            }

        }

    });
</script>
</body>
</html>
