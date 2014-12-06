package org.arabikitouhu.event;

import org.arabikitouhu.event.keyboard.KeyboardEventSender;
import org.arabikitouhu.event.mouse.MouseEventSender;
import org.arabikitouhu.event.rendering.RenderingEventSender;
import org.arabikitouhu.event.update.UpdateEventSender;

public interface EventManager extends MouseEventSender, KeyboardEventSender, UpdateEventSender, RenderingEventSender {
}
