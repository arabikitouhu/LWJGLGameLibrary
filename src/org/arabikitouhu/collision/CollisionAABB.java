package org.arabikitouhu.collision;

import org.arabikitouhu.direction.Direction;
import org.arabikitouhu.ray.Ray;
import org.arabikitouhu.vector.Vector3;

public class CollisionAABB extends Collision {

	protected Vector3 center;
	protected Vector3 min;
	protected Vector3 max;

	public CollisionAABB() {

	}

	public boolean CheckIntersectRay(Ray ray, Direction direction) {

		//レイをAABBのローカルへ移動
		Vector3 rayStart = Vector3.Addition(ray.GetNear(), center.Inverse());
		Vector3 rayEnd = Vector3.Addition(ray.GetFar(), center.Inverse());

		Vector3 vMinX = rayStart.IntermediateWithXValue(rayEnd, this.min.GetX());
		Vector3 vMaxX = rayStart.IntermediateWithXValue(rayEnd, this.max.GetX());
		Vector3 vMinY = rayStart.IntermediateWithYValue(rayEnd, this.min.GetY());
		Vector3 vMaxY = rayStart.IntermediateWithYValue(rayEnd, this.max.GetY());
		Vector3 vMinZ = rayStart.IntermediateWithZValue(rayEnd, this.min.GetZ());
		Vector3 vMaxZ = rayStart.IntermediateWithZValue(rayEnd, this.max.GetZ());

		// それぞれの平面上での衝突判定
		if (! isVecInsideYZBounds(vMinX)) { vMinX = null; }
		if (! isVecInsideYZBounds(vMaxX)) { vMaxX = null; }
		if (! isVecInsideXZBounds(vMinY)) { vMinY = null; }
		if (! isVecInsideXZBounds(vMaxY)) { vMaxY = null; }
		if (! isVecInsideXYBounds(vMinZ)) { vMinZ = null; }
		if (! isVecInsideXYBounds(vMaxZ)) { vMaxZ = null; }

		// 最終的な衝突判定(6方向判定)
		Vector3 vHit = null;

		if (vMinX != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMinX) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMinX;
		}
		if (vMaxX != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMaxX) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMaxX;
		}
		if (vMinY != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMinY) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMinY;
		}
		if (vMaxY != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMaxY) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMaxY;
		}
		if (vMinZ != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMinZ) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMinZ;
		}
		if (vMaxZ != null &&
				(vHit == null ||
				rayStart.SquaredDistanceTo(vMaxZ) < rayStart.SquaredDistanceTo(vHit))) {
			vHit = vMaxZ;
		}

		// 最終判定
		if (vHit == null) { direction = null; return false;
		} else {
			if (vHit == vMinX) { direction.SetID(Direction.EAST); }
			if (vHit == vMaxX) { direction.SetID(Direction.WEST); }
			if (vHit == vMinY) { direction.SetID(Direction.UP); }
			if (vHit == vMaxY) { direction.SetID(Direction.DOWN); }
			if (vHit == vMinZ) { direction.SetID(Direction.NORTH); }
			if (vHit == vMinX) { direction.SetID(Direction.SOUTH); }
			return true;
		}
	}

	/** YZ平面上の衝突判定 */
	private boolean isVecInsideYZBounds(Vector3 value) {
		return value == null ?
				false :
					value.GetY() >= this.min.GetY() && value.GetY() <= this.max.GetY() &&
					value.GetZ() >= this.min.GetZ() && value.GetZ() <= this.max.GetZ();
	}

	/** XZ平面上の衝突判定 */
	private boolean isVecInsideXZBounds(Vector3 value) {
		return value == null ?
				false :
					value.GetX() >= this.min.GetX() && value.GetX() <= this.max.GetX() &&
					value.GetZ() >= this.min.GetZ() && value.GetZ() <= this.max.GetZ();
	}

	/** XY平面上の衝突判定 */
	private boolean isVecInsideXYBounds(Vector3 value) {
		return value == null ?
				false :
					value.GetX() >= this.min.GetX() && value.GetX() <= this.max.GetX() &&
					value.GetY() >= this.min.GetY() && value.GetY() <= this.max.GetY();
	}

}
