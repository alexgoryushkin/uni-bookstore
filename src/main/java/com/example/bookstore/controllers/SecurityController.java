package com.example.bookstore.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.config.ApiTags;
import com.example.bookstore.dto.NewUserDto;
import com.example.bookstore.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер по работе с пользователями
 */
@Api(tags = ApiTags.SECURITY)
@RestController
@RequestMapping(path = "/security", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authManager;
    
    /**
     * Регистрирует пользователя
     * 
     * @param userData данные пользователя
     */
    @ApiOperation(
            value = "Регистрация пользователя", 
            notes = "Регистрирует пользователя в системе")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Пользователь зарегистрирован"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Некорректные данные в запросе"),
    })
    @RequestMapping(path = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signUp(@RequestBody @ApiParam(name = "userData", value = "регистрационные данные пользователя", required = true) NewUserDto userData) {
        userService.createUser(userData);
    }
    
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public void signIn(@RequestParam("login") String login, @RequestParam("password") String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                login, password);
        //userService.loadUserByUsername(login);
        Authentication authentication = authManager.authenticate(token);
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
