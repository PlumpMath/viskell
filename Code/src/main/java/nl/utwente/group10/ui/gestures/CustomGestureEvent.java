package nl.utwente.group10.ui.gestures;

import javafx.event.Event;
import javafx.event.EventType;

public class CustomGestureEvent extends Event {

	public static final EventType<CustomGestureEvent> ANY = new EventType<>(
			Event.ANY, "ANY");
	public static final EventType<CustomGestureEvent> TAP = new EventType<>(
			ANY, "TAP");
	public static final EventType<CustomGestureEvent> TAP_HOLD = new EventType<>(
			ANY, "TAP_HOLD");
	private EventType<CustomGestureEvent> eventType;

	public CustomGestureEvent(EventType<CustomGestureEvent> eventType) {
		super(eventType);
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		String result;
		if (eventType.equals(ANY)) {
			result = "CustomGestureEvent type = ANY";
		} else if (eventType.equals(TAP)) {
			result = "CustomGestureEvent type = TAP";
		} else if (eventType.equals(TAP_HOLD)) {
			result = "CustomGestureEvent type = TAP_HOLD";
		} else {
			result = "Nog niet opgenomen CustomGestureEvent";
		}
		return result;
	}
}