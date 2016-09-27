package com.hualongdata.util.scalaapi.poi

import java.io.File
import java.time.{LocalDateTime, ZoneId}

import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.collection.JavaConverters._

/**
  * Xlsx帮助函数
  * Created by yangbajing on 16-9-21.
  */
abstract class ExcelHelper {

  /**
    * 根据sheet名获取sheet所有单元格
    *
    * @param workbook  Excel [[Workbook]]对象
    * @param sheetName sheet 名
    * @return 返回所有有效单元格可迭代二维列表
    */
  def getSheetCells(workbook: Workbook, sheetName: String): Iterable[Iterable[RichCell]] = {
    workbook.getSheet(sheetName).asScala
      .map(row => row.asScala.map(cell => new RichCell(cell)))
  }


  /**
    * 权限sheet名获取sheet所有单元格
    *
    * @param workbook   Excel [[Workbook]]对象
    * @param sheetIndex sheet 序号
    * @return 返回所有有效单元格可迭代二维列表
    */
  def getSheetCellsAt(workbook: Workbook, sheetIndex: Int): Iterable[Iterable[RichCell]] = {
    workbook.getSheetAt(sheetIndex).asScala
      .map(row => row.asScala.map(cell => new RichCell(cell)))
  }

  /**
    * 将原始Cell对象包装成CellData对象
    *
    * @param cell 原始Cell对象
    * @return 包装后新的CellData对象
    */
  def toCellData(cell: Cell): CellData = {
    val richCell = new RichCell(cell)
    CellData(cell.getColumnIndex, richCell.value, richCell.getExcelCellType, richCell)
  }

  def getCellDatas(row: Row): Iterable[CellData] = row.asScala.map(cell => toCellData(cell))

  /**
    * 将原始Row对象包装成RowData对象
    *
    * @param row 原始Row对象
    * @return 包装后新的RowData对象
    */
  def toRowData(row: Row): RowData = {
    val cells = getCellDatas(row).toSeq
    RowData(row.getRowNum, cells, row)
  }

  def toSheetData(sheet: Sheet): SheetData = {
    val rows = sheet.asScala.map(row => toRowData(row)).toSeq
    SheetData(sheet.getSheetName, rows, sheet)
  }

  def getSheetDatas(workbook: Workbook): Seq[SheetData] = workbook.asScala.map(sheet => toSheetData(sheet)).toSeq

}

object ExcelUtils extends ExcelHelper {
}
