package com.example.LOGITRACK.filter;

import com.example.LOGITRACK.config.JwtUtils;
import com.example.LOGITRACK.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtUtils jwtUtils;

    private final CustomUserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {


        String path = request.getServletPath();

        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }


        String authHeader = request.getHeader("Authorization");


        String token = null;

        String email = null;


        if(authHeader != null && authHeader.startsWith("Bearer ")){

            token = authHeader.substring(7);

            email = jwtUtils.extractUsername(token);
        }


        if(email != null &&
                SecurityContextHolder.getContext()
                        .getAuthentication() == null){


            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);


            if(jwtUtils.validateToken(token, userDetails)){


                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );


                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
            }
        }


        filterChain.doFilter(request,response);
    }
}