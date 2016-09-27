/**
  * Created by yangbajing on 16-9-2.
  */
package com.hualongdata.springstarter.data.domain

import scala.beans.BeanProperty

/**
  * 用户认证请求，用于登录、注册
  *
  * @param account  账号
  * @param password 密码
  * @param captcha  图片验证码[可选]
  * @param smsCode  短信验证码[可选]
  */
case class SignRequest(@BeanProperty
                       account: String,

                       @BeanProperty
                       password: String,

                       @BeanProperty
                       captcha: String,

                       @BeanProperty
                       smsCode: String) {

  def this() {
    this("", "", "", "")
  }

  def isEmail = account.contains('@')

  def isPhone = account.forall(Character.isDigit)


}
