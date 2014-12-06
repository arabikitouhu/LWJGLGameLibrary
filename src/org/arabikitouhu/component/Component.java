package org.arabikitouhu.component;

import org.arabikitouhu.color.Color;
import org.arabikitouhu.event.keyboard.KeyboardEventListener;
import org.arabikitouhu.event.keyboard.KeyboardEventSender;
import org.arabikitouhu.event.mouse.MouseEventListener;
import org.arabikitouhu.event.mouse.MouseEventSender;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.event.rendering.RenderingEventSender;
import org.arabikitouhu.event.update.UpdateEventListener;
import org.arabikitouhu.event.update.UpdateEventSender;
import org.arabikitouhu.font.Font;
import org.arabikitouhu.font.FontState;
import org.arabikitouhu.loader.FontLoader;
import org.arabikitouhu.point.Point;
import org.arabikitouhu.point.PointF;
import org.arabikitouhu.rectangle.Rectangle;
import org.arabikitouhu.rectangle.RectangleF;
import org.arabikitouhu.size.Size;
import org.arabikitouhu.size.SizeF;
import org.arabikitouhu.timer.Timer;

/**
 * 空のコンポーネントクラス(抽象クラス)
 * @author arabikitouhu
 * @version 0.0
 */
public abstract class Component implements MouseEventSender, RenderingEventSender, KeyboardEventSender, UpdateEventSender {

	Timer timer;



	protected Component m_Parent;

	/** [GET]親コンポーネント */
	public Component GetParent() { return this.m_Parent; }
	/** [SET]親コンポーネント */
	public Component SetParent(Component value) { this.m_Parent = value; return this; }

	protected Point m_Point;

	/** [GET]XY座標 */
	public Point GetPoint() { return this.m_Point; }
	/** [SET]XY座標 */
	public Component SetPoint(Point value) { this.m_Point = value; return this; }
	/** [GET]XY座標 */
	public PointF GetPointF() { return this.m_Point.GetFloat(); }

	protected String m_Name;

	/** [GET]名前 */
	public String GetName() { return this.m_Name; }
	/** [SET]名前 */
	public Component SetName(String value) { this.m_Name = value; return this; }

	protected boolean m_IsEnable;

	/** [GET]有効化(Update) */
	public boolean GetEnable() { return this.m_IsEnable; }
	/** [SET]有効化(Update) */
	public Component SetEnable(boolean value) { this.m_IsEnable = value; return this; }

	protected boolean m_IsVisible;

	/** [GET]可視化(Draw) */
	public boolean GetVisible() { return this.m_IsVisible; }
	/** [SET]可視化(Draw) */
	public Component SetVisible(boolean value) { this.m_IsVisible = value; return this; }

	protected Font m_Font;

	/** [GET]フォント名 */
	public Font GetFont() { return this.m_Font; }
	/** [SET]フォント名 */
	public Component SetFontName(Font value) { this.m_Font = value; return this; }

	protected FontState m_FontState;

	/** [GET]フォント情報 */
	public FontState GetFontState() { return this.m_FontState; }
	/** [SET]フォント情報 */
	public Component SetFontState(FontState value) { this.m_FontState = value; return this; }

	protected String m_Text;

	/** [GET]テキスト */
	public String GetText() { return this.m_Text; }
	/** [SET]テキスト */
	public Component SetText(String value) { this.m_Text = value; return this; }

	protected Size m_Size;

	/** [GET]大きさ */
	public Size GetSize() { return this.m_Size; }
	/** [SET]XY座標 */
	public Component SetSize(Size value) { this.m_Size = value; return this; }
	/** [GET]大きさ */
	public SizeF GetSizeF() { return this.m_Size.GetFloat(); }

	/** [GET]矩形 */
	public Rectangle GetRectangle() { return new Rectangle(this.m_Point, this.m_Size); }
	/** [GET]矩形 */
	public RectangleF GetRectangleF() { return new RectangleF(GetPointF(), GetSizeF()); }

	protected boolean isFocus;

	public boolean IsFocus() { return this.isFocus; }
	public Component SetFocus(boolean value) { this.isFocus = value; return this; }

	public Component() {
		timer = new Timer();

		m_Parent = null;
		m_Point = new Point(0, 0);
		m_Name = "";
		m_IsEnable = false;
		m_IsVisible = false;
		m_Font = FontLoader.GetDefaultFont();
		m_FontState = FontLoader.GetDefaultFontState();
		m_Text = "";
		m_Size = new Size(0, 0);
		isFocus = false;

		//-------------------------------------------------------------------------------------------------------------------------------
		// MouseEventSender

		mouseChk_leftBtnTime = -1;
		mouseChk_rightBtnTime = -1;
		mouseChk_middleBtnTime = -1;

		mouseListener_Enter = MouseEventListener.DummyListener;
		mouseListener_Leave = MouseEventListener.DummyListener;
		mouseListener_Hover = MouseEventListener.DummyListener;
		mouseListener_Down = MouseEventListener.DummyListener;
		mouseListener_Click = MouseEventListener.DummyListener;
		mouseListener_DoubleClick = MouseEventListener.DummyListener;
		mouseListener_Up = MouseEventListener.DummyListener;

		//-------------------------------------------------------------------------------------------------------------------------------
		// RenderingEventSender

		renderingListener_Paint = RenderingEventListener.DummyListener;

		//-------------------------------------------------------------------------------------------------------------------------------
		// KeyboardEventSender

		keyboardListener_Down = KeyboardEventListener.DummyListener;
		keyboardListener_Press = KeyboardEventListener.DummyListener;
		keyboardListener_Up = KeyboardEventListener.DummyListener;

		//-------------------------------------------------------------------------------------------------------------------------------
		// UpdateEventSender

		updateListener_Update = UpdateEventListener.DummyListener;
	}


	/** 初期化処理(汎用) */
	public void Initialize() {
		SetEnable(true);
		SetVisible(true);

		SetFontState(new FontState());
		GetFontState().m_FontSize = 14;
		GetFontState().SetColor(Color.BLACK);

		mouseChk_isHit = false;
		mouseChk_isClick = false;
	}




	public void SetBound(int x, int y, int width, int height) {
		m_Point = new Point(x, y);
		m_Size = new Size(width, height);
	}


	//-------------------------------------------------------------------------------------------------------------------------------
	// MouseEventSender

		//判定用
	protected boolean mouseChk_isHit;
	protected boolean mouseChk_isClick;
	protected boolean mouseChk_leftBtnState;
	protected boolean mouseChk_rightBtnState;
	protected boolean mouseChk_middleBtnState;
	protected long mouseChk_leftBtnTime;
	protected long mouseChk_rightBtnTime;
	protected long mouseChk_middleBtnTime;

		// コールバック
	protected MouseEventListener mouseListener_Enter;
	protected MouseEventListener mouseListener_Leave;
	protected MouseEventListener mouseListener_Hover;
	protected MouseEventListener mouseListener_Down;
	protected MouseEventListener mouseListener_Click;
	protected MouseEventListener mouseListener_DoubleClick;
	protected MouseEventListener mouseListener_Up;


	@Override public boolean MouseEventCheck(MouseEventObject e) {
		if(GetEnable()) {
			boolean c = GetRectangleF().CollisionToPointer(e.GetPosX(), e.GetPosY());
			e.SetID(MouseEventObject.ID_None);
			if(mouseChk_isHit && !c) {	//Leave
				mouseChk_isHit = false;
				mouseListener_Leave.OnMouseLeave(this, e);
			} else if(!mouseChk_isHit && c) {	// Enter
				mouseChk_isHit = true;
				mouseListener_Enter.OnMouseEnter(this, e);
			} else if(mouseChk_isHit && c) {	// Hover
				mouseListener_Hover.OnMouseHover(this, e);
			}

			if(c) {

				long nowTime = timer.GetTime();

				//左ボタン
				if(!mouseChk_leftBtnState && e.isLeftButtonState()) {	//MouseDown
					mouseChk_leftBtnState = true;
					mouseChk_isClick = true;
					mouseListener_Down.OnMouseDown(this, e.SetID(MouseEventObject.ID_Left));
				} else if(mouseChk_leftBtnState && !e.isLeftButtonState()) {	//MouseUp
					if(mouseChk_leftBtnTime > 0 && nowTime - mouseChk_leftBtnTime <= 180) {	//DoubleClick
						mouseListener_DoubleClick.OnDoubleClick(this, e.SetID(MouseEventObject.ID_Left));
					} else {	//Click
						mouseListener_Click.OnClick(this, e.SetID(MouseEventObject.ID_Left));
					}
					mouseChk_leftBtnState = false;
					mouseChk_isClick = false;
					mouseChk_leftBtnTime = nowTime;
					mouseListener_Up.OnMouseUp(this, e.SetID(MouseEventObject.ID_Left));
				}

				//右ボタン
				if(!mouseChk_rightBtnState && e.isRightButtonState()) {	//MouseDown
					mouseChk_rightBtnState = true;
					mouseChk_isClick = true;
					mouseListener_Down.OnMouseDown(this, e.SetID(MouseEventObject.ID_Right));
				} else if(mouseChk_rightBtnState && !e.isRightButtonState()) {	//MouseUp
					if(mouseChk_rightBtnTime > 0 && nowTime - mouseChk_rightBtnTime <= 180) {	//DoubleClick
						mouseListener_DoubleClick.OnDoubleClick(this, e.SetID(MouseEventObject.ID_Right));
					} else {	//Click
						mouseListener_Click.OnClick(this, e.SetID(MouseEventObject.ID_Right));
					}
					mouseChk_rightBtnState = false;
					mouseChk_isClick = false;
					mouseChk_rightBtnTime = nowTime;
					mouseListener_Up.OnMouseUp(this, e.SetID(MouseEventObject.ID_Right));
				}

				//ホイールボタン
				if(!mouseChk_middleBtnState && e.isMiddleButtonState()) {	//MouseDown
					mouseChk_middleBtnState = true;
					mouseChk_isClick = true;
					mouseListener_Down.OnMouseDown(this, e.SetID(MouseEventObject.ID_Middle));
				} else if(mouseChk_middleBtnState && !e.isMiddleButtonState()) {	//MouseUp
					if(mouseChk_middleBtnTime > 0 && nowTime - mouseChk_middleBtnTime <= 180) {	//DoubleClick
						mouseListener_DoubleClick.OnDoubleClick(this, e.SetID(MouseEventObject.ID_Middle));
					} else {	//Click
						mouseListener_Click.OnClick(this, e.SetID(MouseEventObject.ID_Middle));
					}
					mouseChk_middleBtnState = false;
					mouseChk_isClick = false;
					mouseChk_middleBtnTime = nowTime;
					mouseListener_Up.OnMouseUp(this, e.SetID(MouseEventObject.ID_Middle));
				}

				e.SetID(MouseEventObject.ID_None);

			}
			return c;
		} else {
			return false;
		}
	}

	/**
	 * コールバックの登録(一括)
	 * @param callback コールバックインタフェース
	 */
	public void SetMouseEventCallback(MouseEventListener callback) {
		SetMouseEventCallback(MouseEventListener.EVENT_Enter, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_Leave, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_Hover, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_Down, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_Click, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_DoubleClick, callback);
		SetMouseEventCallback(MouseEventListener.EVENT_Up, callback);
	}

	/**
	 * コールバックの登録(指定)
	 * @param eventType イベント種類 (「MouseEventListener.EVENT_～～」の使用推奨)
	 * @param callback コールバックインタフェース
	 */
	public void SetMouseEventCallback(int eventType, MouseEventListener callback) {
		if(eventType == MouseEventListener.EVENT_Enter) {
			mouseListener_Enter = callback;
		} else if(eventType == MouseEventListener.EVENT_Leave) {
			mouseListener_Leave = callback;
		} else if(eventType == MouseEventListener.EVENT_Hover) {
			mouseListener_Hover = callback;
		} else if(eventType == MouseEventListener.EVENT_Down) {
			mouseListener_Down = callback;
		} else if(eventType == MouseEventListener.EVENT_Click) {
			mouseListener_Click = callback;
		} else if(eventType == MouseEventListener.EVENT_DoubleClick) {
			mouseListener_DoubleClick = callback;
		} else if(eventType == MouseEventListener.EVENT_Up) {
			mouseListener_Up = callback;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------
	// RenderingEventSender

		// コールバック
	protected RenderingEventListener renderingListener_Paint;

	@Override public boolean RenderingEventCheck(RenderingEventObject e) {
		if(GetVisible()) {
			if(renderingListener_Paint != null) {
				renderingListener_Paint.OnPaint(this, e);
			}
		}

		return false;
	}

	/**
	 * コールバックの登録(一括)
	 * @param callback コールバックインタフェース
	 */
	public void SetRenderingEventCallback(RenderingEventListener callback) {
		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint, callback);
	}

	/**
	 * コールバックの登録(指定)
	 * @param eventType イベント種類 (「RenderingEventListener.EVENT_～～」の使用推奨)
	 * @param callback コールバックインタフェース
	 */
	public void SetRenderingEventCallback(int eventType, RenderingEventListener callback) {
		if(eventType == RenderingEventListener.EVENT_Paint) {
			renderingListener_Paint = callback;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------
	// KeyboardEventSender

		// コールバック
	protected KeyboardEventListener keyboardListener_Down;
	protected KeyboardEventListener keyboardListener_Press;
	protected KeyboardEventListener keyboardListener_Up;


	@Override public boolean KeyboardEventCheck(KeyboardEventObject e) {
		if(GetEnable()) {
			if(keyboardListener_Down != null) {
				keyboardListener_Down.OnKeyboardDown(this, e);
			}
			if(keyboardListener_Press != null) {
				keyboardListener_Press.OnKeyboardPress(this, e);
			}
			if(keyboardListener_Up != null) {
				keyboardListener_Up.OnKeyboardUp(this, e);
			}
		}
		return false;
	}

	/**
	 * コールバックの登録(一括)
	 * @param callback コールバックインタフェース
	 */
	public void SetKeyboardEventCallback(KeyboardEventListener callback) {
		SetKeyboardEventCallback(KeyboardEventListener.EVENT_Down, callback);
		SetKeyboardEventCallback(KeyboardEventListener.EVENT_Press, callback);
		SetKeyboardEventCallback(KeyboardEventListener.EVENT_Up, callback);
	}

	/**
	 * コールバックの登録(指定)
	 * @param eventType イベント種類 (「KeyboardEventListener.EVENT_～～」の使用推奨)
	 * @param callback コールバックインタフェース
	 */
	public void SetKeyboardEventCallback(int eventType, KeyboardEventListener callback) {
		if(eventType == KeyboardEventListener.EVENT_Down) {
			keyboardListener_Down = callback;
		} else if(eventType == KeyboardEventListener.EVENT_Press) {
			keyboardListener_Press = callback;
		} else if(eventType == KeyboardEventListener.EVENT_Up) {
			keyboardListener_Up = callback;
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------
	// UpdateEventSender

		// コールバック
	protected UpdateEventListener updateListener_Update;


	@Override public boolean UpdateEventCheck(UpdateEventObject e) {

		if(updateListener_Update != null) {
			updateListener_Update.OnUpdate(this, e);
		}

		return false;
	}

	/**
	 * コールバックの登録(一括)
	 * @param callback コールバックインタフェース
	 */
	public void SetUpdateEventCallback(UpdateEventListener callback) {
		SetUpdateEventCallback(UpdateEventListener.EVENT_Update, callback);
	}

	/**
	 * コールバックの登録(指定)
	 * @param eventType イベント種類 (「UpdateEventListener.EVENT_～～」の使用推奨)
	 * @param callback コールバックインタフェース
	 */
	public void SetUpdateEventCallback(int eventType, UpdateEventListener callback) {
		if(eventType == UpdateEventListener.EVENT_Update) {
			updateListener_Update = callback;
		}
	}

}
