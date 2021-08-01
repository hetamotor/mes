<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>BOM系统 ——查看零件</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>
    <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon"/>
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
</head>
<body>
<table width="100%" height="90%" border="0" cellpadding="5" cellspacing="0" class="main_tabbor">
    <tr>
        <td colspan="2" align="left"><font size="12">查看零件${part.virtualPartnumber}</font></td>
    </tr>
    <tr>
        <td valign="top">
            <table width="100%" border="1" cellpadding="5" cellspacing="0"
                   style="border:#c2c6cc 1px solid; border-collapse:collapse;">
                <tr>
                    <td class="font3 fftd" width="100">生产结果</td>
                    <td class="font3 fftd">${part.result}</td>
                </tr>
                <tr>
                    <td class="font3 fftd">零件二维码</td>
                    <td class="font3 fftd">${part.printCode}</td>
                </tr>
                <tr>
                    <td class="font3 fftd">分流器二维码</td>
                    <td class="font3 fftd">${part.shuntCode}</td>
                </tr>
                <tr>
                    <td class="font3 fftd">R25</td>
                    <td class="font3 fftd">
                        <f:formatNumber value="${part.r25}" type="number"
                                        pattern="######.0000"/></td>
                </tr>
                <tr>
                    <td class="font3 fftd">R16</td>
                    <td class="font3 fftd">
                        <f:formatNumber value="${part.r16}" type="number"
                                        pattern="######.0000"/></td>
                </tr>
                <tr>
                    <td class="font3 fftd">Rntc</td>
                    <td class="font3 fftd">
                        <f:formatNumber value="${part.rntc}" type="number"
                                        pattern="######.0000"/></td></td>
                </tr>
                <tr>
                    <td class="font3 fftd">Tntc</td>
                    <td class="font3 fftd">
                        <f:formatNumber value="${part.tntc}" type="number"
                                        pattern="######.0000"/></td></td></td>
                </tr>
                <tr>
                    <td class="font3 fftd">点胶图片</td>
                    <td class="font3 fftd">
                        <c:if test="${part.fluxPictureNames != null && part.fluxPictureNames.length() > 120}">
                            ${part.fluxPictureNames.substring(1, 120).concat("......")}
                        </c:if>
                        <c:if test="${part.fluxPictureNames != null && part.fluxPictureNames.length() <= 120}">
                            ${part.fluxPictureNames}
                        </c:if>
                </tr>
                <tr>
                    <td class="font3 fftd">焊接图片</td>
                    <td class="font3 fftd">
                        <c:if test="${part.weldPictureNames != null && part.weldPictureNames.length() > 120}">
                            ${part.weldPictureNames.substring(1, 120).concat("......")}
                        </c:if>
                        <c:if test="${part.weldPictureNames != null && part.weldPictureNames.length() <= 120}">
                            ${part.weldPictureNames}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="font3 fftd">AOI图片</td>
                    <td class="font3 fftd">
                        <c:if test="${part.aoiPictureNames != null && part.aoiPictureNames.length() > 120}">
                            ${part.aoiPictureNames.substring(1, 120).concat("......")}
                        </c:if>
                        <c:if test="${part.aoiPictureNames != null && part.aoiPictureNames.length() <= 120}">
                            ${part.aoiPictureNames}
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="font3 fftd">AOI信息</td>
                    <td class="font3 fftd" colspan="3">${part.aoiResult}</td>
                </tr>
                <tr>
                    <td class="font3 fftd">开始生产时间</td>
                    <td class="font3 fftd">
                        <f:formatDate value="${part.startTime}"
                                      type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                </tr>
                <tr>
                    <td class="font3 fftd">EOL检测时间</td>
                    <td class="font3 fftd">
                        <f:formatDate value="${part.proDate}"
                                      type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <td class="font3 fftd">结束生产时间</td>
                    <td class="font3 fftd">
                        <f:formatDate value="${part.endTime}"
                                      type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>

            </table>
        </td>
    </tr>
</table>
</body>
</html>