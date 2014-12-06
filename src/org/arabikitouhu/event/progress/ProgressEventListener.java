package org.arabikitouhu.event.progress;

import org.arabikitouhu.component.Component;
import org.arabikitouhu.event.object.ProgressEventObject;

public class ProgressEventListener {

	public static final ProgressEventListener DummyListener = new ProgressEventListener();


	public static final int EVENT_ValueChange = 0;
	public static final int EVENT_ValueZero = 1;
	public static final int EVENT_ValueMax = 2;


	public void OnValueChange(Component sender, ProgressEventObject e){ }

	public void OnValueZero(Component sender, ProgressEventObject e){ }

	public void OnValueMax(Component sender, ProgressEventObject e){ }

}
