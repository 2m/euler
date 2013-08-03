/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.

 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 *
 * MM: from these two equations:
 *   a^2 + b^2 = c^2
 *   a + b + c = 1000
 * we can get one equation without the term c:
 *   a + b - a * b / 1000 = 500
 * Notice that a and b here are integers. So we get another constraint that a * b must be
 * divisible by 1000 evenly.
 */

val (a, b) = (for (a <- (1 to 1000); b <- (a to 1000)
                   if a * b % 1000 == 0 && a + b - a * b / 1000 == 500)
                      yield (a, b)
             ).head

val c = Math.sqrt(a * a + b * b).toInt
List(a, b, c).product
