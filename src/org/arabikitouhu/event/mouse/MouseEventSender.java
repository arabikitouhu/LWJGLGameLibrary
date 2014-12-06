package org.arabikitouhu.event.mouse;

import org.arabikitouhu.event.object.MouseEventObject;

/**
 * マウスイベントを受け取り、判断するインタフェース
 * @author arabikitouhu
 * @version 0.0
 */
public interface MouseEventSender {

	/**
	 * イベント判定
	 * @param e イベントオブジェクト
	 * @return true・・・判定の中断
	 */
	boolean MouseEventCheck(MouseEventObject e);
}
