package worldsimulator

import org.scalatest.{FlatSpec, Matchers}

class CitySpec extends FlatSpec with Matchers {
  
  "A City" should "have name, population, and resources" in {
    val city = new City("Metropolis", 500000L, 1000.0)
    city.name shouldBe "Metropolis"
    city.getPopulation shouldBe 500000L
    city.getResources shouldBe 1000.0
  }
}
