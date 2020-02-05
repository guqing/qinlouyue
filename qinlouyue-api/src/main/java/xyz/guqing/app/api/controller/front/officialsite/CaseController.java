package xyz.guqing.app.api.controller.front.officialsite;

import xyz.guqing.app.api.controller.BaseController;
import xyz.guqing.app.bean.entity.cms.Article;
import xyz.guqing.app.bean.enumeration.cms.BannerTypeEnum;
import xyz.guqing.app.bean.enumeration.cms.ChannelEnum;
import xyz.guqing.app.bean.vo.front.Rets;
import xyz.guqing.app.bean.vo.offcialsite.BannerVo;
import xyz.guqing.app.bean.vo.offcialsite.Product;
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
@RequestMapping("/offcialsite/case")
public class CaseController extends BaseController {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET)
    public Object index() {
        Map<String, Object> dataMap = new HashMap<>(2);

        BannerVo banner = bannerService.queryBanner(BannerTypeEnum.CASE.getValue());
        dataMap.put("banner", banner);

        List<Product> products = new ArrayList<>();
        Page<Article> articlePage = articleService.query(1, 10, ChannelEnum.PRODUCT.getId());
        for (Article article : articlePage.getRecords()) {
            products.add(new Product(article.getId(), article.getTitle(), article.getImg()));
        }
        dataMap.put("caseList", products);


        Map map = new HashMap(1);

        map.put("data", dataMap);
        return Rets.success(map);

    }
}
