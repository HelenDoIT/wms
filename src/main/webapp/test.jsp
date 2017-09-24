<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript">
        $(function () {
            $.dialog({
                title:"温馨提示",
                icon:"face-smile",
                content:"确定删除?",
                ok:function(){
//                    alert("删除成功");
                    $.dialog({
                        title:"温馨提示",
                        icon:"face-smile",
                        content:"删除成功",
                        ok:function(){}
                    })
                },
                cancel:true
            })
        })
    </script>
</head>

<body>

</body>
</html>
