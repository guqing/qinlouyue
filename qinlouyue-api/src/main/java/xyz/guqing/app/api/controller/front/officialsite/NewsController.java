package xyz.guqing.app.api.controller.front.officialsite;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.entity.cms.Article;
import xyz.guqing.app.bean.enumeration.cms.BannerTypeEnum;
import xyz.guqing.app.bean.enumeration.cms.ChannelEnum;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.offcialsite.BannerVo;
import xyz.guqing.app.bean.vo.offcialsite.News;
import xyz.guqing.app.service.cms.ArticleService;
import xyz.guqing.app.service.cms.BannerService;
import xyz.guqing.app.utils.factory.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/offcialsite/news")
public class NewsController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object list() {
        Map<String, Object> dataMap = new HashMap<>(10);
        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.NEWS.getValue());
        dataMap.put("banner", banner);

        List<News> newsList = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.NEWS.getId());

        for (Article article : articlePage.getRecords()) {
            News news = new News();
            news.setDesc(article.getTitle());
            news.setUrl("/article?id=" + article.getId());
            news.setSrc("static/images/icon/user.png");
            newsList.add(news);
        }

        dataMap.put("list", newsList);

        Map map = new HashMap(2);

        map.put("data", dataMap);
        return Rets.success(map);

    }
}
