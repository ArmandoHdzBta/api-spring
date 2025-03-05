package com.nando.apiExample.service.tokens;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.service.tokens.base.IGenerateJwtToken;
import com.nando.apiExample.service.tokens.base.Rsa;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Date;

@Service
public class GenerateJwtToken extends Rsa implements IGenerateJwtToken {
    @Value("classpath:jwtKeys/private_key.pem")
    private Resource privateKeyFile;

    @Value("classpath:jwtKeys/public_key.pem")
    private Resource publicKeyFile;

    @Value("${jwt.secret.expired}")
    private Long secretExpiresAt;

    @Override
    public String generateJwtToken(UserDto user) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException {
        PrivateKey privateKey = this.getPrivateKey(privateKeyFile);

        JWSSigner signed = new RSASSASigner(privateKey);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .expirationTime(new Date(System.currentTimeMillis() + this.secretExpiresAt))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

        signedJWT.sign(signed);

        return signedJWT.serialize();
    }

    @Override
    public JWTClaimsSet getJwtClaimsSet(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException {
        PublicKey publicKey = this.getPublicKey(publicKeyFile);

        SignedJWT signedJWT = SignedJWT.parse(token);

        JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);

        if(!signedJWT.verify(verifier)){
            throw new JOSEException("JWT verification failed");
        }

        JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

        if(claimsSet.getExpirationTime().before(new Date())){
            throw new JOSEException("JWT expired");
        }

        return claimsSet;
    }

    @Override
    public String generateRefreshJwtToken(UserDto user){
        return "";
    }
}
