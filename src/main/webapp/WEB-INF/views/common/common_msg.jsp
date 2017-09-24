<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">
    $(function(){
        <s:if test="hasErrors()">
            var msg ="<s:property value="actionErrors[0]"/>";
            showDialog("",msg);
            //return;
        </s:if>
        <s:if test="hasActionMessages()">
            var msg ="<s:property value="actionMessages[0]"/>";
            showDialog("",msg);
        </s:if>
    })
</script>

