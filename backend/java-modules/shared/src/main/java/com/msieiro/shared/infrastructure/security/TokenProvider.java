package com.msieiro.shared.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String JWT_SECRET_KEY = "akjldsfbgdsajbfgajdsnfgdsjfkng";
    private static final Long JWT_VALIDITY = 3600L;

    public boolean validateToken(String authToken) {
        Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(authToken);
        return true;
    }

    public Authentication getAuthentication(String authToken) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(authToken).getBody();
        GrantedAuthority authority = new SimpleGrantedAuthority(claims.get(AUTHORITIES_KEY).toString());
        Collection<GrantedAuthority> authorities = Collections.singleton(authority);
        User user = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(user, authToken, authorities);
    }

    public String createToken(Authentication authentication) {
        String authority = authentication.getAuthorities().iterator().next().toString();

        long now = (new Date()).getTime();
        Date validity = new Date(now + (JWT_VALIDITY * 1000));

        return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authority)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY).setExpiration(validity).compact();
    }
}
