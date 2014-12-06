package org.arabikitouhu.component;

import java.util.ArrayList;

import org.arabikitouhu.event.keyboard.KeyboardEventSender;
import org.arabikitouhu.event.mouse.MouseEventSender;
import org.arabikitouhu.event.object.KeyboardEventObject;
import org.arabikitouhu.event.object.MouseEventObject;
import org.arabikitouhu.event.object.RenderingEventObject;
import org.arabikitouhu.event.object.UpdateEventObject;
import org.arabikitouhu.event.rendering.RenderingEventSender;
import org.arabikitouhu.event.update.UpdateEventSender;
import org.arabikitouhu.point.Point;

public abstract class ContainerComponent extends Component {

	protected ArrayList<Component> m_Children;

	public ContainerComponent() {
		super();
		m_Children = new ArrayList<Component>();
	}

	/** 子コンポーネントの追加 */
	public void AddChild(Component value) {
		value.Initialize();
		value.SetParent(this);
		Point p = value.GetPoint();
		value.SetPoint(new Point(p.GetX() + GetPoint().GetX(), p.GetY() + GetPoint().GetY()));
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

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof MouseEventSender) {
				if(((MouseEventSender)child).MouseEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		if(!isCancel) {
			isCancel = super.MouseEventCheck(e);
		}

		return isCancel;
	}

	@Override public boolean KeyboardEventCheck(KeyboardEventObject e) {

		boolean isCancel = false;

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof KeyboardEventSender) {
				if(((KeyboardEventSender)child).KeyboardEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		if(!isCancel) {
			isCancel = super.KeyboardEventCheck(e);
		}

		return isCancel;
	}

	@Override public boolean RenderingEventCheck(RenderingEventObject e) {

		boolean isCancel = false;

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof RenderingEventSender) {
				if(((RenderingEventSender)child).RenderingEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		if(!isCancel) {
			isCancel = super.RenderingEventCheck(e);
		}

		return isCancel;
	}

	@Override public boolean UpdateEventCheck(UpdateEventObject e) {

		boolean isCancel = false;

		for(int i = 0; i < m_Children.size(); i++) {
			Component child = m_Children.get(i);
			if(child instanceof UpdateEventSender) {
				if(((UpdateEventSender)child).UpdateEventCheck(e)) {
					isCancel = true;
					break;
				}
			}
		}

		if(!isCancel) {
			isCancel = super.UpdateEventCheck(e);
		}

		return isCancel;
	}

}
