package com.hualongdata.util.scalaapi.poi

import java.time.{LocalDateTime, ZoneId}
import java.util.{Calendar, Date}

import com.hualongdata.util.poi.ExcelCellType
import org.apache.poi.ss.usermodel._
import org.apache.poi.ss.util.{CellAddress, CellRangeAddress}

import scala.compat.java8.OptionConverters._

/**
  * poi Cell 包装
  * Created by yangbajing on 16-9-21.
  */
class RichCell(val cell: Cell) extends Cell {

  override def getHyperlink: Hyperlink = cell.getHyperlink

  override def removeHyperlink(): Unit = cell.removeHyperlink()

  override def getCellType: Int = cell.getCellType

  override def setCellErrorValue(value: Byte): Unit = cell.setCellErrorValue(value)

  override def setCellType(cellType: Int): Unit = cell.setCellType(cellType)

  override def getCellStyle: CellStyle = cell.getCellStyle

  override def getColumnIndex: Int = cell.getColumnIndex

  override def getCellFormula: String = cell.getCellFormula

  override def getCellComment: Comment = cell.getCellComment

  override def getStringCellValue: String = cell.getStringCellValue

  override def setCellStyle(style: CellStyle): Unit = cell.setCellStyle(style)

  override def setHyperlink(link: Hyperlink): Unit = cell.setHyperlink(link)

  override def isPartOfArrayFormulaGroup: Boolean = cell.isPartOfArrayFormulaGroup

  override def getRow: Row = cell.getRow

  override def removeCellComment(): Unit = cell.removeCellComment()

  override def getRichStringCellValue: RichTextString = cell.getRichStringCellValue

  override def setAsActiveCell(): Unit = cell.setAsActiveCell()

  override def getNumericCellValue: Double = cell.getNumericCellValue

  override def getBooleanCellValue: Boolean = cell.getBooleanCellValue

  override def getArrayFormulaRange: CellRangeAddress = cell.getArrayFormulaRange

  override def getErrorCellValue: Byte = cell.getErrorCellValue

  override def getCachedFormulaResultType: Int = cell.getCachedFormulaResultType

  override def getAddress: CellAddress = cell.getAddress

  override def getDateCellValue: Date = cell.getDateCellValue

  override def setCellFormula(formula: String): Unit = cell.setCellFormula(formula)

  override def setCellComment(comment: Comment): Unit = cell.setCellComment(comment)

  override def setCellValue(value: Double): Unit = cell.setCellValue(value)

  override def setCellValue(value: Date): Unit = cell.setCellValue(value)

  override def setCellValue(value: Calendar): Unit = cell.setCellValue(value)

  override def setCellValue(value: RichTextString): Unit = cell.setCellValue(value)

  override def setCellValue(value: String): Unit = cell.setCellValue(value)

  override def setCellValue(value: Boolean): Unit = cell.setCellValue(value)

  override def getRowIndex: Int = cell.getRowIndex

  override def getSheet: Sheet = cell.getSheet

  def value: Any = cell.getCellType match {
    case Cell.CELL_TYPE_BLANK =>
      cell.getStringCellValue

    case Cell.CELL_TYPE_BOOLEAN =>
      cell.getBooleanCellValue

    case Cell.CELL_TYPE_ERROR =>
      cell.getErrorCellValue

    case Cell.CELL_TYPE_FORMULA =>
      cell.getCellFormula

    case Cell.CELL_TYPE_NUMERIC =>
      if (DateUtil.isCellDateFormatted(cell)) {
        val instant = cell.getDateCellValue.toInstant
        LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
      } else {
        cell.getNumericCellValue
      }

    case Cell.CELL_TYPE_STRING =>
      cell.getStringCellValue
  }

  def asStringOpt = if (Cell.CELL_TYPE_STRING == cell.getCellType) Some(cell.getStringCellValue) else None

  def asStringOptional = asStringOpt.asJava

  def asRichStringOpt = if (Cell.CELL_TYPE_STRING == cell.getCellType) Some(cell.getRichStringCellValue) else None

  def asRichStringOptional = asRichStringOpt.asJava

  def asDoubleOpt = if (Cell.CELL_TYPE_NUMERIC == cell.getCellType) Some(cell.getNumericCellValue) else None

  def asDoubleOptional = asDoubleOpt.asJava

  def asBooleanOpt = if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType) Some(cell.getBooleanCellValue) else None

  def asBooleanOptional = asBooleanOpt.asJava

  def asErrorOpt = if (Cell.CELL_TYPE_ERROR == cell.getCellType) Some(cell.getErrorCellValue) else None

  def asErrorOptional = asErrorOpt.asJava

  def asDateOpt = if (Cell.CELL_TYPE_NUMERIC == cell.getCellType && DateUtil.isCellDateFormatted(cell)) Some(cell.getDateCellValue) else None

  def asDateOptional = asDateOpt.asJava

  def asLocalDateTimeOpt = asDateOpt.map(d => LocalDateTime.ofInstant(d.toInstant, ZoneId.systemDefault()))

  def asLocalDateTimeOptional = asLocalDateTimeOpt.asJava

  def asFormulaOpt = if (Cell.CELL_TYPE_FORMULA == cell.getCellType) Some(cell.getCellFormula) else None

  def asFormulaOptional = asFormulaOpt.asJava

  def getExcelCellType: ExcelCellType = cell.getCellType match {
    case Cell.CELL_TYPE_BLANK => ExcelCellType.BLACK
    case Cell.CELL_TYPE_BOOLEAN => ExcelCellType.BOOLEAN
    case Cell.CELL_TYPE_ERROR => ExcelCellType.ERROR
    case Cell.CELL_TYPE_FORMULA => ExcelCellType.FORMULA
    case Cell.CELL_TYPE_NUMERIC => if (DateUtil.isCellDateFormatted(cell)) ExcelCellType.DATE else ExcelCellType.NUMERIC
    case Cell.CELL_TYPE_STRING => ExcelCellType.STRING
  }

  override def toString: String = s"[$getRowIndex:$getColumnIndex]$value"
}
