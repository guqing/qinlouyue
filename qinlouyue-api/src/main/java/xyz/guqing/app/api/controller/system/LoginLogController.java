package xyz.guqing.app.api.controller.system;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.constant.factory.PageFactory;
import xyz.guqing.app.bean.entity.system.LoginLog;
import xyz.guqing.app.bean.enumeration.Permission;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.query.SearchFilter;
import xyz.guqing.app.service.system.LoginLogService;
import xyz.guqing.app.utils.BeanUtil;
import xyz.guqing.app.utils.DateUtil;
import xyz.guqing.app.utils.factory.Page;
import xyz.guqing.app.warpper.LogWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录日志controller
 *
 * @author enilu
 * @version 2018/10/5 0005
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginlogService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.LOGIN_LOG})
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName) {
        Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
        page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtil.parseDate(beginTime));
        page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtil.parseDate(endTime));
        page.addFilter( "logname", SearchFilter.Operator.LIKE, logName);
        Page pageResult = loginlogService.queryPage(page);
        pageResult.setRecords((List<LoginLog>) new LogWarpper(BeanUtil.objectsToMaps(pageResult.getRecords())).warp());
        return Rets.success(pageResult);

    }


    /**
     * 清空日志
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.LOGIN_LOG_CLEAR})
    public Object clear() {
        loginlogService.clear();
        return Rets.success();
    }
}
