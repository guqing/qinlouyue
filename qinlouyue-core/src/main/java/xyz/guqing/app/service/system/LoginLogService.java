package xyz.guqing.app.service.system;


import xyz.guqing.app.bean.entity.system.LoginLog;
import xyz.guqing.app.dao.system.LoginLogRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created  on 2018/3/26 0026.
 *
 * @author enilu
 */
@Service
public class LoginLogService extends BaseService<LoginLog,Long,LoginLogRepository> {

}
