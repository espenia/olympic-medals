package com.tdd.grupo5.medallero.config;

import com.tdd.grupo5.medallero.controller.dto.UserDTO;
import com.tdd.grupo5.medallero.entities.User;
import com.tdd.grupo5.medallero.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Primary
public class JwtAuthenticationFilterTest extends OncePerRequestFilter {
  private final UserService userService;

  public JwtAuthenticationFilterTest(final UserService userService) {
    this.userService = userService;
  }

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDTO userDTO = new UserDTO();
      userDTO.setUserName("test");
      User user = userService.internalGetUser(userDTO);
      UserDetails userDetails = userService.userDetailsService(user).loadUserByUsername("test");
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      context.setAuthentication(authToken);
      SecurityContextHolder.setContext(context);
    }
    filterChain.doFilter(request, response);
  }
}
