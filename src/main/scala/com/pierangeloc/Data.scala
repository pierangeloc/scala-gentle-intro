package com.pierangeloc

import scala.io.Source


object Data {
  val introPath = "./src/main/resources/intro.txt"

  lazy val lines = Source.fromFile(introPath).getLines().toList

}
