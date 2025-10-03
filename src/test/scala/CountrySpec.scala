package worldsimulator

import org.scalatest.{FlatSpec, Matchers}

class CountrySpec extends FlatSpec with Matchers {
  
  "A Country" should "have name, population, and gdp" in {
    val country = new TestCountry("Testland", 1000000L, 50000.0)
    country.name shouldBe "Testland"
    country.population shouldBe 1000000L
    country.gdp shouldBe 50000.0
  }
  
  it should "implement simulateYear method" in {
    val country = new TestCountry("Testland", 1000000L, 50000.0)
    noException should be thrownBy country.simulateYear()
  }
}

class TestCountry(override val name: String, override val population: Long, override val gdp: Double) extends Country {
  override def simulateYear(): Unit = {
    // Test implementation
  }
}
