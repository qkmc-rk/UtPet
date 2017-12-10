var url_prefix = $("#contextPath")[0].value;



$(window).ready(function () {
    //首先更新一波数据啊
    refreshCharge();
    //选中某条数据

});

//在这里定义加载数据到bootstraptable
function refreshCharge() {
    //直接发送ajax获取数据.
    $.ajax({
        type: "get",
        url: url_prefix + "/vip/recharge/query",
        data: {token: window.localStorage.getItem("token")},
        async: true,
        dataType: 'json',
        success: function (data) {
            // alert("调试信息:读取数据成功" + JSON.stringify(data));
            $('#chargetable').bootstrapTable('load', data);
        }
    });
}

function addcharge() {

    //获取选中元素的id值和name phone等值
    var updateinfo = $('#chargeinfo').children('input');
    var customName = updateinfo[0].value;
    var amount = updateinfo[1].value;

    $.ajax({
        type: "post",
        url: url_prefix + "/vip/recharge/charge",
        data: {
            token: window.localStorage.getItem("token"),
            customName:customName,
            amount:amount
        },
        async: true,
        dataType: 'json',
        success: function (data) {
            //alert("调试信息:读取数据成功" + JSON.stringify(data));
            if(data.result === 'true')
                alert("充值成功!");
            else
                alert("充值失败!")
            $("#chargemodal").modal('hide');
            refreshCharge();
        }
    });
}