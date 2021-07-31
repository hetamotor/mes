<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>人事管理系统 ——后台管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="${ctx}/css/css.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/js/ligerUI/skins/Aqua/css/ligerui-dialog.css"/>
    <link href="${ctx}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script src="${ctx}/js/ligerUI/js/core/base.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
    <script src="${ctx}/js/ligerUI/js/plugins/ligerResizable.jss" type="text/javascript"></script>
    <link href="${ctx}/css/pager.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript">
        $(function () {
            /** 表单提交的校验 */
            $("#noticeForm").submit(function () {
                var title = $("#title");
                var content = $("#content");
                var msg = "";
                if ($.trim(pcs.val()) == "") {
                    msg = "零件数量不能为空！";
                    title.focus();
                } else if ($.trim(content.val()).length <= 2) {
                    msg = "请检查供应商批次！";
                    title.focus();
                } else if ($.trim(content.val()) == "") {
                    msg = "供应商批次不能为空！";
                    content.focus();
                }
                if (msg != "") {
                    $.ligerDialog.error(msg);
                    return false;
                } else {
                    return true;
                }
                $("#noticeForm").submit();
            });
        });
    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td height="10"></td>
    </tr>
    <tr>
        <td width="15" height="32"><img src="${ctx }/images/main_locleft.gif" width="15" height="32"></td>
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：零件追溯 &gt; 修改投料信息</td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<!-- 请求异常错误  -->
<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form id="noticeForm" name="noticeForm" action="${ctx }/notice/updateNotice" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <input type="hidden" name="id" value="${notice.id }">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd">工单号（W + 8位数字）</td>
                        <td class="font3 fftd"><input type="text" name="workorder" size="30" id="workorder"
                                                      value="${notice.workorder }"/></td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd" width="15%">原材料</td>
                        <td class="font3 fftd">
                            <table width="60%">
                                <tr>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="外壳"
                                               <c:if test="${notice.title eq '外壳'}">checked</c:if>/> 外壳
                                    </td>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="PIN Base"
                                               <c:if test="${notice.title eq 'PIN Base'}">checked</c:if>/> PIN Base
                                    </td>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="NTC"
                                               <c:if test="${notice.title eq 'NTC'}">checked</c:if>/> NTC
                                    </td>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="NTC外支架"
                                               <c:if test="${notice.title eq 'NTC外支架'}">checked</c:if>/> NTC外支架
                                    </td>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="NTC内支架"
                                               <c:if test="${notice.title eq 'NTC内支架'}">checked</c:if>/> NTC内支架
                                    </td>
                                    <td class="font3 fftd">
                                        <input name="title" type="radio" value="分流器"
                                               <c:if test="${notice.title eq '分流器'}">checked</c:if>/> 分流器
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">数量 （只能输入数字）</td>
                        <td class="font3 fftd">
                            <input type="text" name="pcs" size="10" id="pcs" value="${notice.pcs }"
                                   oninput="value=value.replace(/[^\d]/g,'')"/></td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>
                    <tr>
                        <td class="font3 fftd">
                            供应商批次
                        </td>
                        <td class="font3 fftd"><input type="text" name="content" size="30" id="content"
                                                      value="${notice.content }"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            投料时间
                        </td>
                        <td class="font3 fftd">
                            <f:formatDate value="${notice.feedtime}"
                                          type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">
                            &nbsp;
                        </td>
                        <td class="font3 fftd">
                            <input type="submit" value="修改" style="width:80px">
                            <input type="reset" value="重置" style="width:80px">
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>

                </table>
            </form>
        </td>
    </tr>
</table>
<div style="height:10px;"></div>
</body>
</html>