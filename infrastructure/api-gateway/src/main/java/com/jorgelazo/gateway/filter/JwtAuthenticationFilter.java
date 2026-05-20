package com.jorgelazo.gateway.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtAuthenticationFilter extends
        AbstractGatewayFilterFactory<
                JwtAuthenticationFilter.Config> {

    private final SecretKey key =
            Keys.hmacShaKeyFor(
                    "supersecretkeysupersecretkey123456".getBytes()
            );

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            String authHeader =
                    exchange.getRequest()
                            .getHeaders()
                            .getFirst("Authorization");

            if (authHeader == null ||
                    !authHeader.startsWith("Bearer ")) {

                exchange.getResponse()
                        .setStatusCode(
                                HttpStatus.UNAUTHORIZED
                        );

                return exchange.getResponse()
                        .setComplete();
            }

            String token =
                    authHeader.substring(7);

            try {

                Jwts.parser()
                        .verifyWith(key)
                        .build()
                        .parseSignedClaims(token);

            } catch (Exception ex) {

                exchange.getResponse()
                        .setStatusCode(
                                HttpStatus.UNAUTHORIZED
                        );

                return exchange.getResponse()
                        .setComplete();
            }

            return chain.filter(exchange);
        };
    }
}