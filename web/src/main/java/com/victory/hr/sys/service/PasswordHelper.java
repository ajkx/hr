package com.victory.hr.sys.service;

import com.victory.hr.sys.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * 加密密码
 *
 * @author ajkx_Du
 * @create 2016-11-03 20:40
 */
@Service
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    //读取property配置文件设置加密方式
    //@Value("${password.algorithmName}")
    private String algorithmName = "md5";
    //@Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {
        //设置user的加密盐
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations
        ).toHex();
        user.setPassword(newPassword);
    }

    public String encryptPassword(String password,String salt){
        String newPassword = new SimpleHash(algorithmName,
                password,
                ByteSource.Util.bytes(salt),
                hashIterations).toHex();
        return newPassword;
    }
}
