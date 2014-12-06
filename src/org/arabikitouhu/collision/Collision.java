package org.arabikitouhu.collision;

import org.arabikitouhu.direction.Direction;
import org.arabikitouhu.ray.Ray;

public class Collision {

	public static boolean CheckIntersectRayAndAABB(Ray ray, CollisionAABB aabb, Direction direction) {
		if(direction != null) {
			direction = new Direction(Direction.UP);
		}
		return aabb.CheckIntersectRay(ray, direction);
	}

}
