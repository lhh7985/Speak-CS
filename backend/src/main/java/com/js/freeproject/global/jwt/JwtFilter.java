package com.js.freeproject.global.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.js.freeproject.domain.user.appliction.UserService;
import com.js.freeproject.domain.user.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends BasicAuthenticationFilter {
	
	private UserService userService;
	
	public JwtFilter(AuthenticationManager authenticationManager, UserService userService) {
		super(authenticationManager);
		this.userService = userService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = resolveToken(request);
		if(token == null) {
			chain.doFilter(request, response);
			return;
		}
		
		try {
			Authentication authentication = getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("Security Context에 {} 인증정보를 저장했습니다.",authentication.getName());
		} catch(Exception e) {
			log.info("JWT 검증에 실패하였습니다.");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	private String resolveToken(HttpServletRequest request) {
		String header = request.getHeader(TokenProvider.HEADER_STRING);
		
		if(header != null && header.startsWith(TokenProvider.TOKEN_PREFIX)) {
			return header;
		}
		return null;
	}
	
	@Transactional(readOnly = true)
    public Authentication getAuthentication(String token) throws Exception {
        // 요청 헤더에 Authorization 키값에 jwt 토큰이 포함된 경우에만, 토큰 검증 및 인증 처리 로직 실행.
        if (token != null) {
            // parse the token and validate it (decode)
            JWTVerifier verifier = TokenProvider.getVerifier();
            TokenProvider.handleError(token);
            DecodedJWT decodedJWT = verifier.verify(token.replace(TokenProvider.TOKEN_PREFIX, ""));
            String email = decodedJWT.getSubject();
            
            // Search in the DB if we find the user by token subject (username)
            // If so, then grab user details and create spring auth token using username, pass, authorities/roles
            if (email != null) {
                    // jwt 토큰에 포함된 계정 정보(userId) 통해 실제 디비에 해당 정보의 계정이 있는지 조회.
            	User user = userService.findByUserEmail(email);
                if(user != null) {
                        // 식별된 정상 유저인 경우, 요청 context 내에서 참조 가능한 인증 정보(jwtAuthentication) 생성.
                		CustomUserDetails accountDetails = new CustomUserDetails(user);
                		UsernamePasswordAuthenticationToken jwtAuthentication = new UsernamePasswordAuthenticationToken(email,
                				null, accountDetails.getAuthorities());
                		jwtAuthentication.setDetails(accountDetails);
                		return jwtAuthentication;
                }
            }
            return null;
        }
        return null;
    }
}
