package worldsimulator

object WorldSimulator {
  def main(args: Array[String]): Unit = {
    val world = new World()
    val dummyCountry = new DummyCountry("Testland", 1000000L, 50000.0)
    
    world.addCountry(dummyCountry)
    
    println("Starting World Simulation...")
    println(s"Initial year: ${world.getCurrentYear}")
    
    world.simulateYears(5)
    
    println(s"Simulation complete. Final year: ${world.getCurrentYear}")
  }
}

class DummyCountry(override val name: String, override val population: Long, override val gdp: Double) extends Country {
  override def simulateYear(): Unit = {
    println(s"$name is simulating year...")
  }
}
