package br.com.acoaapi.config.security.auth.jwt.verifier;

/**
 *
 */
public interface TokenVerifier {
    public boolean verify(String jti);
}
