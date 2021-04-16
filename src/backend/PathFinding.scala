package backend

import backend.physics.PhysicsVector
import play.api.libs.json.jackson.PlayJsonModule

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks.{break, breakable}

object PathFinding {

  def findPath(start: GridLocation, end: GridLocation, map: List[List[MapTile]]): List[GridLocation] = {
    var currentLocation:GridLocation = new GridLocation(start.x,start.y)

    val path:ListBuffer[GridLocation] = ListBuffer()
    path.append(start)
    val travelVector:PhysicsVector = new PhysicsVector(end.x-start.x,end.y-start.y)

    val travelX:PhysicsVector = new PhysicsVector(travelVector.x,0)
    val travelY:PhysicsVector = new PhysicsVector(0,travelVector.y)

    val travelXNormal = travelX.normal2d()
    val travelYNormal = travelY.normal2d()

    for (i <- 0 until travelX.magnitude().toInt){
      path.append(new GridLocation((currentLocation.x+travelXNormal.x).toInt,currentLocation.y))
      currentLocation = new GridLocation((currentLocation.x+travelXNormal.x).toInt,currentLocation.y)

    }
    for (i <- 0 until travelY.magnitude().toInt){
      path.append(new GridLocation(currentLocation.x,(currentLocation.y+travelYNormal.y).toInt))
      currentLocation = new GridLocation(currentLocation.x,(currentLocation.y+travelYNormal.y).toInt)
    }

    for (gridLocation<-path){
      println(gridLocation.toString)
    }
    path.toList
  }

  val EPSILON: Double = 0.1

  def equalDoubles(d1: Double, d2: Double): Boolean = {
    (d1 - d2).abs < EPSILON
  }

  def getVelocity(path: List[GridLocation], currentLocation: PhysicsVector): PhysicsVector = {
    val currentTile:GridLocation = new GridLocation(currentLocation.x.floor.toInt,currentLocation.y.floor.toInt)

      breakable{
        for (i <- path.indices) {
          if (path(i).equals(currentTile) && path(i) == path.last) {
            if (equalDoubles(currentLocation.x,currentTile.x + 0.5)  && equalDoubles(currentLocation.y,currentTile.y + 0.5) ) {
              break
              println("Velocity 0")
            } else{
              val x = (path(i).x + 0.5) - currentLocation.x
              val y = (path(i).y + 0.5)  - currentLocation.y

              val velocity:PhysicsVector = new PhysicsVector(x,y).normal2d()
              val returnVelocity = new PhysicsVector(velocity.x*5,velocity.y*5)
              println("Final tile reached," + "current location:" + currentLocation.toString)
              return returnVelocity
            }
          }
          else if (path(i).equals(currentTile)) {
            val x = path(i + 1).x + 0.5 - currentLocation.x
            val y = path(i + 1).y + 0.5 - currentLocation.y

            val velocity: PhysicsVector = new PhysicsVector(x, y).normal2d()
            println("On the way," + "current location:" + currentLocation.toString)

            val returnVelocity: PhysicsVector = new PhysicsVector((velocity.x * 5), (velocity.y * 5))
            return returnVelocity
          }
        }
      }
   new PhysicsVector(0.0, 0.0)
  }

}
