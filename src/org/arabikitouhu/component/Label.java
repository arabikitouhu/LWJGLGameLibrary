package org.arabikitouhu.component;

import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.font.FontState;

public class Label extends Component {

	public Label() {

		super();

		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint,
			new RenderingEventListener() {
				@Override public void OnPaint(Component sender, RenderingEventObject e) {
					if(GetText() != null) {
						e.Enable2D();

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
				}
			});
	}
}
