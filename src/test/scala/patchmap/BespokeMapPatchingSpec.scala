package patchmap

import org.scalatest.{FlatSpec, Matchers}

final class BespokeMapPatchingSpec extends FlatSpec with Matchers {

  behavior of "update"

  it should "leave an empty map alone" in {
    BespokeMapPatching.update(Map.empty) shouldBe empty
  }

  it should "update a map with a truthy value for the 'checkPool' key to a true value" in {
    BespokeMapPatching.update(Map("checkPool" -> "1")) shouldEqual Map("checkPool" -> "true")
  }

  it should "update a map with a falsy value fo the 'checkPool' key to false" in {
    BespokeMapPatching.update(Map("checkPool" -> "true")) shouldEqual Map("checkPool" -> "false")
  }

  it should "update a map with the 'Newheader' key with a new key" in {
    BespokeMapPatching.update(Map("Newheader" -> "true")) shouldEqual Map("header" -> "true")
  }

  it should "leave a map without changes if none are needed" in {
    BespokeMapPatching.update(Map("key-we-don't-care-about" -> "stuff")) shouldEqual Map("key-we-don't-care-about" -> "stuff")
  }

}
