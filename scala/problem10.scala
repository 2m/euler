/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

 * Find the sum of all the primes below two million.
 */

def time(f: => Unit) = {
  val s = System.currentTimeMillis
  f
  System.currentTimeMillis - s
}

def isPrime(a: Int) = {
  (2 to Math.sqrt(a).floor.toInt).forall(a % _ != 0)
}

def isPrimeFaster(a: Int) = {
  if (a == 2) true
  else if (a % 2 == 0) false
  else (3 to Math.sqrt(a).floor.toInt by 2).forall(a % _ != 0)
}

//time((2 until 2000000).filter(isPrime).sum) // takes around 2100ms
//time((2 until 2000000).filter(isPrimeFaster).sum) // takes around 1400ms

(2 until 2000000).filter(isPrimeFaster).sum
