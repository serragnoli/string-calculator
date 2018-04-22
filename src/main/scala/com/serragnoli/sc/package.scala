package com.serragnoli

package object sc {

  case class NegativesNotAllowedException(l: Vector[Int]) extends Exception(s"Negatives not allowed: ${l.mkString(",")}")

}
