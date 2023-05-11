package com.lili.lilichatgpt.service;

import com.lili.lilichatgpt.bean.LoginUser;
import com.lili.lilichatgpt.constant.Constant;
import com.lili.lilichatgpt.utils.JsonUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TokenService {

    public String getToken(LoginUser user) {
        return Jwts.builder()
                .setSubject(JsonUtil.toJson(user))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Constant.EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256,  Constant.SECRET_KEY)
                .compact();
    }

    public boolean isEffective(String token) {
        try {
            getSubject(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Constant.SECRET_KEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return claims.getSubject();
    }
}
