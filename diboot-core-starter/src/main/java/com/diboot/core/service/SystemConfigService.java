/*
 * Copyright (c) 2015-2022, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.diboot.core.service;

import com.diboot.core.entity.SystemConfigValues;
import com.diboot.core.config.SystemConfigType;
import com.diboot.core.entity.SystemConfig;
import com.diboot.core.vo.LabelValue;
import com.diboot.core.vo.SystemConfigVO;

import java.util.List;
import java.util.Map;

/**
 * 系统配置相关Service
 *
 * @author wind
 * @version v2.5.0
 * @date 2022-01-13
 */
public interface SystemConfigService extends BaseService<SystemConfig> {

    /**
     * 获取配置类型列表
     *
     * @return 配置类型列表
     */
    List<LabelValue> getTypeList();

    /**
     * 根据类型获取配置值
     * <p>
     * 用于代码中通过类型获取所有配置，避免多次查询
     *
     * @param typeClass 配置类型class
     * @param <E>       配置类型
     * @return 配置映射
     */
    <E extends Enum<? extends SystemConfigType>> SystemConfigValues<E> getValuesByType(Class<E> typeClass);

    /**
     * 根据类型获取配置
     *
     * @param type 类型
     * @return 配置列表
     */
    List<SystemConfigVO> getConfigByType(String type);

    /**
     * 根据类型及属性删除配置
     *
     * @param type
     * @param prop
     * @return
     */
    boolean deleteByTypeAndProp(String type, String prop);

    /**
     * 配置测试
     *
     * @param type 类型
     * @param data 数据
     */
    void configTest(String type, Map<String, Object> data);

}
