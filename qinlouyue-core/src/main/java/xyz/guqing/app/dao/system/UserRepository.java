package xyz.guqing.app.dao.system;


import xyz.guqing.app.bean.entity.system.User;
import xyz.guqing.app.dao.BaseRepository;

/**
 * Created  on 2018/3/21 0021.
 *
 * @author enilu
 */
public interface UserRepository extends BaseRepository<User,Long> {
    User findByAccount(String account);

    User findByAccountAndStatusNot(String account, Integer status);
}
