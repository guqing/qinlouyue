package xyz.guqing.app.cache;

import xyz.guqing.app.bean.core.ShiroUser;
import xyz.guqing.app.cache.impl.EhcacheDao;
import xyz.guqing.app.utils.HttpKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户登录时，生成的Token与用户ID的对应关系
 */
@Service
public   class TokenCache {

    @Autowired
    private EhcacheDao ehcacheDao;
    public void put(String key,Object value){
        ehcacheDao.hset(EhcacheDao.SESSION,key,value);
    }
     public <T> T get(String key,Class<T> klass){
         return ehcacheDao.hget(EhcacheDao.SESSION, key,klass);
     }
    public   void put(String token, Long idUser) {
        ehcacheDao.hset(EhcacheDao.SESSION,token, idUser);
    }

    public   Long getToken(String token) {
        return ehcacheDao.hget(EhcacheDao.SESSION,token,Long.class);
    }
    public Long getIdUser(){
        return ehcacheDao.hget(EhcacheDao.SESSION, HttpKit.getToken(),Long.class );
    }

    public   void remove(String token) {
        ehcacheDao.hdel(EhcacheDao.SESSION,token+"user");
    }

    public void setUser(String token, ShiroUser shiroUser){
        ehcacheDao.hset(EhcacheDao.SESSION,token+"user",shiroUser);
    }
    public ShiroUser getUser(String token){
        return ehcacheDao.hget(EhcacheDao.SESSION,token+"user",ShiroUser.class);
    }
}
