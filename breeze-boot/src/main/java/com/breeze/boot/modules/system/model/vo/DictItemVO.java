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

package com.breeze.boot.modules.system.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * 系统字典项VO
 *
 * @author gaoweixuan
 * @since 2024-07-13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "系统字典项VO")
public class DictItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键")
    private Long id;

    /**
     * 字典ID
     */
    @Schema(description = "字典ID")
    private Long dictId;

    /**
     * 字典项的值
     */
    @Schema(description = "字典项的值")
    private String value;

    /**
     * 字典项名称
     */
    @Schema(description = "字典项名称")
    private String label;

    /**
     * 类型，对应elementUI 的类型
     */
    @Schema(description = "类型，对应elementUI 的类型")
    private String type;

}
