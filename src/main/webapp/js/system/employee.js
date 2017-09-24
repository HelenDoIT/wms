/*数据校验 jquery-validate*/
jQuery(function(){
    jQuery("#editForm").validate({
        rules:{
            'employee.name':{
                required:true,
                minlength:2
            },
            'employee.password':{
                required:true,
                minlength:3
            },
            'repassword':{
                required:true,
                minlength:3,
                equalTo:"#password"  // #password是选择器
            },
            'employee.email':{
                email:true
            },
            'employee.age':{
                required:true,
                range:[18,60],
                digits:true
            }
        },
        messages:{
            'employee.name': {
                required: "用户名不能为空",
                minlength: "长度不能小于2"
            },
            'employee.password':{
                required:"密码不能为空",
                minlength:"长度不能小于3"
            },
            'repassword':{
                required:"验证密码不能为空",
                minlength:"长度不能小于3",
                equalTo:"两次密码不一致"
            },
            'employee.email':{
                email:"必须符合email格式"
            },
            'employee.age':{
                required:"年龄不能为空",
                range:"年龄必须在18到60岁之间",
                digits:"年龄必须为整数"
            }
        }
    });
    //======角色列表=====
    //全选
    $("#selectAll").click(function(){
        $(".roleAll option").appendTo($(".roleSelected"));
    });
    //全取消
    $("#deselectAll").click(function () {
        $(".roleSelected option").appendTo($(".roleAll"));
    })
    //选中移动
    $("#select").click(function(){
        $(".roleAll option:selected").appendTo($(".roleSelected"));
    })
    $("#deselect").click(function () {
        $(".roleSelected option:selected").appendTo($(".roleAll"));
    })
    //给表单提交绑定响应处理函数
    $("#editForm").submit(function(){
        $(".roleSelected option").prop("selected",true);
    })
    //=====员工角色去重====
    var roles = $(".roleSelected option");
    var selectedIds = $.map(roles,function(item,index){
        //console.log($(item).val());
        return $(item).val();
    });
    $.each($(".roleAll option"),function(index,item){
        if($.inArray($(item).val(),selectedIds)!=-1){
            $(item).remove();
        }
    })
});