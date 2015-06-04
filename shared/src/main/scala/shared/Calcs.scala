package shared

object Calcs {
  def bmi(weight: Double, height: Double) = {
    weight / (height * height)
  }
}
