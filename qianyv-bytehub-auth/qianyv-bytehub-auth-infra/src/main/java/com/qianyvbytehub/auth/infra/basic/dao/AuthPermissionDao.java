package com.qianyvbytehub.auth.infra.basic.dao;


import com.qianyvbytehub.auth.infra.basic.entity.AuthPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表(AuthPermission)表数据库访问层
 *
 * @author makejava
 * @since 2024-12-13 10:04:29
 */
public interface AuthPermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);

    /**
     * 根据id批量查询
     * @param ids
     * @return
     */
    List<AuthPermission> queryByIdList(@Param("ids") List<Long> ids);

    /**
     * 查询条件
     *
     * @param authPermission 查询条件
     * @return 对象列表
     */
    AuthPermission queryAllByCondition(AuthPermission authPermission);

    /**
     * 统计总行数
     *
     * @param authPermission 查询条件
     * @return 总行数
     */
    long count(AuthPermission authPermission);

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 影响行数
     */
    int insert(AuthPermission authPermission);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthPermission> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AuthPermission> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AuthPermission> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AuthPermission> entities);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 影响行数
     */
    int update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}

