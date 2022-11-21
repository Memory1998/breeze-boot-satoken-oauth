/*
 * Copyright (c) 2021-2022, gaoweixuan (breeze-cloud@foxmail.com).
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

package com.breeze.boot.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.database.mapper.BreezeBaseMapper;
import com.breeze.boot.security.entity.PermissionDTO;
import com.breeze.boot.system.domain.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 系统权限映射器
 *
 * @author gaoweixuan
 * @date 2022-10-30
 */
@Mapper
public interface SysPermissionMapper extends BreezeBaseMapper<SysPermission> {

    /**
     * 列表分页
     *
     * @param permissionDTO 权限dto
     * @param page          页面
     * @return {@link Page}<{@link SysPermission}>
     */
    Page<SysPermission> listPage(Page<SysPermission> page, @Param("permissionDTO") PermissionDTO permissionDTO);

}
