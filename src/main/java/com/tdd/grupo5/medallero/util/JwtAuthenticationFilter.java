package com.tdd.grupo5.medallero.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.exception.dto.ErrorResponse;
import com.tdd.grupo5.medallero.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final UserService userService;
  private final ObjectMapper objectMapper;

  public JwtAuthenticationFilter(
      final JwtService jwtService, final UserService userService, final ObjectMapper objectMapper) {
    this.jwtService = jwtService;
    this.userService = userService;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) {
    final String authHeader = request.getHeader("X-Auth-Token");
    try {
      if (!StringUtils.hasLength(authHeader)
          || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
        filterChain.doFilter(request, response);
        return;
      }
      final String jwt = authHeader.substring(7);
      final String userName = jwtService.extractUserName(jwt);
      if (StringUtils.hasLength(userName)
          && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        User user = userService.internalGetUser(userDTO);
        UserDetails userDetails = userService.userDetailsService(user).loadUserByUsername(userName);
        if (jwtService.isTokenValid(jwt, userDetails)) {
          SecurityContext context = SecurityContextHolder.createEmptyContext();
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          context.setAuthentication(authToken);
          SecurityContextHolder.setContext(context);
        }
      }
      filterChain.doFilter(request, response);
    } catch (RuntimeException | ServletException | IOException e) {
      try {
        response
            .getWriter()
            .write(
                objectMapper.writeValueAsString(
                    new ErrorResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", e.getMessage())));
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }
}
