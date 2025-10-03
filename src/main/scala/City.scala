package worldsimulator

class City(val name: String, val population: Long, val resources: Double) {
  def getPopulation: Long = population
  def getResources: Double = resources
}
