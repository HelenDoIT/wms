//避免提交数组的时候有 中括号[]
jQuery.ajaxSettings.traditional = true;

$(function () {
    //新增
    $(".btn_input").click(function () {
        //window.location.href="employee_input.action";
        window.location.href=$(this).data("inputurl");
    });
    //分页
    $(".btn_page").click(function(){
        //获取点击按钮所指向的页数
        var pageNo = $(this).data("page") || $("input[name='qo.currentPage']").val() ;
        //把页数设置给 currentPage
        $("input[name='qo.currentPage']").val(pageNo);
        $("#searchForm").submit();
    });
    //页面容量
    $(":input[name='qo.pageSize']").change(function(){
        $("input[name='qo.currentPage']").val(1);
        $("#searchForm").submit();
    });
    //删除操作
    $(".btn_audit").click(function () {
        var url = $(this).data("url");
        //console.log(url);
        showDialog("","确认要审核?",function () {
            //发送ajax请求
            $.get(url, function (data) {
                showDialog("",data,function(){
                    window.location.reload();
                });
            });
        },true);
    });
    //审核操作
    $(".btn_delete").click(function () {
        var url = $(this).data("url");
        //console.log(url);
        showDialog("","确认删除?",function () {
            //发送ajax请求
            $.get(url, function (data) {
                showDialog("",data,function(){
                    window.location.reload();
                });
            });
        },true);
    });

    //批量删除
    //页面加载时让复选框都是未选中的状态
    $(".acb").prop("checked",false);
    $(".btn_batchDelete").click(function () {
            //获取删除的url
            var deleteUrl = $(this).data("deleteurl");
            //获取选中的员工id集合
            console.log($(".acb:checked"));
            //如果没有选中任何选择框
            if($(".acb:checked").length == 0){
                showDialog("","请选择要删除的项",true);
                return;
            }
            //弹出确认框
        showDialog("","确定要批量删除?",function(){
            var selectedIds = $.map($(".acb:checked"),function(item, index){
                //item: 选中的元素
                //console.log(item);
                return $(item).data("oid");
            },true);
            //发送ajax请求去删除数据
            $.get(deleteUrl,{'ids':selectedIds}, function (data) {
                showDialog("",data,function () {
                    //重新加载页面
                    window.location.reload();
                });
            });
        },true);
    });
    //表头复选框的值改变事件
    $("#all").change(function () {
        $(".acb").prop("checked",$(this).prop("checked"));
    });
})
//=========抽取通用的显示对话框函数========
function showDialog(title,content,ok,cancel){
    $.dialog({
        title:title||"温馨提示",
        content:content||"",
        ok:ok||true,
        icon:"face-smile",
        cancel:cancel||false
    });
}