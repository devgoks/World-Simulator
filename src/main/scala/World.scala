package worldsimulator

import scala.collection.mutable.ListBuffer

class World {
  private val countries: ListBuffer[Country] = ListBuffer.empty
  private var currentYear: Int = 0

  def addCountry(country: Country): Unit = {
    countries += country
  }

  def getCountries: List[Country] = countries.toList

  def getCurrentYear: Int = currentYear

  def simulateTick(): Unit = {
    currentYear += 1
    countries.foreach(_.simulateYear())
  }

  def simulateYears(years: Int): Unit = {
    for (_ <- 1 to years) {
      simulateTick()
    }
  }
}
