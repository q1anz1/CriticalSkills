package com.equestria.criticalskills.criticalskills.utils;

import cn.hutool.core.util.RandomUtil;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JsonWebTokenUtil {

    private String secret = RandomUtil.randomString(64);

    private Key getSignedKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String subject, Map<String, Object> claims, long expiredMinutes){
        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuer("equestria")
                    .setIssuedAt(new Date())
                    .addClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis()+expiredMinutes*60*1000))
                    .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                    .compact();
        }catch (Exception e){
        }
        return null;
    }

    public Claims getTokenClaims(String token){
        JwtParserBuilder builder=Jwts.parserBuilder();
        JwtParser parser=builder.setSigningKey(getSignedKey()).build();
        try {
            return parser.parseClaimsJws(token).getBody();
        }catch (Exception e){
        }
        return null;
    }

    public boolean verifyToken(String token){
        Claims claims=getTokenClaims(token);
        if(claims==null){return false;}
        if (claims.getExpiration().before(new Date())){return false;}
        return claims.getIssuer().equals("equestria");

    }



}
