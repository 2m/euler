/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10 001st prime number?
 */

Stream.from(2).filter(a => (2 to Math.sqrt(a).floor.toInt).forall(a % _ != 0))(10000)
