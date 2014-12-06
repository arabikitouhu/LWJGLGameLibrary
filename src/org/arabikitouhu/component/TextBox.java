package org.arabikitouhu.component;

import org.arabikitouhu.color.Color;
import org.arabikitouhu.common.LogStream;
import org.arabikitouhu.common.StringHelper;
import org.arabikitouhu.event.keyboard.KeyboardEventListener;
import org.arabikitouhu.event.mouse.MouseEventListener;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.font.FontState;
import org.arabikitouhu.loader.SpriteLoader;
import org.arabikitouhu.sprite.Sprite;
import org.lwjgl.input.Keyboard;

public class TextBox extends Component {

	public static final Sprite sprite = SpriteLoader.Import("./data/sprite/standard/gui/textbox.xml");

	private boolean isKANA = false;

	private StringBuilder bufferText = new StringBuilder();

	public TextBox() {
		super();

		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint,
				new RenderingEventListener() {
					@Override public void OnPaint(Component sender, RenderingEventObject e) {
						e.Enable2D();

						if(sprite != null) {
							sprite.OnPreRender();
							if(!GetEnable()) {
								Color.DARKGLAY.Using();
							}
							sprite.OnRender("b", GetPointF().GetX(), GetPointF().GetY(), GetSizeF().GetWidth(), GetSizeF().GetHeight());
							Color.WHITE.Using();
							sprite.OnPostRender();
						}

						float y = GetPointF().GetY();
						y += (GetSizeF().GetHeight() - GetFontState().m_FontSize) / 2f;

						if(GetText() != null && GetFont() != null) {
							if(GetFontState() != null) {
								GetFont().OnRender(GetPoint().GetX(), y, GetText(), GetFontState());
							} else {
								GetFont().OnRender(GetPoint().GetX(), y, GetText(), new FontState());
							}
						}

						float x = GetPointF().GetX();
						x += GetText().length() * GetFontState().m_FontSize;
						if(bufferText.length() > 0 && GetFont() != null) {
							FontState state = GetFontState() != null ? GetFontState().ToCopy() : new FontState();
							state.GetStyle().SetBold(true);
							GetFont().OnRender(x, y, bufferText.toString(), state);
						}
						e.Disable2D();

					}
				});

		SetMouseEventCallback(MouseEventListener.EVENT_Click,
				new MouseEventListener() {
					@Override public void OnClick(Component sender, MouseEventObject e){
						SetFocus(true);
						int index = (int) ((e.GetPosX() - GetPoint().GetX()) / (GetFontState() == null ? (new FontState()).m_FontSize : GetFontState().m_FontSize));
					}
				});

		SetKeyboardEventCallback(KeyboardEventListener.EVENT_Up,
				new KeyboardEventListener() {
					@Override public void OnKeyboardUp(Component sender, KeyboardEventObject e) {
						if(IsFocus()) {
							StringBuilder word = new StringBuilder();
							if(e.isKeyUp(Keyboard.KEY_INSERT)) {	//半角/全角切り替え「Insert」キー
								isKANA = !isKANA;
								LogStream.parent.info("push is Insert! now value is " + isKANA);
							} else if(e.isKeyUp(Keyboard.KEY_SPACE)) {	//空白or変換など
								if(IsKanaInputMode()) {	//かな字入力
									if(bufferText.length() > 0) {		//変換

									} else {	//全角空白の入力
										word.append('　');
									}
								} else {	//半角空白の入力
									word.append(' ');
								}
							} else if(e.isKeyUp(Keyboard.KEY_RETURN)) {	//確定
								if(IsKanaInputMode()) {	//かな字入力
									m_Text += bufferText.toString();
									bufferText = new StringBuilder();
								}
							} else {
								for(int i = 0; i < 256; i++) {
									if(e.isKeyUp(i)) {
										String str = (e.isKeyUp(Keyboard.KEY_LSHIFT) ||  e.isKeyUp(Keyboard.KEY_RSHIFT)) ?
												StringHelper.GetIMEfromKeyCode(i + 256) : StringHelper.GetIMEfromKeyCode(i);
										if(str != null) {
											word.append(str);
										}
									}
								}
								if(IsKanaInputMode()) {	//かな字入力
									bufferText.append(word.toString());
									String convText = StringHelper.ConvertFromEnglishToJapanese(bufferText.toString());
									bufferText = new StringBuilder(convText);
								} else {
									SetText(GetText() + word.toString());
								}
							}
//							if(IsKanaInputMode()) {	//かな字入力
//								if(e.isKeyUp(Keyboard.KEY_SPACE)) { //空白or変換
//									if(bufferText.length() > 0) {		//変換
//
//									} else {
//										m_Text += "　";
//										LogStream.parent.info("push is □!");
//									}
//								} else if(e.isKeyUp(Keyboard.KEY_RETURN)) { //確定
//									m_Text += bufferText.toString();
//									bufferText = new StringBuilder();
//								} else {
//									bufferText.append(Keyboard.getEventCharacter());
//									String convText = StringHelper.ConvertFromEnglishToJapanese(bufferText.toString());
//									bufferText = new StringBuilder(convText);
//								}
//							} else {	//ローマ字入力
//								for(int i = 0; i < 256; i++) {
//									if(e.isKeyUp(i)) {
//										String str = (e.isKeyUp(Keyboard.KEY_LSHIFT) ||  e.isKeyUp(Keyboard.KEY_RSHIFT)) ?
//												StringHelper.GetIMEfromKeyCode(i + 256) : StringHelper.GetIMEfromKeyCode(i);
//										if(str != null) {
//											LogStream.parent.info("push is " + str);
//											SetText(GetText() + str);
//										}
//									}
//								}
//							}
						}
					}
				});

	}

	public void InputModeChange(int mode) {
		if(mode == StringHelper.IME_MODE_ROMA) {
			isKANA = false;
		} else if(mode == StringHelper.IME_MODE_KANA) {
			isKANA = true;
		}
	}

	public boolean IsKanaInputMode() { return this.isKANA; }

}
