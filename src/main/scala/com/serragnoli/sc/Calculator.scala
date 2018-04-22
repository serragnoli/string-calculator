package com.serragnoli.sc

object Calculator {

  def add(input: String): Int = input match {
    case "" => 0
    case _ =>
      vetNegative(convertToInt(extractSeparator(input), extractOnlyValues(input))).sum
  }

  private def extractSeparator(s: String = ""): String = s match {
    case l if l.startsWith("//") =>
      val lines = l.lines.toVector
      lines(0).slice(2, 3)
    case _ => "[,\n]"
  }

  private def extractOnlyValues(s: String): String = s match {
    case l: String if l.startsWith("//") => l.lines.toVector(1)
    case _ => s
  }

  private def convertToInt(sep: String, l: String): Vector[Int] = {
    l.split(sep).map(_.trim.toInt).toVector
  }

  private def vetNegative(line: Vector[Int]): Vector[Int] = line match {
    case l: Vector[Int] if l.exists(_ < 0) => throw NegativesNotAllowedException(l.filter(_ < 0))
    case _ => line
  }
}
