package com.ekinsol.challenge.apiservice.config;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class AwsCognitoIdTokenProcessor {

    private final JwtConfiguration jwtConfiguration;

    private final ConfigurableJWTProcessor configurableJWTProcessor;

    public AwsCognitoIdTokenProcessor(final JwtConfiguration jwtConfiguration,
                                      final ConfigurableJWTProcessor configurableJWTProcessor) {
        this.jwtConfiguration = jwtConfiguration;
        this.configurableJWTProcessor = configurableJWTProcessor;
    }

    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String idToken = request.getHeader(this.jwtConfiguration.getHttpHeader());
        if (idToken != null) {
            JWTClaimsSet claims = this.configurableJWTProcessor.process(this.getBearerToken(idToken),null);
            validateIssuer(claims);
            verifyIfIdToken(claims);
            String username = getUserNameFrom(claims);
            if (username != null) {
                List<GrantedAuthority> grantedAuthorities = Collections.singletonList( new SimpleGrantedAuthority("ROLE_USER"));;
                List<String> userRoles = getGroupsFrom(claims);
                if(userRoles != null && !userRoles.isEmpty()) {
                    userRoles.stream().forEach(log::debug);
                    grantedAuthorities = Collections.singletonList( new SimpleGrantedAuthority("ROLE_ADMIN"));
                }
                User user = new User(username, "", Collections.emptyList());
                return new JwtAuthentication(user, claims, grantedAuthorities);
            }
        }
        return null;
    }

    private String getUserNameFrom(JWTClaimsSet claims) {
        return claims.getClaims().get(this.jwtConfiguration.getUserNameField()).toString();
    }

    private List<String> getGroupsFrom(JWTClaimsSet claims) {
        return (List<String>) claims.getClaims().get(this.jwtConfiguration.getGroupNameField());
    }

    private void verifyIfIdToken(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception("JWT Token is not an ID Token");
        }
    }

    private void validateIssuer(JWTClaimsSet claims) throws Exception {
        if (!claims.getIssuer().equals(this.jwtConfiguration.getCognitoIdentityPoolUrl())) {
            throw new Exception(String.format("Issuer %s does not match cognito idp %s", claims.getIssuer(), this.jwtConfiguration.getCognitoIdentityPoolUrl()));
        }
    }

    private String getBearerToken(String token) {
        return token.startsWith("Bearer ") ? token.substring("Bearer ".length()) : token;
    }
}