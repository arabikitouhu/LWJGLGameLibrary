package org.arabikitouhu.event.object;

public class MouseEventObject {

	public static final int ID_None = 0;
	public static final int ID_Left = 1;
	public static final int ID_Right = 2;
	public static final int ID_Middle = 3;

	private int id;
	private float posX;
	private float posY;
	private float posDeltaX;
	private float posDeltaY;
	private float deltaWheel;

	private boolean leftButton;
	private boolean rightButton;
	private boolean middleButton;

	public MouseEventObject(float x, float y, float dx, float dy, float dz, boolean lBtn, boolean rBtn, boolean mBtn) {
		id = ID_None;
		posX = x;
		posY = y;
		posDeltaX = dx;
		posDeltaY = dy;
		deltaWheel = dz;
		leftButton = lBtn;
		rightButton = rBtn;
		middleButton = mBtn;
	}

	/** [GET]X座標 */
	public float GetPosX() { return posX; }

	/** [GET]Y座標 */
	public float GetPosY() { return posY; }

	/** [GET]X座標増減 */
	public float GetDeltaX() { return posDeltaX; }

	/** [GET]Y座標増減 */
	public float GetDeltaY() { return posDeltaY; }

	/** [GET]ホイール増減 */
	public float GetDeltaWheel() { return deltaWheel; }

	public boolean isLeftButtonState() { return leftButton; }
	public boolean isRightButtonState() { return rightButton; }
	public boolean isMiddleButtonState() { return middleButton; }

	public int GetID() { return id; }
	public MouseEventObject SetID(int value) { this.id = value; return this; }
}
