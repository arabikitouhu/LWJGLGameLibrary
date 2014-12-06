package org.arabikitouhu.component;

import org.arabikitouhu.event.object.ProgressEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.event.progress.ProgressEventListener;
import org.arabikitouhu.event.progress.ProgressEventSender;
import org.arabikitouhu.event.rendering.RenderingEventListener;
import org.arabikitouhu.loader.SpriteLoader;
import org.arabikitouhu.sprite.Sprite;

public class ProgressBar extends Component implements ProgressEventSender {

	public static final Sprite sprite = SpriteLoader.Import("./data/sprite/standard/gui/progressbar.xml");

	protected int prevProgress;
	protected int nowProgress;
	protected int maxProgress;


	public ProgressBar() {

		super();

		prevProgress = 0;
		nowProgress = 0;
		maxProgress = 100;

		progressListener_ValueChange = ProgressEventListener.DummyListener;
		progressListener_ValueZero = ProgressEventListener.DummyListener;
		progressListener_ValueMax = ProgressEventListener.DummyListener;

		SetRenderingEventCallback(RenderingEventListener.EVENT_Paint,
			new RenderingEventListener() {
				@Override public void OnPaint(Component sender, RenderingEventObject e) {
					e.Enable2D();

//					if(SpriteManager.Accessor().Has("standard.gui.progressbar")) {
//						float w = GetSizeF().GetWidth() / 2f;
//						float h = GetSizeF().GetHeight() / 2f;
//						float p = maxProgress == 0 ? 0f : (float)nowProgress / (float)maxProgress;
//						SpriteManager.Accessor().Get("standard.gui.progressbar.a").OnRendering(GetPoint().GetX() + w, GetPoint().GetY() + h, w, h);
//						SpriteManager.Accessor().Get("standard.gui.progressbar.b").OnRendering(GetPoint().GetX() + w * p, GetPoint().GetY() + h, w * p, h);
//						SpriteManager.Accessor().Get("standard.gui.progressbar.c").OnRendering(GetPoint().GetX() + w, GetPoint().GetY() + h, w, h);
//					}

					e.Disable2D();

				}
			});

	}

	@Override public boolean UpdateEventCheck(UpdateEventObject e) {

		if(super.UpdateEventCheck(e)) return true;

		ProgressEventCheck(new ProgressEventObject());

		return false;
	}


	public ProgressBar SetProgress(int value) {
		if(value > 0) {
			nowProgress = value;
		}
		return this;
	}

	public ProgressBar SetProgressMax(int value) {
		if(value > 0) {
			maxProgress = value;
		}
		return this;
	}

	public int GetProgress() { return nowProgress; }

	public int GetProgressMax() { return maxProgress; }


	protected ProgressEventListener progressListener_ValueChange;
	protected ProgressEventListener progressListener_ValueZero;
	protected ProgressEventListener progressListener_ValueMax;

	@Override public boolean ProgressEventCheck(ProgressEventObject e) {

		if(nowProgress > maxProgress) {
			nowProgress = maxProgress;
		}

		if(prevProgress != nowProgress) {
			if(progressListener_ValueChange != null) {
				progressListener_ValueChange.OnValueChange(this, e);
			}

			if(nowProgress == 0 && progressListener_ValueZero != null) {
				progressListener_ValueZero.OnValueZero(this, e);
			}

			if(nowProgress == maxProgress && progressListener_ValueMax != null) {
				progressListener_ValueMax.OnValueMax(this, e);
			}
			prevProgress = nowProgress;
		}

		return false;
	}

	/**
	 * コールバックの登録(一括)
	 * @param callback コールバックインタフェース
	 */
	public void SetProgressEventCallback(ProgressEventListener callback) {
		SetProgressEventCallback(ProgressEventListener.EVENT_ValueChange, callback);
		SetProgressEventCallback(ProgressEventListener.EVENT_ValueZero, callback);
		SetProgressEventCallback(ProgressEventListener.EVENT_ValueMax, callback);
	}

	/**
	 * コールバックの登録(指定)
	 * @param eventType イベント種類 (「ProgressEventListener.EVENT_～～」の使用推奨)
	 * @param callback コールバックインタフェース
	 */
	public void SetProgressEventCallback(int eventType, ProgressEventListener callback) {
		if(eventType == ProgressEventListener.EVENT_ValueChange) {
			progressListener_ValueChange = callback;
		} else if(eventType == ProgressEventListener.EVENT_ValueZero) {
			progressListener_ValueZero = callback;
		} else if(eventType == ProgressEventListener.EVENT_ValueMax) {
			progressListener_ValueMax = callback;
		}
	}

}
