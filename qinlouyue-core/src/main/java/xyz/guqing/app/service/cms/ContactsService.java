package xyz.guqing.app.service.cms;

import xyz.guqing.app.bean.entity.cms.Contacts;
import xyz.guqing.app.dao.cms.ContactsRepository;
import xyz.guqing.app.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class ContactsService extends BaseService<Contacts,Long,ContactsRepository> {
}
