/**
 * The sum of the squares of the first ten natural numbers is,

 * 1^2 + 2^2 + ... + 10^2 = 385
 * The square of the sum of the first ten natural numbers is,

 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */

// humbly taken from http://www.derekwyatt.org/2012/03/12/adding-squared-to-scala-numbers-with-implicts-and-type-classes/
implicit def numeric2Powerable[A : Numeric](i: A) = new {
  import Numeric.Implicits._
  def squared: A = i * i
}


val a = 1
val b = 100
val sumOfSquares = (a to b).map(x => x * x).foldLeft(0)((a, b) => a + b)
val squareOfSums = (a to b).foldLeft(0)((a, b) => a + b).squared

squareOfSums - sumOfSquares
