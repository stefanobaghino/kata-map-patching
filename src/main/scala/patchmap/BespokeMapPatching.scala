package patchmap

object BespokeMapPatching {

  def update(parameters: Map[String, String]): Map[String, String] = {
    parameters.map {
      case (k, v) =>
        k match {
          case "checkPool" =>
            (k, if (k.contains("checkPool")) {
              v match {
                case "1" => "true"
                case _ => "false"
              }
            }
            else {
              v
            })
          case "Newheader" => (k.replace("Newheader", "header"), v)
          case _ => (k, v)
        }
      case _ => ("", "")
    }
  }

}
