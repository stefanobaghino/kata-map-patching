package patchmap

sealed trait Patch extends (((String, String)) => (String, String))

final class PatchValue(update: String => String) extends Patch {
  override def apply(pair: (String, String)): (String, String) = pair.copy(_2 = update(pair._2))
}

final class PatchKey(update: String => String) extends Patch {
  override def apply(pair: (String, String)): (String, String) = pair.copy(_1 = update(pair._1))
}

final class MalleableMapPatching(rules: Map[String, Patch]) {

  def updatePair(pair: (String, String)): (String, String) = {
    rules.filterKeys(_ == pair._1).foldLeft(pair) {
      case (pair, (_, patch)) =>
        patch(pair)
    }
  }

  def update(parameters: Map[String, String]): Map[String, String] =
    parameters.map(updatePair)

}

