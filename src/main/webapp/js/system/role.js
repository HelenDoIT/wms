$(function () {
    //全选
    $("#selectAll").click(function(){
        $(".permissionAll option").appendTo($(".permissionSelected"));
    });
    //全取消
    $("#deselectAll").click(function () {
        $(".permissionSelected option").appendTo($(".permissionAll"));
    })
    //选中移动
    $("#select").click(function(){
        $(".permissionAll option:selected").appendTo($(".permissionSelected"));
    })
    $("#deselect").click(function () {
        $(".permissionSelected option:selected").appendTo($(".permissionAll"));
    })
    //给表单提交绑定响应处理函数
    $("#editForm").submit(function(){
        $(".permissionSelected option").prop("selected",true);
    })
    //==========角色权限去重: 编辑界面 角色拥有的权限不应该出现在权限列表中======
    //获取角色拥有的权限元素,转换成数组
    //遍历数组, 如果权限列表中拥有该权限,则删除
    var rolePermissions = $(".permissionSelected option");
    var selectedIds = $.map(rolePermissions,function(item,index){
        //console.log($(item).val());
        return $(item).val();
    });
    $.each($(".permissionAll option"),function(index,item){
        if($.inArray($(item).val(),selectedIds)!=-1){
            $(item).remove();
        }
    })
    //================菜单列表============================
    //全选
    $("#mselectAll").click(function(){
        $(".menuAll option").appendTo($(".menuSelected"));
    });
    //全取消
    $("#mdeselectAll").click(function () {
        $(".menuSelected option").appendTo($(".menuAll"));
    })
    //选中移动
    $("#mselect").click(function(){
        $(".menuAll option:selected").appendTo($(".menuSelected"));
    })
    $("#mdeselect").click(function () {
        $(".menuSelected option:selected").appendTo($(".menuAll"));
    })
    //给表单提交绑定响应处理函数
    $("#editForm").submit(function(){
        $(".menuSelected option").prop("selected",true);
    })
    //==========角色菜单去重: 编辑界面 角色拥有的权限不应该出现在权限列表中======
    //获取角色拥有的权限元素,转换成数组
    //遍历数组, 如果权限列表中拥有该权限,则删除
    var rolePermissions = $(".menuSelected option");
    var selectedIds = $.map(rolePermissions,function(item,index){
        //console.log($(item).val());
        return $(item).val();
    });
    $.each($(".menuAll option"),function(index,item){
        if($.inArray($(item).val(),selectedIds)!=-1){
            $(item).remove();
        }
    })
})