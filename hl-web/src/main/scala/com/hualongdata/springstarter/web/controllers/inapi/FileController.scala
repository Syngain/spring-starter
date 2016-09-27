package com.hualongdata.springstarter.web.controllers.inapi

import com.hualongdata.springstarter.common.domain.AuthToken
import com.hualongdata.util.poi.ExcelHelper
import io.swagger.annotations.Api
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{PostMapping, RequestMapping, RequestParam, RestController}
import org.springframework.web.multipart.MultipartFile

case class FileResponse(filename: String,
                        contentType: String,
                        size: Long)

/**
  * 文件上传
  * Created by yangbajing on 16-9-20.
  */
@RestController
@RequestMapping(path = Array("/inapi/file"))
@Api(value = "file-api")
class FileController @Autowired()(excelHelper: ExcelHelper) {

  @PostMapping(path = Array("upload"))
  def upload(@RequestParam("file") file: MultipartFile) = {
    println(file.getName)
    println(file.getOriginalFilename)
    println(file.getContentType)
    println(file.getSize)
    val authToken = AuthToken.getFromThreadLocal.get()
    println(authToken)
    FileResponse(file.getOriginalFilename, file.getContentType, file.getSize)
  }

  @PostMapping(path = Array("upload_excel"))
  def uploadExcel(@RequestParam(required = false) sheet: Array[String],
                  @RequestParam("file") file: MultipartFile) = {
    val workbook = WorkbookFactory.create(file.getInputStream)
    if (sheet ne null) {
      sheet.map(sheetName => excelHelper.toSheetData(workbook.getSheet(sheetName)))
    } else {
      excelHelper.getSheetDatas(workbook)
    }
  }

}
