package com.github.binarywang.demo.wx.mp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

@Api(tags = "网页开发")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("wx/webview/{appid}")
public class WxWebviewController {
    private final WxMpService wxService;

    @ApiOperation(value = "获得ticket.")
    @GetMapping("/getticket")
    public String getTicket(@PathVariable String appid) throws WxErrorException {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        String ticket = wxService.getTicket(TicketType.JSAPI, true);
        return ticket;
    }

    @ApiOperation(value = "创建调用jsapi时所需要的签名.")
    @GetMapping("/createjsapisignature")
    public WxJsapiSignature createJsapiSignature(@PathVariable String appid, @RequestParam String url) throws WxErrorException {
        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        WxJsapiSignature wxJsapiSignature = wxService.createJsapiSignature(url);
        return wxJsapiSignature;
    }

    @ApiOperation(value = "第一步：构造网页授权url")
    @ApiImplicitParams({@ApiImplicitParam(name = "redirectType", value = "重定向类型：1.用户信息界面，2.网页权限测试界面", required = true, paramType = "query", dataType = "int")})
    @GetMapping("/getcode")
    public String getCode(@PathVariable String appid, Integer redirectType) {
        String redirectURI = null;
        switch (redirectType) {
            case 1:
                redirectURI = "http://58g0b94ec.nat123.cc/wx/redirect/wxff43d3d562475f26/greet";
                break;
            case 2:
                redirectURI = "http://58g0b94ec.nat123.cc/demo/index.html";
                break;
        }
        String url = this.wxService.switchoverTo(appid).oauth2buildAuthorizationUrl(redirectURI,
            WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        return url;
    }
}
