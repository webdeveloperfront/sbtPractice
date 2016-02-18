/**
 * Created by juliovg on 13/2/16.
 */
package org.preownedkittens

import org.specs2.mutable.Specification

object LogicSpec extends Specification {
  "The 'matchLikelihood' method" should {
    "be 100% when all attributes match" in {
      val tabby = Kitten(1, Set("male", "tabby"))
      val prefs = BuyerPreferences(Set("male", "tabby"))
      Logic.matchLikelihood(tabby, prefs) must beGreaterThan(0.999)
    }
    "be 100% when all attributes match" in {
      val tabby = Kitten(1, Set("male", "tabby"))
      val prefs = BuyerPreferences(Set("male", "tabby"))
      Logic.matchLikelihood(tabby, prefs) must beGreaterThan(0.999)
      }
      "be 0% when no attributes match" in {
      val tabby = Kitten(1, Set("male", "tabby"))
      val prefs = BuyerPreferences(Set("female", "calico"))
      val result = Logic.matchLikelihood(tabby, prefs)
      result must beLessThan(0.001)
    }
    "correctly handle an empty BuyerPreferences" in {
      val tabby = Kitten(1, Set("male", "tabby"))
      val prefs = BuyerPreferences(Set())
      val result = Logic.matchLikelihood(tabby, prefs)
      result.isNaN mustEqual false
    }
  }
}
