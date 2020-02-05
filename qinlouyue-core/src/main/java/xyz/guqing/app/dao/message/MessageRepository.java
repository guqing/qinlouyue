package xyz.guqing.app.dao.message;


import xyz.guqing.app.bean.entity.message.Message;
import xyz.guqing.app.dao.BaseRepository;

import java.util.ArrayList;


public interface MessageRepository extends BaseRepository<Message,Long> {
    void deleteAllByIdIn(ArrayList<String> list);
}

