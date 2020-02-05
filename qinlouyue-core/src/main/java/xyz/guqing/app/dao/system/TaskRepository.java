
package xyz.guqing.app.dao.system;


import xyz.guqing.app.bean.entity.system.Task;
import xyz.guqing.app.dao.BaseRepository;

import java.util.List;

public interface TaskRepository extends BaseRepository<Task,Long> {

    long countByNameLike(String name);

    List<Task> findByNameLike(String name);
    List<Task> findAllByDisabled(boolean disable);
}
