/*
 * Copyright (c) 2015-2021, www.dibo.ltd (service@dibo.ltd).
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
package com.diboot.mobile.starter;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号/小程序配置
 *
 * @author : uu
 * @version : v2.3.1
 * @Copyright © diboot.com
 * @Date 2021/8/31  07:40
 */
@AllArgsConstructor
@Configuration
@ConditionalOnExpression("#{environment.getProperty('diboot.mobile.wx.mp.appid') != null ||  environment.getProperty('diboot.mobile.wx.miniapp.appid') != null}")
public class WeiXinConfiguration {

    private final WeiXinProperties properties;

    /**
     * 微信公众号配置
     *
     * @return
     */
    @Bean
    @ConditionalOnExpression("#{environment.getProperty('diboot.mobile.wx.mp.appid') != null}")
    public WxMpService wxMpService() {
        WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
        WeiXinProperties.Config mp = properties.getMp();
        configStorage.setAppId(mp.getAppid());
        configStorage.setSecret(mp.getSecret());
        configStorage.setAesKey(mp.getAesKey());
        configStorage.setToken(mp.getToken());
        WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(configStorage);
        return service;
    }

    /**
     * 微信小程序配置
     *
     * @return
     */
    @Bean
    @ConditionalOnExpression("#{environment.getProperty('diboot.mobile.wx.miniapp.appid') != null}")
    public WxMaService wxMaService() {
        WxMaDefaultConfigImpl configStorage = new WxMaDefaultConfigImpl();
        WeiXinProperties.Config miniapp = properties.getMiniapp();
        configStorage.setAppid(miniapp.getAppid());
        configStorage.setSecret(miniapp.getSecret());
        configStorage.setAesKey(miniapp.getAesKey());
        configStorage.setToken(miniapp.getToken());
        WxMaService service = new WxMaServiceImpl();
        service.setWxMaConfig(configStorage);
        return service;
    }

}