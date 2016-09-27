package com.hualongdata.springstarter.web.controllers.sign

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import com.hualongdata.springstarter.business.service.UserService
import com.hualongdata.springstarter.common.domain.AuthToken
import com.hualongdata.springstarter.data.domain.SignRequest
import com.hualongdata.springstarter.data.model.User
import com.hualongdata.springstarter.web.components.HlTokenComponent
import com.hualongdata.springstarter.web.utils.WebUtils
import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._

/**
  * Created by yangbajing on 16-9-2.
  */

@RestController
@RequestMapping(Array("/sign"))
@Api(value = "sign-api", description = "系统认证相关操作")
class SignController @Autowired()(userService: UserService,
                                  webUtils: WebUtils,
                                  ccTokenComponent: HlTokenComponent) {

  @RequestMapping(path = Array("signin"), method = Array(RequestMethod.POST))
  @ApiOperation(value = "用户登录", notes = "用户登录")
  def signin(@RequestBody signRequest: SignRequest,
             request: HttpServletRequest,
             response: HttpServletResponse): ResponseEntity[User] = {
    Option(userService.signin(signRequest)) match {
      case Some(user) =>
        val roleIds = user.getRoles.asScala.map(_.getId).asJava
        val authToken = AuthToken.create(user.getId, webUtils.getIpFromRequest(request), roleIds)
        ccTokenComponent.storeAuthTokenToHttpResponse(response, authToken)
        new ResponseEntity[User](user, HttpStatus.OK);

      case None =>
        new ResponseEntity[User](HttpStatus.NOT_FOUND)
    }
  }

  @RequestMapping(path = Array("signup"), method = Array(RequestMethod.POST))
  @ApiOperation(value = "用户注册", notes = "用户注册")
  def signup(@RequestBody signRequest: SignRequest): ResponseEntity[User] = {
    val user = userService.signup(signRequest)
    new ResponseEntity[User](user, HttpStatus.CREATED)
  }

  @RequestMapping(path = Array("signout"), method = Array(RequestMethod.DELETE))
  @ApiOperation(value = "用户登出")
  def signout(response: HttpServletResponse): ResponseEntity[Void] = {
    val cookie = ccTokenComponent.discardCookieFromToken()
    response.addCookie(cookie)
    new ResponseEntity(HttpStatus.OK)
  }

}
