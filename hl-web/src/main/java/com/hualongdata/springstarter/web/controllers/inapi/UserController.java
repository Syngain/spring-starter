package com.hualongdata.springstarter.web.controllers.inapi;

import com.hualongdata.springstarter.business.service.UserService;
import com.hualongdata.springstarter.common.domain.AuthToken;
import com.hualongdata.springstarter.data.domain.SignRequest;
import com.hualongdata.springstarter.data.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangbajing on 16-9-5.
 */
@RestController
@RequestMapping("/inapi/user")
@Api(value = "user-api", description = "有关于用户的CURD操作")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    @ApiOperation(value = "注册用户", notes = "注册用户")
    public User createUser(@RequestBody SignRequest signRequest) {
        return userService.signup(signRequest);
    }

    @RequestMapping(path = "update", method = RequestMethod.PUT)
    @ApiOperation(value = "用户信息更新", notes = "用户信息更新")
    public ResponseEntity<User> updateUser(@ApiParam(required = true, name = "payload", value = "用户信息json数据")
                                           @RequestBody User payload) {
        return userService.updateUser(payload)
                .map(user -> new ResponseEntity<User>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "获得当前用户基本信息", notes = "包括名字，角色，用户状态，创建时间等")
    public ResponseEntity<User> get() {
        return AuthToken
                .getFromThreadLocal()
                .flatMap(token -> userService.findUserById(token.ownerId))
                .map(user -> new ResponseEntity<User>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<User>(HttpStatus.NOT_FOUND));

    }
}
