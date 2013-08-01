/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * MM: will approach this first by calculating all needed prime factors to construct any number from 2 to 20.
 * For 2 to 10 all such prime factors are: 2, 2, 2, 3, 3, 5, 7
 * For 2 to 20 all such prime factors are: 2, 2, 2, 2, 3, 3, 5, 7, 11, 13, 17, 19
 * Then the smallest positive number which is evenly divisible by all of the numbers of the given set is
 * a product of all found prime factors.
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

(2 to 20).map(getPrimeFactors(_)).foldLeft(List(BigInt(2))) { (a, b) =>
  val allFactors = a ::: b
  (allFactors.min to allFactors.max).flatMap {x =>
    List.fill(List(a.count(_ == x), b.count(_ == x)).max)(x)
  }.toList
}.product
