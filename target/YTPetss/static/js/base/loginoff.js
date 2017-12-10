//如果是登录状态的显示退出登录,如果未登录显示登录
$(window).ready(function () {
    if(!window.localStorage.getItem('token')){
        //未登录
        alert("你没有登录,因此无法进行相关操作");
        $("#loginButton").innerHTML = '登录';
    }
});
//注销登录的函数
function loginoff(to) {
    //清除令牌信息,相当于注销登录
    window.localStorage.clear();
    alert("注销登录");
    window.location.href = to;
}