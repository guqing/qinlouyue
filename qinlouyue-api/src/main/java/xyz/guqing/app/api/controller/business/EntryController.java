package xyz.guqing.app.api.controller.business;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.entity.front.Entry;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.dao.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created  on 2018/1/4 0004.
 *
 * @author zt
 */
@RestController
public class EntryController extends BaseController {
    @Autowired
    private MongoRepository mongoRepository;
    @RequestMapping(value = "/v2/index_entry",method = RequestMethod.GET)
    public Object list(){
        return Rets.success(mongoRepository.findAll(Entry.class));
    }
}
