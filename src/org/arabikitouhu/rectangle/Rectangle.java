package org.arabikitouhu.rectangle;

import org.arabikitouhu.point.Point;
import org.arabikitouhu.size.Size;

public class Rectangle {

	private int x, y, w, h;

	public Rectangle() {
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	public Rectangle(Point point, Size size) {
		x = point.GetX();
		y = point.GetY();
		w = size.GetWidth();
		h = size.GetHeight();
	}





	public boolean CollisionToPointer(Point point) {
		return CollisionToPointer(point.GetX(), point.GetY());
	}

	public boolean CollisionToPointer(int x, int y) {
		if(x >= this.x && x <= this.x + this.w) {
			if(y >= this.y && y <= this.y + this.h) {
				return true;
			}
		}
		return false;
	}

	/** [GET]XY座標 */
	public Point GetPoint() { return new Point(x, y); }
	/** [SET]XY座標 */
	public Rectangle SetPoint(Point value) { x = value.GetX(); y = value.GetY(); return this; }

	/** [GET]大きさ */
	public Size GetSize() { return new Size(w, h); }
	/** [SET]XY座標 */
	public Rectangle SetSize(Size value) { w = value.GetWidth(); h = value.GetHeight(); return this; }
}
