package com.js.freeproject.global.jwt;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureGenerationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;


@Component
public class TokenProvider {
	
	private static String secret;
	private static long expiration;
	private static long refresh_expiration;
	
	public static final String TOKEN_PREFIX= "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public TokenProvider(@Value("${jwt.secret}")String secret,@Value("${jwt.expiration}") long expiration, @Value("${jwt.refresh_expiration}")long refresh_expiration) {
		TokenProvider.secret = secret;
		TokenProvider.expiration = expiration;
		TokenProvider.refresh_expiration = refresh_expiration;
	}
	
	public static JWTVerifier getVerifier() {
        return JWT
                .require(Algorithm.HMAC512(secret.getBytes()))
                .build();
    }
	
	public static String generateToken(String email) {
		return getToken(email,expiration);
	}
	
	public static String generateRefreshToken(String email) {
		return getToken(email,refresh_expiration);
	}
	
	public static String getToken(String email, long expire) {
		return JWT.create()
				.withSubject(email)
				.withExpiresAt(new Date(System.currentTimeMillis() + expire))
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.sign(Algorithm.HMAC512(secret.getBytes()));
	}
    
    public static String getToken(String email) {
    		Date expires = TokenProvider.getTokenExpiration(expiration);
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(expires)
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    public static String getToken(Instant expires, String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(Date.from(expires))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }
    
    public static Date getTokenExpiration(long expiration) {
    		Date now = new Date();
    		return new Date(now.getTime() + expiration);
    }

    public static void handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secret.getBytes()))
                .build();

        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void handleError(JWTVerifier verifier, String token) {
        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
