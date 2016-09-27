package com.hualongdata.util.scalaapi.poi

import java.io.File

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.scalatest.WordSpec

/**
  * Created by yangbajing on 16-9-26.
  */
class ExcelUtilsTest extends WordSpec {

  "ExcelUtilsTest" should {

    "main" in {
      val workbook = WorkbookFactory.create(new File("/home/yangbajing/Dropbox/Works/hldata/信用重庆/信息平台/行政处罚等信用信息数据导入模板.xlsx")).asInstanceOf[XSSFWorkbook]

      val iterable = ExcelUtils.getSheetCells(workbook, "行政处罚表")

      for (row <- iterable) {
        for (cell <- row) {
          print(cell + "\t")
        }
        println()
      }
    }

  }
}
