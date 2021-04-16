package backend.tests
import backend.physics.PhysicsVector
import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite
import backend.{GridLocation, PathFinding}
import play.api.libs.json.jackson.PlayJsonModule

class testVelocity extends AnyFunSuite{
  test("The method finds the appropriate vector to follow the path"){
      val testGrid:List[GridLocation] = List(new GridLocation(0,0),new GridLocation(1,0),new GridLocation(2,0),new GridLocation(3,0))
      val startingLocation1:PhysicsVector = new PhysicsVector(0.5,0.5)

      val velocity1 = PathFinding.getVelocity(testGrid,startingLocation1)

      assert(velocity1.x == 5 && velocity1.y == 0)

      ///////////////////////Starting location//


      val startingLocation2:PhysicsVector = new PhysicsVector(1.3,0.5)
      val velocity2:PhysicsVector = PathFinding.getVelocity(testGrid,startingLocation2)

      assert(velocity2.x == 5 && velocity2.y == 0)
  }
  test("The method finds the appropriate vector to follow the path vertically"){
    val testGrid:List[GridLocation] = List(new GridLocation(0,0),new GridLocation(1,0),new GridLocation(1,1),new GridLocation(2,1))
    val startingLocation1:PhysicsVector = new PhysicsVector(1.4367394278317271,0.31567184794091674)

    val velocity1 = PathFinding.getVelocity(testGrid,startingLocation1)

    assert(velocity1.x == 1.43 && velocity1.y == 5)
  }
  test("The method finds the appropriate vector to follow the path at the end of the path"){
    val testGrid:List[GridLocation] = List(new GridLocation(0,0),new GridLocation(1,0),new GridLocation(1,1),new GridLocation(2,1))
    val startingLocation1:PhysicsVector = new PhysicsVector(2,1)

    val velocity1 = PathFinding.getVelocity(testGrid,startingLocation1)

    assert(velocity1.x == 3.5355339059327373 && velocity1.y == 3.5355339059327373)

    val startingLocation2:PhysicsVector = new PhysicsVector(2.5,1.5)

    val velocity2:PhysicsVector = PathFinding.getVelocity(testGrid,startingLocation2)

    assert(velocity2.x == 0 && velocity2.y == 0)
  }

  test("The method finds the appropriate neagative vector"){
    val testGrid:List[GridLocation] = List(new GridLocation(5,1),new GridLocation(4,1),new GridLocation(4,0),new GridLocation(2,1))
    val startingLocation1:PhysicsVector = new PhysicsVector(5,1)

    val velocity1 = PathFinding.getVelocity(testGrid,startingLocation1)

    assert(velocity1.x == -3.5355339059327373 && velocity1.y == 3.5355339059327373)

    val startingLocation2:PhysicsVector = new PhysicsVector(2.5,1.5)

    val velocity2:PhysicsVector = PathFinding.getVelocity(testGrid,startingLocation2)

    assert(velocity2.x == 0 && velocity2.y == 0)
  }


}
