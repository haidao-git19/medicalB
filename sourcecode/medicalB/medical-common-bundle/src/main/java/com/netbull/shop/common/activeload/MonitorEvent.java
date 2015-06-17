package com.netbull.shop.common.activeload; 
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 监听事件;
 */
public class MonitorEvent {

	private Object lock = new Object();
	@SuppressWarnings("rawtypes")
	private List monitorListenerList = new ArrayList();

	@SuppressWarnings("unchecked")
	public void addListener(IMonitorListener listener) {
		synchronized (lock) {
			if (!monitorListenerList.contains(listener)) {
				monitorListenerList.add(listener);
			}
		}
	}

	public void removeListener(IMonitorListener listener) {
		synchronized (lock) {
			if (monitorListenerList.contains(listener)) {
				monitorListenerList.remove(listener);
			}
		}
	}

	public void removeAllListener() {
		synchronized (lock) {
			monitorListenerList.clear();
		}
	}

	/**
	 * 触发事件可由子类重载
	 */
	@SuppressWarnings("rawtypes")
	public void fireEvent() {
		synchronized (lock) {
			for (Iterator it = monitorListenerList.iterator(); it.hasNext();) {
				IMonitorListener listener = (IMonitorListener) it.next();
				listener.update(this);
			}
		}
	}

}
