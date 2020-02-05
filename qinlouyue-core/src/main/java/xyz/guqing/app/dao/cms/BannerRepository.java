
package xyz.guqing.app.dao.cms;

import xyz.guqing.app.bean.entity.cms.Banner;
import xyz.guqing.app.dao.BaseRepository;

import java.util.List;

public interface BannerRepository extends BaseRepository<Banner,Long> {
    /**
     * 查询指定类别的banner列表
     * @param type
     * @return
     */
    List<Banner> findAllByType(String type);
}
