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

package com.breeze.boot.security.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.breeze.boot.core.base.UserInfoDTO;
import com.breeze.boot.core.constants.CacheConstants;
import lombok.SneakyThrows;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

/**
 * 安全工具
 *
 * @author gaoweixuan
 * @since 2022-08-31
 */
public class SecurityUtils {

    /**
     * 获取当前用户
     *
     * @return {@link UserInfoDTO}
     * @throws OAuth2AuthenticationException 如果用户未登录或JWT验证失败
     */
    @SneakyThrows
    public static UserInfoDTO getCurrentUser() throws OAuth2AuthenticationException {
        CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);
        return (UserInfoDTO) cacheManager.getCache(CacheConstants.LOGIN_USER).get(getUsername()).get();
    }

    /**
     * 获取当前用户是否是管理员
     *
     * @return {@link UserInfoDTO}
     * @throws OAuth2AuthenticationException 如果用户未登录或JWT验证失败
     */
    @SneakyThrows
    public static Boolean isAdmin() throws OAuth2AuthenticationException {
        CacheManager cacheManager = SpringUtil.getBean(CacheManager.class);
        return ((UserInfoDTO) cacheManager.getCache(CacheConstants.LOGIN_USER).get(getUsername()).get()).getUserRoleCodes().contains("ROLE_ADMIN");
    }


    /**
     * 获取用户名
     *
     * @return {@link String}
     */
    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
