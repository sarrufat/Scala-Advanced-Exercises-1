import scala.annotation.tailrec

def fibs(ct: Int): Seq[Int] = {
  var n1 = 0
  var n2 = 1
  for (i <- 1 to ct) yield {
    val x = n1 + n2
    n1 = n2
    n2 = x
    x
  }
}

def fibs2(ct: Int): Seq[Int] = {
  @tailrec
  def ifib(ct: Int, acc: Seq[Int]): Seq[Int] = {
    if (ct == 1) acc
    else ifib(ct - 1,  acc.take(2).sum +: acc)
  }

  ifib(ct, Seq(1, 1)).reverse
}

fibs(20)
fibs2(20)
def facts(ct: Int): Seq[Long] = {
  var f = 1L
  for (i <- 1 to ct) yield {
    f = f * i
    f
  }
}

facts(10)

@tailrec
def facts2(limit: Int, ct: Int = 2, acc: Vector[Long] = Vector(1L)): Seq[Long] =
  if (ct > limit) acc else facts2(limit, ct + 1, acc :+ (acc.last * ct))

facts2(10)
