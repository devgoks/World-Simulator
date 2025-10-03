package worldsimulator

import org.scalatest.{FlatSpec, Matchers}

class CitizenSpec extends FlatSpec with Matchers {
  
  "A Citizen" should "have name, age, and occupation" in {
    val citizen = new Citizen("John Doe", 30, "Engineer")
    citizen.name shouldBe "John Doe"
    citizen.getAge shouldBe 30
    citizen.getOccupation shouldBe "Engineer"
  }
}
