package top.dreamyy.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import top.dreamyy.hrm.domain.Dept;

import java.util.Map;

import static top.dreamyy.hrm.util.common.HrmConstants.DEPTTABLE;

/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: 部门动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */
public class PartDynaSqlProvider {
    // 分页动态查询
    public String selectWhitParam(final Map<String, Object> params) {
        String qrocde = (String) params.get("qrcode");
        String shuntsensor = (String) params.get("shuntsensor");

        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DEPTTABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
                if (qrocde != null && !"".equals(qrocde)) {
                    WHERE("  print_code LIKE '%" + qrocde + "%' ");
                }
                if (shuntsensor != null && !"".equals(shuntsensor)) {
                    WHERE("  shunt_code LIKE '%" + shuntsensor + "%' ");
                }
                ORDER_BY(" end_time desc");
            }
        }.toString();

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }

        System.out.println(sql);

        return sql;
    }

    // 动态查询总数量
    public String count(final Map<String, Object> params) {
        String qrocde = (String) params.get("qrcode");
        String shuntsensor = (String) params.get("shuntsensor");

        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DEPTTABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept) params.get("dept");
                    if (dept.getName() != null && !dept.getName().equals("")) {
                        WHERE("  name LIKE CONCAT ('%',#{dept.name},'%') ");
                    }
                }
                if (qrocde != null && !"".equals(qrocde)) {
                    WHERE("  print_code LIKE '%" + qrocde + "%' ");
                }
                if (shuntsensor != null && !"".equals(shuntsensor)) {
                    WHERE("  shunt_code LIKE '%" + shuntsensor + "%' ");
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertDept(final Dept dept) {
        return new SQL() {
            {
                INSERT_INTO(DEPTTABLE);
                if (dept.getName() != null && !dept.getName().equals("")) {
                    VALUES("name", "#{name}");
                }
                if (dept.getRemark() != null && !dept.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateDept(final Dept dept) {
        return new SQL() {
            {
                UPDATE(DEPTTABLE);
                if (dept.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (dept.getRemark() != null) {
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}

