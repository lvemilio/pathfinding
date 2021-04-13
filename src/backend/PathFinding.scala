package backend

import backend.physics.PhysicsVector

object PathFinding {

  def findPath(start: GridLocation, end: GridLocation, map: List[List[MapTile]]): List[GridLocation] = {
    List()
  }


  def getVelocity(path: List[GridLocation], currentLocation: PhysicsVector): PhysicsVector = {
    new PhysicsVector(0.0, 0.0)
  }

}
