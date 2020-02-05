package xyz.guqing.app.api.controller.system;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.constant.factory.PageFactory;
import xyz.guqing.app.bean.constant.state.BizLogType;
import xyz.guqing.app.bean.entity.system.OperationLog;
import xyz.guqing.app.bean.enumeration.Permission;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.query.SearchFilter;
import xyz.guqing.app.service.system.OperationLogService;
import xyz.guqing.app.utils.BeanUtil;
import xyz.guqing.app.utils.DateUtil;
import xyz.guqing.app.utils.HttpKit;
import xyz.guqing.app.utils.factory.Page;
import xyz.guqing.app.warpper.LogWarpper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LogController
 *
 * @author enilu
 * @version 2018/10/5 0005
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 查询操作日志列表
     */
    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions(value = {Permission.LOG})
    public Object list(@RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) String logName,
                       @RequestParam(required = false) Integer logType) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        page.addFilter("createTime", SearchFilter.Operator.GTE, DateUtil.parseDate(beginTime));
        page.addFilter("createTime", SearchFilter.Operator.LTE, DateUtil.parseDate(endTime));
        page.addFilter( "logname", SearchFilter.Operator.LIKE, logName);
        if (logType != null) {
            page.addFilter(SearchFilter.build("logtype", SearchFilter.Operator.EQ, BizLogType.valueOf(logType)));
        }
        page = operationLogService.queryPage(page);
        page.setRecords((List<OperationLog>) new LogWarpper(BeanUtil.objectsToMaps(page.getRecords())).warp());
        return Rets.success(page);
    }

    /**
     * 查询指定用户的操作日志列表
     */
    @RequestMapping("/queryByUser")
    @ResponseBody
    @RequiresPermissions(value = {Permission.LOG})
    public Object list() {
        Page<OperationLog> page = new Page<OperationLog>();
        page.addFilter(SearchFilter.build("userid", SearchFilter.Operator.EQ, getIdUser(HttpKit.getRequest())));
        Page<OperationLog> pageResult = operationLogService.queryPage(page);
        return Rets.success(pageResult.getRecords());
    }

    /**
     * 清空日志
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @RequiresPermissions(value = {Permission.LOG_CLEAR})
    public Object delLog() {
        operationLogService.clear();
        return Rets.success();
    }
}
