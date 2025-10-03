package worldsimulator

import scala.collection.mutable.ListBuffer
import scala.util.Random

class Nigeria extends Country {
  override val name: String = "Nigeria"
  override val population: Long = 223804632L // 2023 estimate
  override val gdp: Double = 440.8e9 // 2023 estimate in USD
  
  private var currentPopulation: Long = population
  private var currentGdp: Double = gdp
  private var oilPrice: Double = 75.0 // USD per barrel
  private var oilProduction: Double = 1.8e6 // barrels per day
  private var inflationRate: Double = 0.18 // 18% inflation
  private var unemploymentRate: Double = 0.33 // 33% unemployment
  
  private val cities: ListBuffer[City] = ListBuffer.empty
  private val citizens: ListBuffer[Citizen] = ListBuffer.empty
  
  // Initialize major Nigerian cities
  initializeCities()
  initializeCitizens()
  
  private def initializeCities(): Unit = {
    cities += new City("Lagos", 15388000L, 45000.0) // Commercial capital
    cities += new City("Kano", 4200000L, 12000.0) // Northern commercial hub
    cities += new City("Ibadan", 3550000L, 8500.0) // Educational center
    cities += new City("Abuja", 3564000L, 25000.0) // Federal capital
    cities += new City("Port Harcourt", 2000000L, 18000.0) // Oil hub
    cities += new City("Kaduna", 1500000L, 6000.0) // Industrial center
    cities += new City("Benin City", 1500000L, 4000.0) // Historical city
    cities += new City("Jos", 1000000L, 3000.0) // Plateau state capital
  }
  
  private def initializeCitizens(): Unit = {
    val occupations = List("Farmer", "Trader", "Civil Servant", "Engineer", "Teacher", 
                          "Doctor", "Lawyer", "Banker", "Oil Worker", "Student")
    val names = List("Adebayo", "Chinwe", "Emeka", "Fatima", "Ibrahim", "Kemi", 
                     "Mohammed", "Ngozi", "Oluwaseun", "Yusuf")
    
    for (i <- 1 to 1000) {
      val name = names(Random.nextInt(names.length)) + " " + names(Random.nextInt(names.length))
      val age = Random.nextInt(65) + 18
      val occupation = occupations(Random.nextInt(occupations.length))
      citizens += new Citizen(name, age, occupation)
    }
  }
  
  override def simulateYear(): Unit = {
    simulateEconomy()
    simulatePopulation()
    simulateOilSector()
    simulateUrbanization()
    simulateEducation()
    simulateHealthcare()
    
    println(s"Nigeria Year Summary:")
    println(s"  Population: ${formatNumber(currentPopulation)}")
    println(s"  GDP: ${formatNumber(currentGdp.toLong)}")
    println(s"  Oil Price: ${oilPrice.toInt}/barrel")
    println(s"  Inflation: ${(inflationRate * 100).toInt}%")
    println(s"  Unemployment: ${(unemploymentRate * 100).toInt}%")
    println(s"  Largest City: ${getLargestCity.name} (${formatNumber(getLargestCity.getPopulation)})")
  }
  
  private def simulateEconomy(): Unit = {
    // Oil price volatility affects economy significantly
    val oilPriceChange = (Random.nextGaussian() * 10) - 2 // Mean -2, std 10
    oilPrice = Math.max(30.0, oilPrice + oilPriceChange)
    
    // GDP growth influenced by oil prices and diversification efforts
    val oilImpact = (oilPrice - 75.0) / 75.0 * 0.3
    val diversificationBonus = 0.02 // Small bonus for economic diversification
    val gdpGrowthRate = 0.025 + oilImpact + diversificationBonus + (Random.nextGaussian() * 0.02)
    
    currentGdp *= (1 + gdpGrowthRate)
    
    // Inflation affected by oil prices and government policies
    val inflationChange = (oilPrice - 75.0) / 75.0 * 0.1 + (Random.nextGaussian() * 0.05)
    inflationRate = Math.max(0.05, Math.min(0.5, inflationRate + inflationChange))
  }
  
  private def simulatePopulation(): Unit = {
    // High birth rate but also high mortality
    val birthRate = 0.035 // 3.5% annual growth
    val mortalityRate = 0.012 // 1.2% annual mortality
    val netMigration = -50000 // Net emigration
    
    val populationGrowth = (birthRate - mortalityRate) * currentPopulation + netMigration
    currentPopulation += populationGrowth.toLong
  }
  
  private def simulateOilSector(): Unit = {
    // Oil production affected by security, investment, and OPEC quotas
    val securityFactor = 0.95 + (Random.nextGaussian() * 0.1) // Security issues
    val investmentFactor = 1.0 + (Random.nextGaussian() * 0.05) // Investment levels
    val opecFactor = 0.98 + (Random.nextGaussian() * 0.04) // OPEC compliance
    
    oilProduction *= securityFactor * investmentFactor * opecFactor
    oilProduction = Math.max(1.0e6, Math.min(2.5e6, oilProduction)) // Realistic bounds
  }
  
  private def simulateUrbanization(): Unit = {
    // Rural to urban migration
    val urbanizationRate = 0.02 // 2% of rural population moves to cities annually
    
    cities.foreach { city =>
      val migration = (urbanizationRate * city.getPopulation * Random.nextDouble()).toLong
      // Simulate population growth in cities
      val cityGrowth = city.getPopulation * 0.03 + migration
      // Note: In a real implementation, we'd need mutable cities or create new ones
    }
  }
  
  private def simulateEducation(): Unit = {
    // Education affects unemployment and economic productivity
    val educationImprovement = 0.01 // Gradual improvement in education
    unemploymentRate = Math.max(0.15, unemploymentRate - educationImprovement)
  }
  
  private def simulateHealthcare(): Unit = {
    // Healthcare improvements reduce mortality
    val healthcareImprovement = 0.005 // Gradual healthcare improvement
    // This would affect mortality rate in population simulation
  }
  
  private def getLargestCity: City = {
    cities.maxBy(_.getPopulation)
  }
  
  private def formatNumber(num: Long): String = {
    if (num >= 1e9) f"${num / 1e9}%.1fB"
    else if (num >= 1e6) f"${num / 1e6}%.1fM"
    else if (num >= 1e3) f"${num / 1e3}%.1fK"
    else num.toString
  }
  
  def getCities: List[City] = cities.toList
  def getCitizens: List[Citizen] = citizens.toList
  def getCurrentPopulation: Long = currentPopulation
  def getCurrentGdp: Double = currentGdp
  def getOilPrice: Double = oilPrice
  def getOilProduction: Double = oilProduction
  def getInflationRate: Double = inflationRate
  def getUnemploymentRate: Double = unemploymentRate
}
