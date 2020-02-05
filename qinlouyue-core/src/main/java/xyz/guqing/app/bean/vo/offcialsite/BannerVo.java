package xyz.guqing.app.bean.vo.offcialsite;

import lombok.Data;
import xyz.guqing.app.bean.entity.cms.Banner;

import java.util.List;

@Data
public class BannerVo {
    private Integer index = 0;
    private List<Banner> list;

}
