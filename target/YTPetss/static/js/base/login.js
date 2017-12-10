function ajaxlogin (url,to) {
    //首先获取密码
    var password = $('#password')[0].value;
    //alert(password);
    //点击按钮时发送ajax请求
    $.ajax({
        type:"POST",
        url:url,
        async:true,
        data:{ password:password },
        dataType: 'json',
        success: function(data){
            if( data.result === 'true' ){
                window.localStorage.setItem("token",password);
                window.location.href = to;
            }else{
                alert("密码输入错误...");
            }
    }
});
}