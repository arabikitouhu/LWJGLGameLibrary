package org.arabikitouhu.component;

import org.arabikitouhu.color.Color;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.event.rendering.RenderingEventSender;
import org.arabikitouhu.loader.SpriteLoader;
import org.arabikitouhu.sprite.Sprite;

public class Panel extends ContainerComponent {

	public static final Sprite sprite = SpriteLoader.Import("./data/sprite/standard/gui/panel.xml");

	public Panel() {

		super();

		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint,
			new RenderingEventListener() {
				@Override public void OnPaint(Component sender, RenderingEventObject e) {
					e.Enable2D();

					sprite.OnPreRender();
					if(!GetEnable()) {
						Color.DARKGLAY.Using();
					}
					sprite.OnRender("@", GetPointF().GetX(), GetPointF().GetY(), GetSizeF().GetWidth(), GetSizeF().GetHeight());
					Color.WHITE.Using();
					sprite.OnPostRender();

					e.Disable2D();

					for(int i = 0; i < m_Children.size(); i++) {
						Component child = m_Children.get(i);
						if(child instanceof RenderingEventSender) {
							((RenderingEventSender)child).RenderingEventCheck(e);
						}
					}


				}
			});
	}

}
