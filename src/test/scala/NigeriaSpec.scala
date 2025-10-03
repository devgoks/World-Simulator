package worldsimulator

import org.scalatest.{FlatSpec, Matchers}

class NigeriaSpec extends FlatSpec with Matchers {
  
  "Nigeria" should "be a country with correct basic attributes" in {
    val nigeria = new Nigeria()
    nigeria.name shouldBe "Nigeria"
    nigeria.population shouldBe 223804632L
    nigeria.gdp shouldBe 440.8e9
  }
  
  it should "have major cities" in {
    val nigeria = new Nigeria()
    val cities = nigeria.getCities
    
    cities should not be empty
    cities.length should be >= 8
    
    val cityNames = cities.map(_.name)
    cityNames should contain("Lagos")
    cityNames should contain("Abuja")
    cityNames should contain("Kano")
    cityNames should contain("Port Harcourt")
  }
  
  it should "have Lagos as the largest city" in {
    val nigeria = new Nigeria()
    val largestCity = nigeria.getCities.maxBy(_.getPopulation)
    largestCity.name shouldBe "Lagos"
    largestCity.getPopulation shouldBe 15388000L
  }
  
  it should "have citizens with various occupations" in {
    val nigeria = new Nigeria()
    val citizens = nigeria.getCitizens
    
    citizens should not be empty
    citizens.length shouldBe 1000
    
    val occupations = citizens.map(_.getOccupation).distinct
    occupations should contain("Farmer")
    occupations should contain("Engineer")
    occupations should contain("Oil Worker")
    occupations should contain("Doctor")
  }
  
  it should "simulate years and update population and GDP" in {
    val nigeria = new Nigeria()
    val initialPopulation = nigeria.getCurrentPopulation
    val initialGdp = nigeria.getCurrentGdp
    
    nigeria.simulateYear()
    
    // Population should grow (high birth rate)
    nigeria.getCurrentPopulation should be > initialPopulation
    
    // GDP should change (oil price volatility)
    nigeria.getCurrentGdp should not be initialGdp
  }
  
  it should "have realistic oil production" in {
    val nigeria = new Nigeria()
    val oilProduction = nigeria.getOilProduction
    
    oilProduction should be >= 1.0e6 // At least 1M barrels per day
    oilProduction should be <= 2.5e6 // At most 2.5M barrels per day
  }
  
  it should "have realistic inflation and unemployment rates" in {
    val nigeria = new Nigeria()
    
    nigeria.getInflationRate should be >= 0.05 // At least 5%
    nigeria.getInflationRate should be <= 0.5 // At most 50%
    
    nigeria.getUnemploymentRate should be >= 0.15 // At least 15%
    nigeria.getUnemploymentRate should be <= 0.5 // At most 50%
  }
  
  it should "have oil price volatility" in {
    val nigeria = new Nigeria()
    val initialOilPrice = nigeria.getOilPrice
    
    nigeria.simulateYear()
    
    // Oil price should change due to volatility
    nigeria.getOilPrice should not be initialOilPrice
    nigeria.getOilPrice should be >= 30.0 // Minimum oil price
  }
  
  it should "implement the Country trait correctly" in {
    val nigeria = new Nigeria()
    
    // Should be able to call simulateYear without exceptions
    noException should be thrownBy nigeria.simulateYear()
    
    // Should have required Country attributes
    nigeria.name should not be empty
    nigeria.population should be > 0L
    nigeria.gdp should be > 0.0
  }
}
