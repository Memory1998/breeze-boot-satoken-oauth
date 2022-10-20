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

package com.breeze.boot.log.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 发布保存系统日志事件
 *
 * @author breeze
 * @date 2022-10-19
 */
public class PublisherSaveSysLogEvent {

    /**
     * 应用程序事件发布者
     */
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 发布事件
     *
     * @param applicationEvent 应用程序事件
     */
    public void publisherEvent(ApplicationEvent applicationEvent) {
        applicationEventPublisher.publishEvent(applicationEvent);
    }

}

