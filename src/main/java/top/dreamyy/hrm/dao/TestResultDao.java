package top.dreamyy.hrm.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.dreamyy.hrm.dao.provider.PartDynaSqlProvider;
import top.dreamyy.hrm.domain.Dept;
import top.dreamyy.hrm.domain.TestResult;

import java.util.List;
import java.util.Map;

import static top.dreamyy.hrm.util.common.HrmConstants.DEPTTABLE;

public interface TestResultDao {
    // 动态查询
    @SelectProvider(type = PartDynaSqlProvider.class, method = "selectWhitParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "pro_date", property = "proDate", javaType = java.util.Date.class),
            @Result(column = "virtual_partnumber", property = "virtualPartnumber"),
            @Result(column = "shunt_code", property = "shuntCode"),
            @Result(column = "print_code", property = "printCode"),
            @Result(column = "weld_picture_names", property = "weldPictureNames"),
            @Result(column = "end_time", property = "endTime", javaType = java.util.Date.class)
    })
    List<TestResult> selectByPage(Map<String, Object> params);

    @SelectProvider(type = PartDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    @Select("select * from " + DEPTTABLE + " ")
    List<Dept> selectAllDept();

    @Select("select * from " + DEPTTABLE + " where ID = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "pro_date", property = "proDate", javaType = java.util.Date.class),
            @Result(column = "virtual_partnumber", property = "virtualPartnumber"),
            @Result(column = "shunt_code", property = "shuntCode"),
            @Result(column = "print_code", property = "printCode"),
            @Result(column = "weld_picture_names", property = "weldPictureNames"),
            @Result(column = "aoi_picture_names", property = "aoiPictureNames"),
            @Result(column = "start_time", property = "startTime", javaType = java.util.Date.class),
            @Result(column = "end_time", property = "endTime", javaType = java.util.Date.class),
            @Result(column = "flux_picture_names", property = "fluxPictureNames"),
            @Result(column = "aoi_result", property = "aoiResult")
    })
    TestResult selectById(int id);

    // 根据id删除部门
    @Delete(" delete from " + DEPTTABLE + " where id = #{id} ")
    void deleteById(Integer id);

    // 动态插入部门
    @SelectProvider(type = PartDynaSqlProvider.class, method = "insertDept")
    void save(Dept dept);

    // 动态修改用户
    @SelectProvider(type = PartDynaSqlProvider.class, method = "updateDept")
    void update(Dept dept);
}
