
/*
* created by qkmc@outlook.com
*
* */
var urlprefix = $("#contextPath")[0].value;
var url =  urlprefix + "/vip/query";

//页面加载完成一次,刷新数据
$(window).ready(function () {
    refreshVip(url);

    //以下是删除用户按钮 修改客户按钮 查询宠物按钮 的开与闭
    $('#viptable').on('check.bs.table',function (row,elem) {
        //先打开按钮
        openBtn();

        //then do someting here
        //修改update modal 中的数据,以便修改的时候更改
        var rows = $('#viptable').bootstrapTable('getSelections');
        var uelems = $("#vipupdateinfo").children('input');
        $(uelems[1]).val($(rows).last()[0].customname);
        $(uelems[2]).val($(rows).last()[0].customphone);
        $(uelems[0]).val($(rows).last()[0].customid);
    });


    $('#viptable').on('uncheck.bs.table', function (row,elem) {
        closeBtn();
    });
}
);


//监听数据刷新,当点击刷新时,刷新数据
$('#viptable').onRefresh = function () {
    refreshVip(url);
}
//在这里定义加载数据到bootstraptable
function refreshVip(url_refresh) {
    //直接发送ajax获取数据.
    $.ajax({
        type:"get",
        url:url_refresh,
        data:{token:window.localStorage.getItem("token") },
        async:true,
        dataType:'json',
        success:function(data){
           // alert("调试信息:读取数据成功" + data);
            $('#viptable').bootstrapTable('load',data);
    }
});

}

//下面是modal的函数了.
function addVip(url_add){
    //增加用户
    //首先获取增加用户modal中的值
    //使用ajax进行增加用户操作
    //伪数组
    var customname = $('#vipname')[0].value;
    var phoneNumber = $('#vipphone')[0].value;
    var petType = $('#petType')[0].value;
    var petname = $('#petname')[0].value;
    var balance = $('#balance')[0].value;
    // alert(name + phoneNumber + petType + petname + balance);

    $.ajax({
        url:url_add,
        type:'post',
        dataType:'json',
        data:{
            customname: customname,
            phoneNumber: phoneNumber,
            pettype: petType,
            petname: petname,
            balance: balance,
            token: window.localStorage.getItem("token")
        },
        success:function (data) {
            if(data.result === 'false')
                alert("添加用户失败,可能是用户已存在,也可能是服务器不稳定!");
            else
                alert("添加用户成功");
            //alert(JSON.stringify(data));
            //重新加载数据
            refreshVip(url);
            //关闭modal
            $("#addvip").modal('hide');
        }
    });
}
//删除一个vip用户
function deletevip() {
    //获取选中元素的id值
    var rows = $('#viptable').bootstrapTable('getSelections');
    var customid = $(rows).last()[0].customid;

    //使用ajax删除
    $.ajax({
        url:urlprefix + "/vip/delete/" + customid,
        type:'post',
        dataType:'json',
        data:{
            token:window.localStorage.getItem("token")
        },
        async:'true',
        success:function (data) {
            if(data.result === 'true'){
                alert("删除成功!");

            }else if(data.result === 'false'){
                alert("删除失败!");
            }
            closeBtn();
            $("#deletevip").modal('hide');
            refreshVip(url);
        }
    });
}
//更新用户的基本信息
function updatevippriminfo() {
    //获取选中元素的id值和name phone等值
    var updateinfo = $('#vipupdateinfo').children('input');
    var customid = updateinfo[0].value;
    var customname =updateinfo[1].value;
    var phoneNumber = updateinfo[2].value;

    //发送修改请求
    $.ajax({
        url:urlprefix + "/vip/modify/" + customid,
        type:'post',
        dataType:'json',
        data:{
            name: customname,
            phoneNumber: phoneNumber,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (data) {
            if(data.result === 'true'){
                alert("修改成功");
            }else{
                alert("修改失败");
            }
            closeBtn();
            $("#updatevip").modal('hide');
            refreshVip(url);
        }
    });

}
//找主人的宠物...
function findpetbycustomid() {
    //获取选中元素的id值和name phone等值
    var rows = $('#viptable').bootstrapTable('getSelections');
    var customid = $(rows).last()[0].customid;
    alert("这个功能复杂,再说吧!");
    //找宠物

}

function closeBtn() {
    $("button[name='deleteCustom']").attr('disabled', 'disabled');
    $("button[name='updateCustom']").attr('disabled', 'disabled');
    $("button[name='findPet']").attr('disabled', 'disabled');
}
function openBtn() {
    $("button[name='deleteCustom']").removeAttr('disabled');
    $("button[name='updateCustom']").removeAttr('disabled');
    $("button[name='findPet']").removeAttr('disabled');
}