package xyz.guqing.app.api.controller.business;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.entity.front.Address;
import xyz.guqing.app.bean.entity.front.Ids;
import xyz.guqing.app.bean.enumeration.BizExceptionEnum;
import xyz.guqing.app.bean.exception.ApplicationException;
import xyz.guqing.app.bean.vo.business.City;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.dao.MongoRepository;
import xyz.guqing.app.service.front.IdsService;
import xyz.guqing.app.service.front.PositionService;
import xyz.guqing.app.utils.Maps;
import xyz.guqing.app.utils.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created  on 2018/1/5 0005.
 *
 * @author zt
 */
@RestController
public class AddressController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    private MongoRepository mongoRepository;
    @Autowired
    private IdsService idsService;
    @Autowired
    private PositionService positionService;
    @RequestMapping(value = "/v1/users/{user_id}/addresses",method = RequestMethod.GET)
    public Object address(@PathVariable("user_id")Long userId){
        return Rets.success(mongoRepository.findAll(Address.class,"user_id",userId));
    }
    @RequestMapping(value = "/v1/users/{user_id}/addresses",method =  RequestMethod.POST)
    public Object save(@PathVariable("user_id")Long userId){
        City city = positionService.guessCity(getIp());
        Address address = getRequestPayload(Address.class);
        address.setUser_id(userId);
        address.setCity_id(city.getId());
        address.setId(idsService.getId(Ids.ADDRESS_ID));
        mongoRepository.save(address);
        return Rets.success("添加地址成功");
    }
    @RequestMapping(value = "/v1/users/${user_id}/addresses/${address_id}",method =  RequestMethod.POST)
    public Object delete(@PathVariable("user_id")Long userId,@PathVariable("address_id") Long addressId){
        mongoRepository.delete("addresses", Maps.newHashMap("user_id",userId,"id",addressId));
        return Rets.success("删除地址成功");
    }

    @RequestMapping(value="/address/{id}",method = RequestMethod.GET)
    public Object get(@PathVariable Long id){
        logger.info("id:{}",id);
        if (ToolUtil.isEmpty(id)) {
            throw new ApplicationException(BizExceptionEnum.REQUEST_NULL);
        }
         return Rets.success(mongoRepository.findOne(Address.class,id));
    }
}
