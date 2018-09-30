package com.ks.controller;

import com.ks.model.User;
import com.ks.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value="/users",description="User profile",produces ="application/json")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value="get users")
    @ApiResponses(value={
            @ApiResponse(code=200,message="OK"),
            @ApiResponse(code=500,message="Internal Server Error")
    })
    public List<User> list() {
        return userService.list();
    }


}
