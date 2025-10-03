package worldsimulator

import org.scalatest.{FlatSpec, Matchers}

class WorldSpec extends FlatSpec with Matchers {
  
  "A World" should "start at year 0" in {
    val world = new World()
    world.getCurrentYear shouldBe 0
  }
  
  it should "add countries" in {
    val world = new World()
    val country = new DummyCountry("Testland", 1000000L, 50000.0)
    world.addCountry(country)
    world.getCountries should contain(country)
  }
  
  it should "simulate ticks and advance years" in {
    val world = new World()
    val country = new DummyCountry("Testland", 1000000L, 50000.0)
    world.addCountry(country)
    
    world.simulateTick()
    world.getCurrentYear shouldBe 1
    
    world.simulateYears(3)
    world.getCurrentYear shouldBe 4
  }
}

class DummyCountry(override val name: String, override val population: Long, override val gdp: Double) extends Country {
  override def simulateYear(): Unit = {
    // Test implementation
  }
}
