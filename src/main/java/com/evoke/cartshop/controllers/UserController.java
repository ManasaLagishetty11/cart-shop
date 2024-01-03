package com.evoke.cartshop.controllers;


import com.evoke.cartshop.dto.LoginDto;
import com.evoke.cartshop.dto.UserDto;
import com.evoke.cartshop.mappers.UserMapper;
import com.evoke.cartshop.models.User;
import com.evoke.cartshop.security.JwtHelper;
import com.evoke.cartshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        HttpHeaders responseHeaders = getHttpHeaders(mapper.toEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(mapper.toDto(userService.createUser(mapper.toEntity(userDto))));
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserDto> accountLogin(@RequestBody LoginDto loginDto) {
        User user = userService.findByEmail(loginDto.getEmail());
        HttpHeaders responseHeaders = getHttpHeaders(user);
        return ResponseEntity.ok().headers(responseHeaders).body(mapper.toDto(user));
    }

    private HttpHeaders getHttpHeaders(User user) {
        String token = jwtHelper.generateToken(user);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + token);
        return responseHeaders;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
      return ResponseEntity.ok(mapper.toDto(userService.getUser(id)));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

    }

}






