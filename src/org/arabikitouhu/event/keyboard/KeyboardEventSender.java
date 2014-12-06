package org.arabikitouhu.event.keyboard;

import org.arabikitouhu.event.object.KeyboardEventObject;


/**
 * キーボードイベント リスナークラス
 * @author arabikitouhu
 * @version 0.0
 */
public interface KeyboardEventSender {

	boolean KeyboardEventCheck(KeyboardEventObject e);
}
