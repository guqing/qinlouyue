package xyz.guqing.app.api.controller.message;

import xyz.guqing.app.bean.constant.factory.PageFactory;
import xyz.guqing.app.bean.core.BussinessLog;
import xyz.guqing.app.bean.dictmap.CommonDict;
import xyz.guqing.app.bean.entity.message.MessageTemplate;
import xyz.guqing.app.bean.enumeration.BizExceptionEnum;
import xyz.guqing.app.bean.enumeration.Permission;
import xyz.guqing.app.bean.exception.ApplicationException;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.service.message.MessagetemplateService;
import xyz.guqing.app.utils.ToolUtil;
import xyz.guqing.app.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/message/template")
public class MessagetemplateController {
    @Autowired
    private MessagetemplateService messagetemplateService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.MSG_TPL})
    public Object list() {
        Page<MessageTemplate> page = new PageFactory<MessageTemplate>().defaultPage();
        page = messagetemplateService.queryPage(page);
        page.setRecords(page.getRecords());
        return Rets.success(page);
    }

    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑消息模板", key = "name", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_TPL_EDIT})
    public Object save(@ModelAttribute @Valid MessageTemplate tMessageTemplate) {
        if(tMessageTemplate.getId()!=null)
        {
            messagetemplateService.update(tMessageTemplate);
        }else{
            messagetemplateService.insert(tMessageTemplate);
        }
        return Rets.success();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除消息模板", key = "id", dict = CommonDict.class)
    @RequiresPermissions(value = {Permission.MSG_TPL_DEL})
    public Object remove(Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        messagetemplateService.delete(id);
        return Rets.success();
    }
}