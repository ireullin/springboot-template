package com.ireullin.springboottemplate.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;

import lombok.Data;

@Component
public class JwtUtils {

    @Data
    @Component
    @ConfigurationProperties(prefix = "jwt")
    public static class JwtConf{    
    //定义token返回头部
        private String header;

        //token前缀
        private String tokenPrefix;

        //签名密钥
        private String secret;

        //有效期
        private long expireTime;
    }
    
    @Autowired
    private JwtConf jwtConf;


    /**
     * 创建TOKEN
     * @param sub
     * @return
     */
    public String createToken(String sub){
        return jwtConf.tokenPrefix + JWT.create()
                .withSubject(sub)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConf.expireTime))
                .sign(Algorithm.HMAC512(jwtConf.secret));
    }


    /**
     * 验证token
     * @param token
     */
    public String validateToken(String token) throws Exception{
        
        return JWT.require(Algorithm.HMAC512(jwtConf.secret))
                    .build()
                    .verify(token.replace(jwtConf.tokenPrefix, ""))
                    .getSubject();
        // } catch (TokenExpiredException e){
        //     throw new ApiException(ResultInfo.unauthorized("token已经过期"));
        // } catch (Exception e){
        //     throw new ApiException(ResultInfo.unauthorized("token验证失败"));
        // }
    }

    /**
     * 检查token是否需要更新
     */
    public boolean isNeedUpdate(String token){
        //获取token过期时间
        Date expiresAt = null;
        try {
            expiresAt = JWT.require(Algorithm.HMAC512(jwtConf.secret))
                    .build()
                    .verify(token.replace(jwtConf.tokenPrefix, ""))
                    .getExpiresAt();
        } catch (TokenExpiredException e){
            return true;
        } catch (Exception e){
            throw e;
        }
        //如果剩余过期时间少于过期时常的一般时 需要更新
        return (expiresAt.getTime()-System.currentTimeMillis()) < (jwtConf.expireTime>>1);
    }
}
