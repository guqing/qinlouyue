
package xyz.guqing.app.dao.cms;

import xyz.guqing.app.bean.entity.cms.Article;
import xyz.guqing.app.dao.BaseRepository;

import java.util.List;

public interface ArticleRepository extends BaseRepository<Article,Long> {
    /**
     * 查询指定栏目下所有文章列表
     * @param idChannel
     * @return
     */
    List<Article> findAllByIdChannel(Long idChannel);
}
