package org.arabikitouhu.rectangle;

import org.arabikitouhu.point.PointF;
import org.arabikitouhu.size.SizeF;


public class RectangleF {

	private float x, y, w, h;

	public RectangleF() {
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}

	public RectangleF(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
	}

	public RectangleF(PointF pofloat, SizeF size) {
		x = pofloat.GetX();
		y = pofloat.GetY();
		w = size.GetWidth();
		h = size.GetHeight();
	}





	public boolean CollisionToPointer(PointF pofloat) {
		return CollisionToPointer(pofloat.GetX(), pofloat.GetY());
	}

	public boolean CollisionToPointer(float x, float y) {
		if(x >= this.x && x <= this.x + this.w) {
			if(y >= this.y && y <= this.y + this.h) {
				return true;
			}
		}
		return false;
	}

	/** [GET]XY座標 */
	public PointF GetPointF() { return new PointF(x, y); }
	/** [SET]XY座標 */
	public RectangleF SetPofloat(PointF value) { x = value.GetX(); y = value.GetY(); return this; }

	/** [GET]大きさ */
	public SizeF GetSizeF() { return new SizeF(w, h); }
	/** [SET]XY座標 */
	public RectangleF SetSizeF(SizeF value) { w = value.GetWidth(); h = value.GetHeight(); return this; }
}
