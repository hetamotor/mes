package top.dreamyy.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import top.dreamyy.hrm.domain.Notice;

import java.util.Map;

import static top.dreamyy.hrm.util.common.HrmConstants.NOTICETABLE;

/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: 公告动态SQL语句提供类
 * <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */
public class NoticeDynaSqlProvider {
    // 分页动态查询
    public String selectWhitParam(final Map<String, Object> params) {
        Notice notice = (Notice) params.get("notice");
        String starttime = (String) params.get("starttime");
        String endtime = (String) params.get("endtime");
        System.out.println("sql starttime = " + starttime);
        System.out.println("sql endtime = " + endtime);

        String sql = new SQL() {
            {
                SELECT("*");
                FROM(NOTICETABLE);
                if (notice != null) {
                    /*if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }*/
                    if (notice.getContent() != null && !notice.getContent().trim().equals("")) {
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                }
                if (starttime != null && !starttime.trim().equals("")) {
                    WHERE(" feedtime >= '" + starttime + "' ");
                }
                if (endtime != null && !endtime.trim().equals("")) {
                    WHERE(" feedtime <= '" + endtime + "' ");
                }
                ORDER_BY(" feedtime desc ");
                ORDER_BY(" title ");


            }
        }.toString();

        System.out.println(sql);

        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
        }

        return sql;
    }

    // 动态查询总数量
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(NOTICETABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice) params.get("notice");
                    if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                        WHERE("  title LIKE CONCAT ('%',#{notice.title},'%') ");
                    }
                    if (notice.getContent() != null && !notice.getContent().equals("")) {
                        WHERE("  content LIKE CONCAT ('%',#{notice.content},'%') ");
                    }
                }
            }
        }.toString();
    }

    // 动态插入
    public String insertNotice(final Notice notice) {
        return new SQL() {
            {
                INSERT_INTO(NOTICETABLE);
                if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }
                if (notice.getContent() != null && !notice.getContent().equals("")) {
                    VALUES("content", "#{content}");
                }
                if (notice.getPcs() != null && !notice.getPcs().equals("")) {
                    VALUES("pcs", "#{pcs}");
                }
                if (notice.getWorkorder() != null && !notice.getWorkorder().equals("")) {
                    VALUES("workorder", "#{workorder}");
                }
                VALUES("feedtime", " NOW() ");
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    // 动态更新
    public String updateNotice(final Notice notice) {
        return new SQL() {
            {
                UPDATE(NOTICETABLE);
                if (notice.getTitle() != null && !notice.getTitle().equals("")) {
                    SET(" title = #{title} ");
                }
                if (notice.getContent() != null && !notice.getContent().equals("")) {
                    SET(" content = #{content} ");
                }
                if (notice.getPcs() != null && !notice.getPcs().equals("")) {
                    SET(" pcs = #{pcs} ");
                }
                if (notice.getWorkorder() != null && !notice.getWorkorder().equals("")) {
                    SET(" workorder = #{workorder} ");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    SET(" user_id = #{user.id} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
