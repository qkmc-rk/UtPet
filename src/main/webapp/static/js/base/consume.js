var url_prefix = $("#contextPath")[0].value;

$(window).ready(function () {
    //首先更新一波数据啊
    refreshConsume();
    //选中某条数据
    // $('#consumetable').on('check.bs.table',function (row,elem) {
    //     //openBtn();
    //     //其它操作 比如刷新一下modal中的值
    //     var rows = $('#balancetable').bootstrapTable('getSelections');
    //     var uelems = $("#blcinfo").children('input');
    //     $(uelems[0]).val($(rows).last()[0].customId);
    //     $(uelems[1]).val($(rows).last()[0].balance);
    // });
    //
    // $('#consumetable').on('uncheck.bs.table', function (row,elem) {
    //    // closeBtn();
    // });

});

//在这里定义加载数据到bootstraptable
function refreshConsume() {
    //直接发送ajax获取数据.
    $.ajax({
        type: "get",
        url: url_prefix + "/vip/consume/query",
        data: {token: window.localStorage.getItem("token")},
        async: true,
        dataType: 'json',
        success: function (data) {
            //alert("调试信息:读取数据成功" + data);
            $('#consumetable').bootstrapTable('load', data);
        }
    });
}

//更新用户的基本信息
function addConsume() {
    //获取选中元素的id值和name phone等值
    var addinfo = $('#consumeinfo').children('input');
    var customname = addinfo[0].value;
    var paywhat = addinfo[1].value;
    var amount = addinfo[2].value;
    var remark = addinfo[3].value;

    //发送修改请求
    $.ajax({
        url:url_prefix + "/vip/consume/add",
        type:'post',
        dataType:'json',
        data:{
            customname: customname,
            paywhat:paywhat,
            amount:amount,
            mark:remark,
            token: window.localStorage.getItem("token")
        },
        async:true,
        success:function (data) {
            if(data.result === 'true'){
                alert("已消费!");
            }else{
                alert("消费失败!");
            }
            $("#addconsume").modal('hide');
            refreshConsume();
        }
    });

}