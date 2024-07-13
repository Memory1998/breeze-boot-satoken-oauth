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

package com.breeze.boot.modules.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.modules.system.model.entity.SysDict;
import com.breeze.boot.modules.system.model.form.DictForm;
import com.breeze.boot.modules.system.model.form.DictOpenForm;
import com.breeze.boot.modules.system.model.query.DictQuery;
import com.breeze.boot.modules.system.model.vo.DictVO;

import java.util.List;

/**
 * 系统字典服务
 *
 * @author gaoweixuan
 * @since 2021-12-06 22:03:39
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 列表页面
     *
     * @param dictQuery 字典查询
     * @return {@link Page}<{@link DictVO}>
     */
    Page<DictVO> listPage(DictQuery dictQuery);

    /**
     * 按id获取信息
     *
     * @param dictId dict id
     * @return {@link DictVO }
     */
    DictVO getInfoById(Long dictId);

    /**
     * 保存dict
     *
     * @param dictForm 字典表单
     * @return {@link Boolean }
     */
    Boolean saveDict(DictForm dictForm);

    /**
     * 修改dict
     *
     * @param id       id
     * @param dictForm 字典表单
     * @return {@link Boolean }
     */
    Boolean modifyDict(Long id, DictForm dictForm);

    /**
     * 开关
     *
     * @param dictOpenForm 字典开关参数
     * @return {@link Boolean}
     */
    Boolean open(DictOpenForm dictOpenForm);

    /**
     * 删除字典通过IDS
     *
     * @param ids id
     * @return {@link Result}<{@link Boolean}>
     */
    Result<Boolean> deleteByIds(List<Long> ids);


}

