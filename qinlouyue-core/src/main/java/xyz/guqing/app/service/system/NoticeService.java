package xyz.guqing.app.service.system;

import xyz.guqing.app.bean.entity.system.Notice;
import xyz.guqing.app.dao.system.NoticeRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * descript
 *
 * @author ：enilu
 * @date ：Created in 2019/6/30 11:14
 */
@Service
public class NoticeService extends BaseService<Notice,Long, NoticeRepository> {
    @Autowired
    private NoticeRepository noticeRepository;
    public List<Notice> findByTitleLike(String title) {
        return noticeRepository.findByTitleLike("%"+title+"%");
    }
}
