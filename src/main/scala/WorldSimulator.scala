package worldsimulator

object WorldSimulator {
  def main(args: Array[String]): Unit = {
    val world = new World()
    val dummyCountry = new DummyCountry("Testland", 1000000L, 50000.0)
    val nigeria = new Nigeria()
    
    world.addCountry(dummyCountry)
    world.addCountry(nigeria)
    
    println("Starting World Simulation...")
    println(s"Initial year: ${world.getCurrentYear}")
    println(s"Countries: ${world.getCountries.map(_.name).mkString(", ")}")
    println()
    
    world.simulateYears(5)
    
    println(s"\nSimulation complete. Final year: ${world.getCurrentYear}")
    println("\nFinal Statistics:")
    world.getCountries.foreach { country =>
      println(s"${country.name}: Population ${country.population}, GDP ${country.gdp.toLong}")
    }
  }
}

class DummyCountry(override val name: String, override val population: Long, override val gdp: Double) extends Country {
  override def simulateYear(): Unit = {
    println(s"$name is simulating year...")
  }
}
