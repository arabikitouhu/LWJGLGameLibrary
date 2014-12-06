package org.arabikitouhu.component;

import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.font.FontState;
import org.arabikitouhu.loader.SpriteLoader;
import org.arabikitouhu.sprite.Sprite;

public class Button extends Component {

	public static final Sprite sprite = SpriteLoader.Import("./data/sprite/standard/gui/button.xml");

	public Button() {

		super();

		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint,
			new RenderingEventListener() {
				@Override public void OnPaint(Component sender, RenderingEventObject e) {
					e.Enable2D();

					String key = null;
					if(! GetEnable()) {	//有効でない場合
						key = "b";
					} else {
						if(mouseChk_isClick) {	//クリック中の場合
							key = "c";
						} else {
							key = "a";
						}
					}
					if(sprite != null) {
						sprite.OnRendering(key, GetPointF(), GetSizeF());
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

					e.Disable2D();

				}
			});

	}




}
