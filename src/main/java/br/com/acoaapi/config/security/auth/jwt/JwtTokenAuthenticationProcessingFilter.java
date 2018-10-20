package br.com.acoaapi.config.security.auth.jwt;

import br.com.acoaapi.config.WebSecurityConfig;
import br.com.acoaapi.config.security.auth.JwtAuthenticationToken;
import br.com.acoaapi.config.security.auth.jwt.extractor.TokenExtractor;
import br.com.acoaapi.config.security.model.token.RawAccessJwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * O filtro JwtTokenAuthenticationProcessingFilter é aplicado para cada API(/api/**) com
 * exceção do endpoint de refresh token(api/auth/token) e login endpoint(/api/auth/login)
 * <p>
 * Este filtro tem as seguintes responsabilidades:
 * 1. Verificar o cabeçalho X-Authorization do token de acesso. Se o token de acesso for encontrado
 * então deleta a autenticação para JwtAuthenticationProvider, caso contrário lança uma exceção
 * de autenticação
 * <p>
 * 2. Invoca estratégias de sucesso ou falha com base no processo de autenticação realizado pelo
 * JwtAuthenticationProvider
 * <p>
 * Assegure-se de que chain.doFilter (request, response) seja invocado na autenticação
 * bem-sucedida. Você deseja que o processamento da solicitação avance para o próximo filtro,
 * porque o último filtro, FilterSecurityInterceptor # doFilter, é responsável por invocar o
 * método em seu controlador que está manipulando o recurso de API solicitado.
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationFailureHandler failureHandler;
    private final TokenExtractor tokenExtractor;

    //@Autowired
    public JwtTokenAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor, RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.tokenExtractor = tokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(WebSecurityConfig.AUTHENTICATION_HEADER_NAME);
        RawAccessJwtToken token = new RawAccessJwtToken(tokenExtractor.extract(tokenPayload));
        return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
