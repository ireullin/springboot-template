package com.ireullin.springboottemplate;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ireullin.springboottemplate.components.JwtUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest // 有這個@Autowired等功能才會生效
@DisplayName("測試JWT")
public class JwtUtilsTest {
    
    @Autowired
    JwtUtils jwtUtils;

    @Test
    void testCreateToken(){

        var actual = jwtUtils.createToken("fuck");
        var expected = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmdWNrIiwiZXhwIjoxNjU4OTEzMzc5fQ.Riq1oZuY9HVgcHEYAA34ITmTLmSnTDKLtai0Lq0Lnhbl1SzefWXtxrywBoeVaT-AzoTAxC9rQ-Mc_dzWFsutGw";
        log.info("token: "+ expected);
        assertEquals(expected, actual);
        
    }

    @Test
    void testValidateToken(){

        var expected = "fuck";
        var actual = assertDoesNotThrow(()->{
            var token = jwtUtils.createToken(expected);
            return jwtUtils.validateToken(token);
        });
        assertEquals(expected, actual);
    }

    @Test
    void testValidateExpiredToken(){

        assertThrows(TokenExpiredException.class, ()->{
            var expected = "fuck";
            var token = jwtUtils.createToken(expected);
            Thread.sleep(60*1000);
            var actual = jwtUtils.validateToken(token);
        });
    }

    @Test
    void testValidateTokenFailed() throws Exception{
        
        assertThrows(JWTDecodeException.class, ()->{
            var expected = "fuck";
            var token = jwtUtils.createToken(expected);
            var actual = jwtUtils.validateToken("mother fucker");
        });
    }
}
