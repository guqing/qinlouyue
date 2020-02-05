package xyz.guqing.app.api.controller.system;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.constant.factory.PageFactory;
import xyz.guqing.app.bean.core.BussinessLog;
import xyz.guqing.app.bean.dictmap.CfgDict;
import xyz.guqing.app.bean.entity.system.Cfg;
import xyz.guqing.app.bean.entity.system.FileInfo;
import xyz.guqing.app.bean.enumeration.BizExceptionEnum;
import xyz.guqing.app.bean.enumeration.Permission;
import xyz.guqing.app.bean.exception.ApplicationException;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.query.SearchFilter;
import xyz.guqing.app.service.system.CfgService;
import xyz.guqing.app.service.system.FileService;
import xyz.guqing.app.service.system.LogObjectHolder;
import xyz.guqing.app.utils.Maps;
import xyz.guqing.app.utils.StringUtils;
import xyz.guqing.app.utils.ToolUtil;
import xyz.guqing.app.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * CfgController
 *
 * @author enilu
 * @version 2018/11/17 0017
 */
@RestController
@RequestMapping("/cfg")
public class CfgController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CfgService cfgService;
    @Autowired
    private FileService fileService;

    /**
     * 查询参数列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.CFG})
    public Object list(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
        Page<Cfg> page = new PageFactory<Cfg>().defaultPage();
        if(StringUtils.isNotEmpty(cfgName)){
            page.addFilter(SearchFilter.build("cfgName", SearchFilter.Operator.LIKE, cfgName));
        }
        if(StringUtils.isNotEmpty(cfgValue)){
            page.addFilter(SearchFilter.build("cfgValue", SearchFilter.Operator.LIKE, cfgValue));
        }
        page = cfgService.queryPage(page);
        return Rets.success(page);
    }

    /**
     * 导出参数列表
     * @param cfgName
     * @param cfgValue
     * @return
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.CFG})
    public Object export(@RequestParam(required = false) String cfgName, @RequestParam(required = false) String cfgValue) {
        Page<Cfg> page = new PageFactory<Cfg>().defaultPage();
        if(StringUtils.isNotEmpty(cfgName)){
            page.addFilter(SearchFilter.build("cfgName", SearchFilter.Operator.LIKE, cfgName));
        }
        if(StringUtils.isNotEmpty(cfgValue)){
            page.addFilter(SearchFilter.build("cfgValue", SearchFilter.Operator.LIKE, cfgValue));
        }
        page = cfgService.queryPage(page);
        FileInfo fileInfo = fileService.createExcel("templates/config.xlsx","系统参数.xlsx",Maps.newHashMap("list",page.getRecords()));
        return Rets.success(fileInfo);
    }
    @RequestMapping(method = RequestMethod.POST)
    @BussinessLog(value = "编辑参数", key = "cfgName",dict= CfgDict.class)
    @RequiresPermissions(value = {Permission.CFG_EDIT})
    public Object save(@ModelAttribute @Valid Cfg cfg){
        if(cfg.getId()!=null){
            Cfg old = cfgService.get(cfg.getId());
            LogObjectHolder.me().set(old);
            old.setCfgName(cfg.getCfgName());
            old.setCfgValue(cfg.getCfgValue());
            old.setCfgDesc(cfg.getCfgDesc());
            cfgService.saveOrUpdate(old);
        }else {
            cfgService.saveOrUpdate(cfg);
        }
        return Rets.success();
    }
    @RequestMapping(method = RequestMethod.DELETE)
    @BussinessLog(value = "删除参数", key = "id",     dict= CfgDict.class)
    @RequiresPermissions(value = {Permission.CFG_DEL})
    public Object remove(@RequestParam Long id){
        logger.info("id:{}",id);
        if (ToolUtil.isEmpty(id)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
        cfgService.delete(id);
        return Rets.success();
    }
}
