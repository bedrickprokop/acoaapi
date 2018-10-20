package br.com.acoaapi.config.security.auth.jwt.extractor;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * JwtHeaderTokenExtractor é uma simples classe usada para extrair o cabeçalho de autorização do
 * token.
 */
//@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {

    public static String HEADER_PREFIX = "Bearer ";

    @Override
    public String extract(String header) {
        if (header.isEmpty()) {
            throw new AuthenticationServiceException("Authorization header cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
