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

package com.breeze.boot.system.config;

import com.breeze.boot.security.service.LocalUserDetailsService;
import com.breeze.boot.system.service.impl.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本地用户服务配置
 *
 * @author gaoweixuan
 * @date 2022-08-31
 */
@Configuration
public class LocalUserServiceConfig {

    @Autowired
    private UserTokenService userTokenService;

    @Bean
    public LocalUserDetailsService loadCurrentLoginUser() {
        return new LocalUserDetailsService(
                userTokenService::loadUserByUsername
                , userTokenService::loadUserByPhone
                , userTokenService::loadUserByEmail);
    }

}
