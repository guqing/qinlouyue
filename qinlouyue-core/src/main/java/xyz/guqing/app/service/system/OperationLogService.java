package xyz.guqing.app.service.system;

import xyz.guqing.app.bean.entity.system.OperationLog;
import xyz.guqing.app.dao.system.OperationLogRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created  on 2018/3/26 0026.
 *
 * @author enilu
 */
@Service
public class OperationLogService extends BaseService<OperationLog,Long,OperationLogRepository> {

}
