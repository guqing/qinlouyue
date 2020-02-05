package xyz.guqing.app.service.system;

import xyz.guqing.app.bean.entity.system.Cfg;
import xyz.guqing.app.cache.ConfigCache;
import xyz.guqing.app.dao.system.CfgRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CfgService
 *
 * @author enilu
 * @version 2018/11/17 0017
 */

@Service
@Transactional
public class CfgService extends BaseService<Cfg, Long, CfgRepository> {
    @Autowired
    private ConfigCache configCache;

    public Cfg saveOrUpdate(Cfg cfg) {
        if(cfg.getId()==null) {
            insert(cfg);
        }else{
            update(cfg);
        }
        configCache.cache();
        return cfg;
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
        configCache.cache();
    }

}
