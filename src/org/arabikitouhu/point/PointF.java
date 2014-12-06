package org.arabikitouhu.point;


public class PointF {

	private float x;
	private float y;

	/** [GET]X座標 */
	public float GetX() { return this.x; }
	/** [SET]X座標 */
	public PointF SetX(float value) { this.x = value; return this; }

	/** [GET]Y座標 */
	public float GetY() { return this.y; }
	/** [SET]Y座標 */
	public PointF SetY(float value) { this.y = value; return this; }

	public PointF(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Point GetInt() {
		Point p = new Point((int)x, (int)y);

		return p;
	}
}
