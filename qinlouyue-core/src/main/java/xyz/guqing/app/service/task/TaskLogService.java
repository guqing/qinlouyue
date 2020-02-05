package xyz.guqing.app.service.task;


import xyz.guqing.app.bean.entity.system.TaskLog;
import xyz.guqing.app.dao.system.TaskLogRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 定时任务日志服务类
 * @author  enilu
 * @date 2019-08-13
 */
@Service
public class TaskLogService extends BaseService<TaskLog,Long,TaskLogRepository> {
}
