package com.hualongdata.util.scalaapi.poi

import com.hualongdata.util.poi.ExcelCellType
import org.apache.poi.ss.usermodel.{Row, Sheet}

import scala.collection.JavaConverters._

/**
  * Sheet Data
  * Created by yangbajing on 16-9-21.
  */

/**
  * Cell包装对象
  *
  * @param columnIndex 单元格索引，从0开始编号
  * @param value       单元格值
  * @param cellType    单元格类型
  * @param cell        单元格
  */
case class CellData(columnIndex: Int,
                    value: Any,
                    cellType: ExcelCellType,
                    @transient cell: RichCell)

/**
  * Row包装对象
  *
  * @param rowNum 行号，从0开始编号
  * @param cells  单元格列表
  * @param row    原始行
  */
case class RowData(rowNum: Int,
                   cells: Seq[CellData],
                   @transient row: Row) {
  def javaCells = cells.asJava
}

/**
  * Sheet包装对象
  *
  * @param sheetName Sheet名
  * @param rows      行列表
  * @param sheet     原始sheet
  */
case class SheetData(sheetName: String,
                     rows: Seq[RowData],
                     @transient sheet: Sheet) {
  def javaRows = rows.asJava
}

