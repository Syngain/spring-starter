package com.hualongdata.springstarter.web.controllers.inapi;

import com.hualongdata.springstarter.business.service.RoleService;
import com.hualongdata.springstarter.data.model.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/inapi/role")
@Api(value = "role-api", description = "有关于角色的CURD操作")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(path = "create", method = RequestMethod.POST)
    @ApiOperation(value = "创建角色", notes = "创建角色")
    public Role createRole(@RequestBody Role role) {
        System.out.println(role);
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());
        return roleService.crateRole(role).get();
    }


    @RequestMapping(path = "update", method = RequestMethod.PUT)
    @ApiOperation(value = "角色信息更新", notes = "角色信息更新")
    public ResponseEntity<Role> updateRole(@ApiParam(required = true, name = "role", value = "跟新后的角色json数据")
                                           @RequestBody Role role) {
        role.setCreatedAt(roleService.findRoleById(role.getId()).get().getCreatedAt());;
        role.setUpdatedAt(LocalDateTime.now());
        return roleService.updateRole(role)
                .map(r -> new ResponseEntity<Role>(r, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Role>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/{roleId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据ID获得角色信息", notes = "根据ID获得角色信息")
    public ResponseEntity<Role> get(@PathVariable("roleId") Integer roleId) {
        return  roleService.findRoleById(roleId)
                .map(role -> new ResponseEntity<Role>(role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<Role>(HttpStatus.NOT_FOUND));

    }


    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "获得所有角色信息", notes = "获得所有角色信息")
    public ResponseEntity<List<Role>> getAll() {
        return  roleService.findRoleAll()
                .map(role -> new ResponseEntity<List<Role>>(role, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<List<Role>>(HttpStatus.NOT_FOUND));

    }

    @RequestMapping(path = "/get/{roleId}",method = RequestMethod.GET)
    @ApiOperation(value = "根据ID获得角色信息", notes = "根据ID获得角色信息")
    public ResponseEntity<Role> delate(@PathVariable("roleId") Integer roleId) {
                roleService.delateRole(roleId);
        return new ResponseEntity<Role>(HttpStatus.OK);

    }
}
