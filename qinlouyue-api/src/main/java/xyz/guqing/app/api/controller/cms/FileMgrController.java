package xyz.guqing.app.api.controller.cms;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.constant.factory.PageFactory;
import xyz.guqing.app.bean.entity.system.FileInfo;
import xyz.guqing.app.bean.enumeration.Permission;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.query.SearchFilter;
import xyz.guqing.app.service.system.FileService;
import xyz.guqing.app.utils.StringUtils;
import xyz.guqing.app.utils.factory.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fileMgr")
public class FileMgrController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @RequiresPermissions(value = {Permission.FILE})
    public Object list(@RequestParam(required = false) String originalFileName
    ) {
        Page<FileInfo> page = new PageFactory<FileInfo>().defaultPage();
        if (StringUtils.isNotEmpty(originalFileName)) {
            page.addFilter(SearchFilter.build("originalFileName", SearchFilter.Operator.LIKE, originalFileName));
        }
        page = fileService.queryPage(page);
        return Rets.success(page);
    }
}
