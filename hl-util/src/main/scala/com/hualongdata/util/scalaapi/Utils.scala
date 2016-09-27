package com.hualongdata.util.scalaapi

import org.apache.commons.codec.digest.DigestUtils

import scala.util.Random

/**
  * Created by yangbajing on 16-8-31.
  */
abstract class BaseUtils {

  def randomString(length: Int): String = {
    (0 until length).map(_ => Random.nextPrintableChar()).mkString
  }

  def generateSaltAndPassword(originalPassword: String): Tuple2[String, String] = {
    val salt = randomString(12)
    val password = DigestUtils.sha256Hex(salt + originalPassword)
    (salt, password)
  }

  def saltSecretPassword(salt: String, originalPassword: String): String = {
    DigestUtils.sha256Hex(salt + originalPassword)
  }

  def matchPassword(salt: String, encryptedPassword: String, originalPassword: String): Boolean = {
    saltSecretPassword(salt, originalPassword) == encryptedPassword
  }

}

object Utils extends BaseUtils {

}
