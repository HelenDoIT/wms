//加载权限操作
//删除操作
$(function () {
    $(".btn_reload").click(function () {
        var url = $(this).data("url");
        //console.log(url);
        showDialog("","亲,重新加载权限可能需要耗费很长的时间,你确定加载吗?",function () {
            //发送ajax请求
            $.get(url, function (data) {
                showDialog("",data,function(){
                    window.location.reload();
                });
            });
        },true);
    });
})
