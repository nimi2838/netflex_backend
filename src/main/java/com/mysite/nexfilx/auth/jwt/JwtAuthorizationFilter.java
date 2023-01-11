package com.mysite.nexfilx.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysite.nexfilx.User.dao.UserRepository;
import com.mysite.nexfilx.User.domain.User;
import com.mysite.nexfilx.auth.PrincipalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    //권한체크 필터
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("JWTHEADER : " + jwtHeader);

        if(jwtHeader == null || !jwtHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
            System.out.println("토큰 없음");
            chain.doFilter(request, response);
            return;
        }
        String token = request.getHeader("Authorization").replace(JwtProperties.TOKEN_PREFIX, "");
//        //여기서의 JwtProperties.TOKEN_PREFIX == "Bearer "

        String username = null;

        try {
            username = JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(token).getClaim("username").asString();
            System.out.println("username : " + username);
        } catch (TokenExpiredException e) {
            System.out.println("토큰 만료됨");
            ResponseDto responseDto = ResponseDto.builder()
                    .code("TOKEN-0001")
                    .message("token has expired")
                    .build();
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), responseDto);
        }
        if (username != null) {
            Optional<User> user = userRepository.findByUseremail(username);
            System.out.println("User: " + user);
            PrincipalDetails principalDetails = new PrincipalDetails(user.get());
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("principalDetails : " + principalDetails);
//            chain.doFilter(request, response);
        }

        super.doFilterInternal(request, response, chain);
    }


}
