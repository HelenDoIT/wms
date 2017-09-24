
$(function () {
    //=========修改图标样式==========
    //alert(111);
    //给li元素绑定点击事件
    $("#TabPage2 li").click(function(){

        //获取当前点击元素的索引
        console.log($(this).index());
        //把其他非选中的img恢复到原始状态
        $("#TabPage2 li").each(function(index,item){
            $(item).find("img").prop("src","/images/common/"+(index+1)+".jpg");
            $(item).removeClass("selected");
        });
        //修改li元素中的img元素的src属性
        $(this).find("img").prop("src","/images/common/"+($(this).index()+1)+"_hover.jpg");
        $(this).addClass("selected");
        //修改菜单标题
        $("#nav_module img").prop("src","/images/common/module_"+($(this).index()+1)+".png");
        //点击的时候显示系统对应的菜单
        loadMenu($(this).data("rootmenu"));
    });
});
//==========系统菜单设置============
var setting = {
    //页面加载时,只需要显示根菜单, 根据菜单编码查询根菜单
    async: {
        enable: true,
        url: "/systemMenu_loadMenuBySn.action",
        autoParam: ["sn=qo.parentSn"]
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onClick: function (event,treeId,treeNode) {
            console.log(treeNode);
            if(treeNode.action){
                $("#rightMain").prop("src",treeNode.action+".action");
                //显示系统菜单的层级
                $("#here_area").html("当前位置："+treeNode.getParentNode().name+"&nbsp;>&nbsp;"+treeNode.name);
            }
        }
    }
};
var business=[
    { id:1, pId:0, name:"业务模块",sn:"business",isParent:true}
];

var system =[
    { id:2, pId:0, name:"系统管理模块",sn:"system",isParent:true}
];

var chart=[
    { id:3, pId:0, name:"报表模块",sn:"chart",isParent:true}
];

var zNodes ={
    "business":business,
    "system":system,
    "chart":chart
};
//定义一个函数把菜单数据显示到对应页面元素中
function loadMenu(parentSn){
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[parentSn]);
}

$(function(){
    loadMenu("business");
});


/*//标准JSON数据结构
var setting = {	};

var zNodes =[
    { name:"业务模块",
        children: [
            { name:"品牌管理"},
            { name:"供应商管理"}
        ]},
    { name:"系统管理模块",
        children: [
            { name:"部门管理", open:true},
            { name:"员工管理", open:true},
            { name:"权限管理", open:true},
            { name:"角色管理", open:true},
            { name:"系统管理", open:true},

        ]},
    { name:"报表模块", isParent:true}
];

$(document).ready(function(){
    $.fn.zTree.init($("#dleft_tab1"), setting, zNodes);
});*/


/*//加载当前日期
function loadDate() {
    var time = new Date();
    var myYear = time.getFullYear();
    var myMonth = time.getMonth() + 1;
    var myDay = time.getDate();
    if (myMonth < 10) {
        myMonth = "0" + myMonth;
    }
    document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."
        + myDay;
}

/!**
 * 隐藏或者显示侧边栏
 *
 *!/
function switchSysBar(flag) {
    var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
    if (flag == true) { // flag==true
        left_menu_cnt.show(500, 'linear');
        side.css({
            width: '280px'
        });
        $('#top_nav').css({
            width: '77%',
            left: '304px'
        });
        $('#main').css({
            left: '280px'
        });
    } else {
        if (left_menu_cnt.is(":visible")) {
            left_menu_cnt.hide(10, 'linear');
            side.css({
                width: '60px'
            });
            $('#top_nav').css({
                width: '100%',
                left: '60px',
                'padding-left': '28px'
            });
            $('#main').css({
                left: '60px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_show.png');
        } else {
            left_menu_cnt.show(500, 'linear');
            side.css({
                width: '280px'
            });
            $('#top_nav').css({
                width: '77%',
                left: '304px',
                'padding-left': '0px'
            });
            $('#main').css({
                left: '280px'
            });
            $("#show_hide_btn").find('img').attr('src',
                '/images/common/nav_hide.png');
        }
    }
}

// =====================================
//zTree
var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },callback:{
        onClick:function(event, treeId, treeNode){
           if(treeNode.action){
               $("#rightMain").prop("src",treeNode.action+".action");
               $("#here_area").html("当前位置："+treeNode.name);
           }
        }
    },
    async: {
        enable: true,
        url: "/systemMenu_loadMenusByParentSn.action",
        autoParam: ["sn=qo.parentSn"]
    }
};

var zNodes ={
    business: {id:1,pId:0,name:"业务模块",isParent:true,sn:"business"},
    system:{id:1,pId:0,name:"系统模块",isParent:true,sn:"system"},
    chart:{id:1,pId:0,name:"报表模块",isParent:true,sn:"chart"}

};


function loadMenu(menuName){
 $.fn.zTree.init($("#dleft_tab1"), setting, zNodes[menuName]);
 }
// =====================================
$(function () {
    // 加载日期
    loadDate();
    loadMenu("business");
    // ======================================
    // ======================================
    // 切换菜单按钮样式和标题
    $("#TabPage2 li").click(function () {
        //每次都删除全部样式
        $.each($("#TabPage2 li"), function (index, item) {
            $(item).removeClass("selected").children("img").prop("src", "/images/common/" + ($(item).index() + 1) + ".jpg");
        });
        var index = $(this).index() + 1;//第N个菜单,从0开始
        $(this).addClass("selected");
        $(this).children("img").prop("src", "/images/common/" + index + "_hover.jpg");

        //切换标题
        $("#nav_module").children("img").prop("src","/images/common/module_"+index+".png");
        //====
        loadMenu($(this).data("rootmenu"));
    });

    // ======================================
    // 显示隐藏侧边栏
    $("#show_hide_btn").click(function () {
        switchSysBar();
    });
});*/


