


def f(l: Array[Int]): Array[Any] =
  l.zipWithIndex.map {
    case (x@1, _) => x.toString
    case (x@2, _) => x
    case (x, _) => x.toDouble
  }


