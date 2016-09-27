package com.hualongdata.util.scalaapi.poi

import org.apache.poi.ss.usermodel.Cell

/**
  * Created by yangbajing on 16-9-21.
  */
object Implicits {
  implicit def cell2RichCell(cell: Cell): RichCell = new RichCell(cell)
}
