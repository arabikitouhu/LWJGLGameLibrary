package org.arabikitouhu.point;


public class Point {

	private int x;
	private int y;

	/** [GET]X座標 */
	public int GetX() { return this.x; }
	/** [SET]X座標 */
	public Point SetX(int value) { this.x = value; return this; }

	/** [GET]Y座標 */
	public int GetY() { return this.y; }
	/** [SET]Y座標 */
	public Point SetY(int value) { this.y = value; return this; }

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public PointF GetFloat() {
		PointF p = new PointF(x, y);

		return p;
	}
}
