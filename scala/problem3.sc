/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */

def longRangeInclusive(first: BigInt, last: BigInt) = new Iterator[BigInt] {
  private var i = first
  def hasNext = i <= last
  def next = {val r = i; i += 1; r}
}

def getPrimeFactors(composite: BigInt):List[BigInt] = {
  longRangeInclusive(2, composite).filter(composite % _ == 0).take(1).toList.flatMap { x =>
    if (x == composite) {
      List(composite)
    }
    else {
      getPrimeFactors(x) ::: getPrimeFactors(composite / x)
    }
  }
}
getPrimeFactors(BigInt("600851475143")).max



