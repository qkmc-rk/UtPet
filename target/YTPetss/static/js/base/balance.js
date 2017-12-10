
var url_prefix = $("#contextPath")[0].value;

$(window).ready(function () {
    //首先更新一波数据啊
    refreshBlc();
    //选中某条数据
    $('#balancetable').on('check.bs.table',function (row,elem) {
        openBtn();
        //其它操作 比如刷新一下modal中的值
        var rows = $('#balancetable').bootstrapTable('getSelections');
        var uelems = $("#blcinfo").children('input');
        $(uelems[0]).val($(rows).last()[0].customId);
        $(uelems[1]).val($(rows).last()[0].balance);
    });

    $('#balancetable').on('uncheck.bs.table', function (row,elem) {
        closeBtn();
    });

});


//在这里定义加载数据到bootstraptable
function refreshBlc() {
    //直接发送ajax获取数据.
    $.ajax({
        type: "get",
        url: url_prefix + "/vip/balance/query",
        data: {token: window.localStorage.getItem("token")},
        async: true,
        dataType: 'json',
        success: function (data) {
            // alert("调试信息:读取数据成功" + data);
            $('#balancetable').bootstrapTable('load', data);
        }
    });
}

//更新用户的基本信息
function updateblc() {
    //获取选中元素的id值和name phone等值
    var updateinfo = $('#blcinfo').children('input');
    var customid = updateinfo[0].value;
    var balance = updateinfo[1].value;

    //发送修改请求
    $.ajax({
        url:url_prefix + "/vip/balance/modify/" + customid,
        type:'post',
        dataType:'json',
        data:{
            balance: balance,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (data) {
            if(data.result === 'true'){
                alert("修改余额成功");
            }else{
                alert("修改余额失败");
            }
            closeBtn();
            $("#updateBlc").modal('hide');
            refreshBlc();
        }
    });

}

function closeBtn() {
    $("button[name='updateBalance']").attr('disabled', 'disabled');
    //$("button[name='balanceZero']").attr('disabled', 'disabled');
}
function openBtn() {
    $("button[name='updateBalance']").removeAttr('disabled');
    //$("button[name='balanceZero']").removeAttr('disabled');
}