package org.arabikitouhu.window;

import org.arabikitouhu.event.EventManager;
import org.arabikitouhu.event.keyboard.KeyboardObserver;
import org.arabikitouhu.event.mouse.MouseObserver;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.event.rendering.RenderingObserver;
import org.arabikitouhu.event.update.UpdateObserver;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;

/**
 * ウィンドウクラス
 * @author arabikitouhu
 * @version 0.0
 * @implements MouseEventSender, KeyboardEventSender, RenderingEventSender, UpdateEventSender
 */
public abstract class Window implements EventManager {

	/**
	 * コンストラクタ(インスタンス生成前に、WindowStateObjectの読み込みをしてください。WindowStateObject.ReadFile()で読み込めます。)
	 */
	public Window() {

		WindowState.Initialize();

		Initialize();	}

	/** インスタンス生成時にこのメソッドが実行されます。 */
	public abstract void Initialize();

	/**
	 * Windowループ開始処理
	 * @memo 60FPS実行<br>時間毎にOnDrawEventListen(new DrawEventObject(this.m_State));を実行。
	 */
	public void Start() {

		MouseObserver.Start();
		MouseObserver.SetSender(this);

		KeyboardObserver.Start();
		KeyboardObserver.SetSender(this);

		UpdateObserver.Start();
		UpdateObserver.SetSender(this);

		RenderingObserver.Start(this);
		RenderingObserver.SetSender(this);


		this.Dispose();
	}

	/** 終了処理(ゲーム終了時に呼ばれる)
	 * @process SceneManager.Clear()の実行→SoundManager.Clear()の実行→AL.destroy()の実行→<br>
	 * FontManager.Clear()の実行→SpriteManager.Clear()の実行→TextureManager.Clear()の実行→<br>
	 * Display.destroy()の実行
	 * */
	public void Dispose() {
		AL.destroy();
		Display.destroy();
	}

	/**
	 * Windowループの終了処理
	 * @memo Running = falseのみ
	 */
	public void Finish() {
		MouseObserver.Stop();
		KeyboardObserver.Stop();
		UpdateObserver.Stop();
		RenderingObserver.Stop();
	}

	/** マウスイベント前処理部分(必ず呼ばれる) */
	public void OnPreMouseEventProccess(MouseEventObject e){ }

	/** マウスイベント処理部分(必ず呼ばれる) */
	public abstract void OnMouseEventProccess(MouseEventObject e);

	@Override public boolean MouseEventCheck(MouseEventObject e) {
		OnPreMouseEventProccess(e);

		OnMouseEventProccess(e);
		return true;
	}

	/** キーボードイベント前処理部分(必ず呼ばれる) */
	public void OnPreKeyboardEventProccess(KeyboardEventObject e){
		if(Display.isCloseRequested()) {
			Finish();
		}

	}

	/** キーボードイベント処理部分(必ず呼ばれる) */
	public abstract void OnKeyboardEventProccess(KeyboardEventObject e);

	@Override public boolean KeyboardEventCheck(KeyboardEventObject e) {
		OnPreKeyboardEventProccess(e);

		OnKeyboardEventProccess(e);
		return true;
	}

	/** 描画イベント前処理部分(必ず呼ばれる) */
	public void OnPreRenderingEventProccess(RenderingEventObject e){ }

	/** 描画イベント処理部分(必ず呼ばれる) */
	public abstract void OnRenderingEventProccess(RenderingEventObject e);

	@Override public boolean RenderingEventCheck(RenderingEventObject e) {
		OnPreRenderingEventProccess(e);

		OnRenderingEventProccess(e);
		return true;
	}

	/** 更新イベント前処理部分(必ず呼ばれる) */
	public void OnPreUpdateEventProccess(UpdateEventObject e){ }

	/** 更新イベント処理部分(必ず呼ばれる) */
	public abstract void OnUpdateEventProccess(UpdateEventObject e);

	@Override public boolean UpdateEventCheck(UpdateEventObject e) {
		OnPreUpdateEventProccess(e);

		OnUpdateEventProccess(e);
		return true;
	}



}
