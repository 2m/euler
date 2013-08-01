/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

def isPalindrome(i: Int) = {
  "%d".format(i).reverse.equals("%d".format(i))
}

(for (a <- 100 to 999; b <- a to 999 if isPalindrome(a * b)) yield a * b).max
