package org.example.features.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.GeneratedOneTimeTokenHandler;
import org.springframework.security.web.authentication.ott.RedirectGeneratedOneTimeTokenHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class MagicLinkGeneratedOneTimeTokenHandler implements GeneratedOneTimeTokenHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(MagicLinkGeneratedOneTimeTokenHandler.class);

    private final GeneratedOneTimeTokenHandler redirectHandler = new RedirectGeneratedOneTimeTokenHandler("/api/accounts");

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, OneTimeToken oneTimeToken) throws IOException, ServletException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(UrlUtils.buildFullRequestUrl(request))
                .replacePath(request.getContextPath())
                .replaceQuery(null)
                .fragment(null)
                .path("/login/ott")
                .queryParam("token", oneTimeToken.getTokenValue());
        String magicLink = builder.toUriString();
        LOGGER.info("Magic link: {}", magicLink);
        this.redirectHandler.handle(request, response, oneTimeToken);
    }
}
