<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

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
    <link href="fkjava.ico" rel="shortcut icon" type="image/x-icon"/>
    <link href="${ctx }/css/css.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="${ctx}/js/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.form.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            /** 表单提交的校验 */
            $("#btn").click(function () {
                var fileName = $("#fileName").val();
                var remark = $("#remark").val();

                if ($.trim(fileName).length <= 2) {
                    alert("请输入文件编号！");
                    return;
                } else if ($.trim(remark).length <= 2) {
                    alert("请输入文档描述！");
                    return;
                }

                $("#documentForm").submit();

            })
        });

        $(function () {
            var reg = $("#namingcon").val();
            var patt1 = new RegExp("产品号");
            var patt2 = new RegExp("零件号");
            if (patt1.test(reg) || patt2.test(reg)) {
                $("#productlist").css('display','block');//显示
            } else {
                $("#productlist").css('display', 'none');//隐藏
            }

            $("#genfname").click(function () {
                var names = reg.split("#");

                var filename = names[0];
                if (names.length > 1) {
                    filename = filename.concat("-");

                    for (x in names) {
                        if (names[x] == "YYMM" || names[x] == "YYMMDD") {
                            filename = filename.concat(timeStamp2String(names[x]));
                        } else if (names[x] == "流水号") {
                            if (x != (names.length - 1)) {
                                alert("流水号必须在最后！")
                                break;
                            }

                            var fn = $("#dbf").val();
                            // 数据库不存在之前的filename，流水号就设置为001
                            if (fn == null || fn.trim().length == 0) {
                                filename = filename.concat("001");
                                break;
                            }

                            var m = parseInt(fn.substring(fn.length - 3, fn.length)) + 1;
                            var n = m < 10 ? "00" + m : (m < 100 ? "0" + m : m);
                            filename = filename.concat(n);
                        } else if (names[x] == "随机号") {
                            var rand = parseInt(Math.random() * 1000) + 1;
                            var seed = rand < 10 ? "00" + rand : (rand < 100 ? "0" + rand : rand);
                            filename = filename.concat(seed);
                        } else if (names[x] == "产品号" || names[x] == "零件号") {
                            filename = filename.concat("[需查找零件号]");
                        } else {
                            if (x > 0) {
                                filename = filename.concat("[不能识别通配符=" + names[x] + "]");
                            }
                        }
                    }
                }
                $("#fileName").val(filename);
            });

            function pattenlength(ss) {
                if (ss == 'YYMM' || ss == "产品号" || ss == "零件号") {
                    return 4;
                } else if (ss == 'YYMMDD') {
                    return 6
                } else {
                    return 3;
                }
            };

            //在Jquery里格式化Date日期时间数据
            function timeStamp2String(timepattern) {
                var datetime = new Date();
                var year = datetime.getFullYear() - 2000;
                var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
                var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();

                if (timepattern == "YYMM") {
                    return year + "" + month;
                } else if (timepattern == 'YYMMDD') {
                    return year + "" + month + "" + date;
                }
            }
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
        <td class="main_locbg font2"><img src="${ctx }/images/pointer.gif">&nbsp;&nbsp;&nbsp;当前位置：文档管理 &gt; 上传文档
        </td>
        <td width="15" height="32"><img src="${ctx }/images/main_locright.gif" width="15" height="32"></td>
    </tr>
</table>

<table width="100%" height="90%" border="0" cellpadding="10" cellspacing="0" class="main_tabbor">
    <tr valign="top">
        <td>
            <form id="documentForm" name="documentForm" action="${ctx }/document/addDocument"
                  enctype="multipart/form-data" method="post">
                <!-- 隐藏表单，flag表示添加标记 -->
                <input type="hidden" name="flag" value="2">
                <table width="100%" border="0" cellpadding="0" cellspacing="10" class="main_tab">
                    <tr>
                        <td class="font3 fftd" width="15%">文件名称</td>
                        <td class="font3 fftd">
                            <input type="text" name="fileName" size="30" id="fileName"/>&nbsp;&nbsp;
                            <input type="button" id="genfname" value="生成文件名"/>
                            <input type="hidden" name="dbf" size="30" id="dbf" value="${requestScope.dbf}"/>&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd">文件描述
                        </td>
                        <td class="font3 fftd">
                            <input type="text" name="remark" size="30" id="remark"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd" width="15%">
                            模板名称
                        </td>
                        <td class="font3 fftd">
                            <input type="text" name="title" size="30" id="title" value="${notice.title }"
                                   readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td class="font3 fftd" width="15%">
                            文件命名规则
                        </td>
                        <td class="font3 fftd">
                            <input type="text" name="namingcon" size="30" id="namingcon" value="${notice.content }"
                                   readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor" colspan="2"></td>
                    </tr>

                    <tr>
                        <td></td>
                        <td class="font3 fftd">
                            <input type="button" id="btn" value="确认" style="width:80px">
                            <input type="reset" value="重置" style="width:80px">
                        </td>
                    </tr>
                    <tr>
                        <td class="main_tdbor"></td>
                    </tr>
                </table>
            </form>
        </td>
</table>

<div style="height:10px;"></div>
</body>
</html>