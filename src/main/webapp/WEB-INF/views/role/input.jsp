<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/system/role.js"></script>

</head>
<body>
<s:debug/>
<s:include value="/WEB-INF/views/common/common_msg.jsp"/>
<s:form name="editForm" namespace="/" action="role_saveOrUpdate" method="post" id="editForm">
    <s:hidden name="role.id"/>
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">角色编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140">角色名称</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.name" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">角色代码</td>
                    <td class="ui_text_lt">
                        <s:textfield name="role.sn" cssClass="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">权限</td>
                    <td class="ui_text_lt">
                        <table>
                            <tr>
                                <td>
                                        <%--左选框--%>
                                    <s:select list="#permissions" listKey="id" listValue="name" multiple="true"
                                              class="ui_multiselect01 permissionAll"/>
                                </td>
                                <td align="center">
                                    <input type="button" id="select" value="-->" class="left2right"/><br/>
                                    <input type="button" id="selectAll" value="==>" class="left2right"/><br/>
                                    <input type="button" id="deselect" value="<--" class="left2right"/><br/>
                                    <input type="button" id="deselectAll" value="<==" class="left2right"/>
                                </td>
                                <td>
                                        <%--右选框:当前角色所拥有的权限列表--%>
                                    <s:select list="role.permissions" listKey="id" listValue="name" multiple="true"
                                              name="role.permissions.id" class="ui_multiselect01 permissionSelected"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                    <%--菜单列表--%>
                <tr>
                    <td class="ui_text_rt" width="140">菜单</td>
                    <td class="ui_text_lt">
                <tr>
                    <table>
                        <td>
                            <s:select list="#menus" listKey="id" listValue="name" multiple="true"
                                      class="ui_multiselect01 menuAll"/>
                        </td>
                        <td align="center">
                            <input type="button" id="mselect" value="-->" class="left2right"/><br/>
                            <input type="button" id="mselectAll" value="==>" class="left2right"/><br/>
                            <input type="button" id="mdeselect" value="<--" class="left2right"/><br/>
                            <input type="button" id="mdeselectAll" value="<==" class="left2right"/>
                        </td>
                        <td>
                            <s:select list="role.menus" listKey="id" listValue="name" multiple="true"
                                      name="role.menus.id" class="ui_multiselect01 menuSelected"/>
                        </td>
                        </tr>
                    </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
</body>
</html>