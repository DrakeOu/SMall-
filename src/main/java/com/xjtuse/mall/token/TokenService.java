package com.xjtuse.mall.token;

import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.redisService.RedisService;
import com.xjtuse.mall.utils.MD5Util;
import com.xjtuse.mall.utils.UUIDUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.parser.Token;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@Service
public class TokenService {
    @Autowired
    RedisService redisService;

    private static final String SALT = "this's salt";

    private static final int EXPIRE_SECOND = 24 * 60 * 60;

    /**
     * 用户生成并保存token
     * @param user
     * @return
     */
    public String putToken(User user){
        //tokenCode是缓存的key
        String tokenCode = createTokenCode(user);
        //生成token
        String token = createToken(tokenCode, user.getUsername(), EXPIRE_SECOND);
        //有未过期的历史token,删除
        if(redisService.exists(tokenCode)){
            redisService.del(tokenCode);
        }
        //缓存token
        redisService.setExpire(tokenCode, token, EXPIRE_SECOND);
        return tokenCode+"="+token;
    }

    public boolean checkToken(String token){
        //计算用户的token key token
        String tokenCode = splitTokenCode(token);
        String realToken = splitToken(token);
        //token错误
        if(tokenCode == null || realToken == null) return false;
        //如果用户token存在，且token正确
        if(redisService.exists(tokenCode) && redisService.get(tokenCode).equals(realToken)){
            redisService.setExpire(tokenCode, realToken, EXPIRE_SECOND);
            return true;
        }
        return false;
    }

    public void dropToken(String token){
        //计算用户的token key
        String tokenCode = splitTokenCode(token);
        //设置立即过期
        redisService.del(tokenCode);
    }

    private String splitTokenCode(String token){
        String[] strings = token.split("=");
        if(strings.length == 2)
            return strings[0];
        return null;
    }

    private String splitToken(String token){
        String[] strings = token.split("=");
        if(strings.length == 2)
            return strings[1];
        return null;
    }

    private String createTokenCode(User user){
        //计算用户token 存在redis中的key值，每个用户的key应该是固定的
        String tokenCode = null;
        if(user!=null){
            tokenCode = MD5Util.MD5Encode(user.getId()+SALT, "UTF-8");
        }
        return tokenCode;
    }



    private static String createToken(String userId, String userName, int expireSecond) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("asd123");//RSAUtil.privateKey为私钥

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .claim("userId", userId) // 设置载荷信息
                .claim("userName", userName)
                .signWith(signatureAlgorithm, signingKey);

        //生成JWT
        return builder.compact();
    }


}
