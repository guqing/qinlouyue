
package xyz.guqing.app.dao.system;


import xyz.guqing.app.bean.entity.system.Dict;
import xyz.guqing.app.dao.BaseRepository;

import java.util.List;

public interface DictRepository  extends BaseRepository<Dict,Long> {
    List<Dict> findByPid(Long pid);
    List<Dict> findByNameAndPid(String name,Long pid);

    List<Dict> findByNameLike(String name);
}
