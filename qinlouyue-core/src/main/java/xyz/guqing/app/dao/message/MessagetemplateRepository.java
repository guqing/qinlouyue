package xyz.guqing.app.dao.message;


import xyz.guqing.app.bean.entity.message.MessageTemplate;
import xyz.guqing.app.dao.BaseRepository;

import java.util.List;


public interface MessagetemplateRepository extends BaseRepository<MessageTemplate,Long> {
    MessageTemplate findByCode(String code);

    List<MessageTemplate> findByIdMessageSender(Long idMessageSender);
}

