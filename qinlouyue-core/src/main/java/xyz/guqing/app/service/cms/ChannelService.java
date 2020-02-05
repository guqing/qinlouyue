package xyz.guqing.app.service.cms;

import xyz.guqing.app.bean.entity.cms.Channel;
import xyz.guqing.app.dao.cms.ChannelRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.stereotype.Service;

/**
 * 文章频道service
 *
 * @author ：enilu
 * @date ：Created in 2019/6/30 13:06
 */
@Service
public class ChannelService extends BaseService<Channel,Long, ChannelRepository> {
}
