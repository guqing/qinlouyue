package xyz.guqing.app.service.cms;

import xyz.guqing.app.bean.entity.cms.Article;
import xyz.guqing.app.bean.enumeration.cms.ChannelEnum;
import xyz.guqing.app.bean.vo.query.SearchFilter;
import xyz.guqing.app.dao.cms.ArticleRepository;
import xyz.guqing.app.service.BaseService;
import xyz.guqing.app.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends BaseService<Article, Long, ArticleRepository> {
    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 查询首页最近5条资讯数据
     *
     * @return
     */
    public List<Article> queryIndexNews() {
        Page<Article> page = query(1, 5, ChannelEnum.NEWS.getId());
        return page.getRecords();
    }

    public Page<Article> query(int currentPage, int size, Long idChannel) {
        Page page = new Page(currentPage, size, "id");
        page.addFilter(SearchFilter.build("idChannel", SearchFilter.Operator.EQ, idChannel));
        return queryPage(page);

    }
}
