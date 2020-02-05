package xyz.guqing.app.bean.vo.business;
import lombok.Data;

@Data
public class LoginVo{
    private String username;
    private String password;
    private String captchaCode;
    private String captchCodeId;
}