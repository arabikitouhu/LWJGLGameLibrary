package org.arabikitouhu.scene;

import java.util.ArrayList;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.EventManager;
import org.arabikitouhu.event.keyboard.KeyboardEventSender;
import org.arabikitouhu.event.mouse.MouseEventSender;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.event.rendering.RenderingEventSender;
import org.arabikitouhu.event.update.UpdateEventSender;
import org.arabikitouhu.window.Window;

/**
 * シーンクラス
 * @author arabikitouhu
 * @version 0.0
 */
public abstract class Scene implements EventManager {

	protected Window m_Parent;
	/** [GET]親コンポーネント */ public Window GetParent() { return this.m_Parent; }
	/** [SET]親コンポーネント */ public Scene SetParent(Window value) { this.m_Parent = value; return this; }

//	protected String m_Id;
//	/** [GET]ID */ public String GetID() { return this.m_Id; }
//	/** [SET]ID */ public Scene SetID(String value) { this.m_Id = value; return this; }
//
//
	protected ArrayList<Component> m_Children;

	public abstract void Open();

	public abstract void Close();

	/**
	 * コンストラクタ
	 * @param name 管理名
	 * @param parent 親のウィンドウクラス
	 */
	public Scene(Window parent) {
		SetParent(parent);

		this.m_Children = new ArrayList<Component>();
	}

	/**
	 * 初期化処理<br>
	 * シーンが切り替わる毎に実行される。<br>
	 */
	public abstract void Initialize();

	/**
	 * 終了処理<br>
	 * シーンが切り替わる毎に実行される。<br>
	 */
	public abstract void Dispose();

	/** 子コンポーネントの追加 */
	public void AddChild(Component value) {
		value.Initialize();
		m_Children.add(value);
	}

	/** 子コンポーネントの削除 */
	public void DelChild(Component value) {
		for(int i = 0; i < m_Children.size(); i++) {
			if(m_Children.get(i).GetName() == value.GetName()) {
				m_Children.remove(i);
				break;
			}
		}
	}

	@Override public boolean MouseEventCheck(MouseEventObject e) {

		boolean isCancel = false;
		OnPreMouseEventProccess(e);

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof MouseEventSender) {
				if(((MouseEventSender)child).MouseEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		OnMouseEventProccess(e);
		return isCancel;
	}

	@Override public boolean KeyboardEventCheck(KeyboardEventObject e) {

		boolean isCancel = false;
		OnPreKeyboardEventProccess(e);

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof KeyboardEventSender) {
				if(((KeyboardEventSender)child).KeyboardEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		OnKeyboardEventProccess(e);
		return isCancel;
	}

	@Override public boolean RenderingEventCheck(RenderingEventObject e) {

		boolean isCancel = false;
		OnPreRenderingEventProccess(e);

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof RenderingEventSender) {
				if(((RenderingEventSender)child).RenderingEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		OnRenderingEventProccess(e);
		return isCancel;

	}

	@Override public boolean UpdateEventCheck(UpdateEventObject e) {

		boolean isCancel = false;
		OnPreUpdateEventProccess(e);

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof UpdateEventSender) {
				if(((UpdateEventSender)child).UpdateEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		OnUpdateEventProccess(e);
		return isCancel;

	}


	/** マウスイベント前処理部分(必ず呼ばれる) */
	public void OnPreMouseEventProccess(MouseEventObject e){ }

	/** マウスイベント処理部分(必ず呼ばれる) */
	public abstract void OnMouseEventProccess(MouseEventObject e);

	/** キーボードイベント前処理部分(必ず呼ばれる) */
	public void OnPreKeyboardEventProccess(KeyboardEventObject e){ }

	/** キーボードイベント処理部分(必ず呼ばれる) */
	public abstract void OnKeyboardEventProccess(KeyboardEventObject e);

	/** 描画イベント前処理部分(必ず呼ばれる) */
	public void OnPreRenderingEventProccess(RenderingEventObject e){ }

	/** 描画イベント処理部分(必ず呼ばれる) */
	public abstract void OnRenderingEventProccess(RenderingEventObject e);

	/** 更新イベント前処理部分(必ず呼ばれる) */
	public void OnPreUpdateEventProccess(UpdateEventObject e){ }

	/** 更新イベント処理部分(必ず呼ばれる) */
	public abstract void OnUpdateEventProccess(UpdateEventObject e);

}
