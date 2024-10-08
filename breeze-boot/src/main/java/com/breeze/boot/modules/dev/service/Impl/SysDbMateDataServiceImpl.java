/*
 * Copyright (c) 2023, gaoweixuan (breeze-cloud@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.breeze.boot.modules.dev.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.breeze.boot.modules.dev.service.SysDbMateService;
import com.google.common.collect.Maps;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 元数据服务impl
 *
 * @author gaoweixuan
 * @since 2022-11-12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysDbMateDataServiceImpl implements SysDbMateService {

    private final SqlSessionFactory sqlSessionFactory;

    /**
     * 连接
     */
    private Connection connection;
    /**
     * 元数据
     */
    private DatabaseMetaData metaData;

    @PostConstruct
    public void init() {
        connection = sqlSessionFactory.openSession().getConnection();
        try {
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            log.error("获取数据库连接失败", e);
        }
    }

    /**
     * 表名下拉框
     *
     * @return {@link List}<{@link Map}<{@link String}, {@link String}>>
     */
    @Override
    public List<Map<String, Object>> selectTable() {
        List<Map<String, Object>> tableList = new ArrayList<>();
        try {
            // 获取 表
            ResultSet rs = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"table"});
            while (rs.next()) {
                Map<String, Object> selectMap = Maps.newHashMap();
                String tableName = rs.getString("TABLE_NAME");
                selectMap.put("label", tableName);
                selectMap.put("value", tableName);
                tableList.add(selectMap);
            }
        } catch (SQLException e) {
            log.error("获取数据库连接失败", e);
        }
        return tableList;
    }

    /**
     * 字段下拉框
     *
     * @param tableName 表名
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Override
    public List<Map<String, Object>> selectTableColumn(String tableName) {
        List<Map<String, Object>> columnList = new ArrayList<>();
        try {
            // 获取 字段
            ResultSet rs = metaData.getColumns(connection.getCatalog(), null, tableName, null);
            while (rs.next()) {
                Map<String, Object> tableMap = Maps.newHashMap();
                String columnName = rs.getString("COLUMN_NAME");
                tableMap.put("label", columnName);
                tableMap.put("value", columnName);
                columnList.add(tableMap);
                log.info("COLUMN_NAME:{}", columnName);
            }
        } catch (SQLException e) {
            log.error("SQL异常", e);
        }
        return columnList;
    }

    @Override
    public List<Map<String, Object>> listTable() {
        List<Map<String, Object>> tableList = new ArrayList<>();
        try {
            // 获取 表
            ResultSet rs = metaData.getTables(connection.getCatalog(), null, "%", new String[]{"table"});
            while (rs.next()) {
                Map<String, Object> columnMap = Maps.newHashMap();
                String tableName = rs.getString("TABLE_NAME");
                String tableType = rs.getString("TABLE_TYPE");
                String remarks = rs.getString("REMARKS");
                columnMap.put("tableName", tableName);
                columnMap.put("tableType", tableType);
                columnMap.put("remarks", remarks);
                if (!StrUtil.startWith(tableName, "qrtz_")) {
                    tableList.add(columnMap);
                }
            }
        } catch (SQLException e) {
            log.error("获取数据库连接失败", e);
        }
        return tableList;
    }

    /**
     * 字段列表
     *
     * @param tableName 表名
     * @return {@link List}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Override
    public List<Map<String, Object>> listTableColumn(String tableName) {
        List<Map<String, Object>> columnList = new ArrayList<>();
        try {
            // 获取 字段
            ResultSet rs = metaData.getColumns(connection.getCatalog(), null, tableName, null);
            while (rs.next()) {
                Map<String, Object> columnMap = Maps.newHashMap();
                String columnName = rs.getString("COLUMN_NAME");
                String comment = rs.getString("REMARKS");
                String typeName = rs.getString("TYPE_NAME");
                String columnSize = rs.getString("COLUMN_SIZE");
                String decimalDigits = rs.getString("DECIMAL_DIGITS");
                String isNullAble = rs.getString("IS_NULLABLE");
                columnMap.put("tableName", tableName);
                columnMap.put("columnName", columnName);
                columnMap.put("comment", comment);
                columnMap.put("typeName", typeName);
                columnMap.put("columnSize", columnSize);
                columnMap.put("decimalDigits", decimalDigits);
                columnMap.put("isNullAble", isNullAble);
                columnList.add(columnMap);
            }
        } catch (SQLException e) {
            log.error("SQL异常", e);
        }
        return columnList;
    }
}
