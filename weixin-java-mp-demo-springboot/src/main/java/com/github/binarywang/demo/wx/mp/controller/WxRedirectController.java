package com.github.binarywang.demo.wx.mp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.enums.TicketType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

/**
 * @author Edward
 */

@Api(tags = "网页授权")
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;

    @ApiOperation(value = "第二步：获得access token")
    @GetMapping("/greet")
    public String greetUser(@PathVariable String appid, @RequestParam String code, ModelMap map) {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            log.info(accessToken.toString());
            //第四步：拉取用户信息(需scope为 snsapi_userinfo)
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
            map.put("user", user);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "greet_user";
    }

    @ApiOperation(value = "第三步：刷新access_token（如果需要）")
    @GetMapping("/refresh")
    public String refreshAccessToken(@PathVariable String appid, @RequestParam String code, ModelMap map) throws WxErrorException {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
        accessToken = this.wxService.oauth2refreshAccessToken(accessToken.getRefreshToken());
        log.info(accessToken.toString());
        //第四步：拉取用户信息(需scope为 snsapi_userinfo)
        WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
        map.put("user", user);
        return "greet_user";
    }


}
//http://58g0b94ec.nat123.cc/wx/redirect/wxff43d3d562475f26/createjsapisignature?url=http%3A%2F%2F58g0b94ec.nat123.cc%2Fdemo%2Findex.html
//http://58g0b94ec.nat123.cc/wx/redirect/wxff43d3d562475f26/createjsapisignature?url=http%3A%2F%2F58g0b94ec.nat123.cc%2Fdemo%2Findex.html
//测试网页权限等使用微信开发者工具请求：http://58g0b94ec.nat123.cc/demo/index.html
