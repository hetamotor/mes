package top.dreamyy.hrm.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import top.dreamyy.hrm.dao.provider.JobDynaSqlProvider;
import top.dreamyy.hrm.domain.Job;

import java.util.List;
import java.util.Map;

import static top.dreamyy.hrm.util.common.HrmConstants.JOBTABLE;


/**
 * @author 肖文吉    36750064@qq.com
 * @version V1.0
 * @Description: <br>网站：<a href="http://www.fkit.org">疯狂Java</a>
 */
public interface JobDao {

    @Select("select * from " + JOBTABLE + " where ID = #{id}")
    Job selectById(int id);

    @Select("select * from " + JOBTABLE + " ")
    List<Job> selectAllJob();

    // 动态查询
    @SelectProvider(type = JobDynaSqlProvider.class, method = "selectWhitParam")
    List<Job> selectByPage(Map<String, Object> params);

    @SelectProvider(type = JobDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    // 根据id删除部门
    @Delete(" delete from " + JOBTABLE + " where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入部门
    @SelectProvider(type = JobDynaSqlProvider.class, method = "insertJob")
    void save(Job job);

    // 动态修改用户
    @SelectProvider(type = JobDynaSqlProvider.class, method = "updateJob")
    void update(Job job);
}

