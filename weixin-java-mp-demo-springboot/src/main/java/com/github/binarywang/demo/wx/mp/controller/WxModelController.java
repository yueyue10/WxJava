package com.github.binarywang.demo.wx.mp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateIndustry;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@Api(tags = "微信模板")
@RequestMapping("/wx/model/{appid}")
public class WxModelController {
    private final WxMpService wxService;

    @ApiOperation(value = "获取模板列表", notes = "模板列表")
    @GetMapping("/list")
    public List<WxMpTemplate> getTemplateMsgList(@PathVariable String appid) throws WxErrorException {
        List<WxMpTemplate> result = this.wxService.switchoverTo(appid).getTemplateMsgService().getAllPrivateTemplate();
        return result;
    }

    @ApiOperation(value = "获用户表")
    @GetMapping("/user/list")
    public WxMpUserList getUserList(@PathVariable String appid) throws WxErrorException {
        WxMpUserList wxMpUserList = this.wxService.switchoverTo(appid).getUserService().userList(null);
        return wxMpUserList;
    }

    @ApiOperation(value = "获取模板所属行业")
    @GetMapping("/belong")
    public WxMpTemplateIndustry getBelong(@PathVariable String appid) throws WxErrorException {
        final WxMpTemplateIndustry industry = this.wxService.switchoverTo(appid).getTemplateMsgService().getIndustry();
        return industry;
    }

    @ApiOperation(value = "发送模板消息")
    @GetMapping("/send/modelmsg")
    public String sendModelMsg(@PathVariable String appid, @RequestParam(name = "toUser") String toUser, @RequestParam(name = "tepplateId") String tepplateId) throws WxErrorException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
            .toUser(toUser)
            .templateId(tepplateId)
            .url(" ")
            .build();
        templateMessage.addData(new WxMpTemplateData("first", dateFormat.format(new Date()), "#FF00FF"))
            .addData(new WxMpTemplateData("remark", RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
        String msgId = this.wxService.switchoverTo(appid).getTemplateMsgService().sendTemplateMsg(templateMessage);
        return msgId;
    }
}
