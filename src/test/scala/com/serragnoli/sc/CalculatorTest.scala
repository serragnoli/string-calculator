package com.serragnoli.sc

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class CalculatorTest extends PropSpec with TableDrivenPropertyChecks with Matchers {

  val validExamples = Table(
    ("input", "expectedOutput"),
    ("", 0),
    ("1", 1),
    ("1,2", 3),
    ("2   ,     3", 5),
    ("1,2,3,4,5,6,7,8,9", 45),
    ("1\n2,3", 6),
    ("//;\n1;2", 3)
  )

  val exceptionalExamples = Table(
    ("input", "expectedException"),
    ("-1", "Negatives not allowed: -1"),
    ("-1,2,-3", "Negatives not allowed: -1,-3"),
    ("//;\n-1;2;-3", "Negatives not allowed: -1,-3")
  )

  property("calculator happy path") {
    forAll(validExamples) { (input, expectedOutput) =>
      val result: Int = Calculator.add(input)

      result shouldBe expectedOutput
    }
  }

  property("calculator exceptions") {
    forAll(exceptionalExamples) { (input, exceptionalMessage) =>
      the[NegativesNotAllowedException] thrownBy {
        Calculator.add(input)
      } should have message exceptionalMessage
    }
  }
}
