package worldsimulator

trait Country {
  val name: String
  val population: Long
  val gdp: Double

  def simulateYear(): Unit
}
