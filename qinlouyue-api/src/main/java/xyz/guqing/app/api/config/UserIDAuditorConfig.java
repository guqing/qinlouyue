package xyz.guqing.app.api.config;

import xyz.guqing.app.security.JwtUtil;
import xyz.guqing.app.utils.Constants;
import xyz.guqing.app.utils.HttpKit;
import xyz.guqing.app.utils.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 审计功能配置
 *
 * @author enilu
 * @version 2019/1/8 0008
 */
@Configuration
public class UserIDAuditorConfig implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        try {
            String token = HttpKit.getRequest().getHeader(Constants.TOKEN_NAME);
            if (StringUtils.isNotEmpty(token)) {
                return Optional.of(JwtUtil.getUserId(token));
            }
        }catch (Exception e){
            //返回系统用户id
            return Optional.of(Constants.SYSTEM_USER_ID);
        }
        //返回系统用户id
        return Optional.of(Constants.SYSTEM_USER_ID);
    }
}
