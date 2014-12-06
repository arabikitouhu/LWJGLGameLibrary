package org.arabikitouhu.event.progress;

import org.arabikitouhu.event.object.ProgressEventObject;

public interface ProgressEventSender {

	/**
	 * イベント判定
	 * @param e イベントオブジェクト
	 * @return true・・・判定の中断
	 */
	boolean ProgressEventCheck(ProgressEventObject e);
}
