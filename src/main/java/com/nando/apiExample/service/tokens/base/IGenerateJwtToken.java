package com.nando.apiExample.service.tokens.base;

import com.nando.apiExample.model.dto.UserDto;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.JWTClaimsSet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Map;

public interface IGenerateJwtToken {
    String generateJwtToken(UserDto userDto) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, JOSEException;
    String generateRefreshJwtToken(UserDto userDto);
    JWTClaimsSet getJwtClaimsSet(String token) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, ParseException, JOSEException;
}
