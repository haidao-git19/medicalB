package com.netbull.shop.common.activeload;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 监听线程类;
 * @author elvis
 */
@SuppressWarnings("rawtypes")
public class MonitorThread extends Thread {

	private List monitorEventList = new ArrayList();
	private long interval;
	private boolean isMonitor = false;
	private Object lock = new Object();
	private static MonitorThread monitor;

	synchronized public static MonitorThread getInstance(long intervalSecond) {
		if (monitor == null) {
			monitor = new MonitorThread(intervalSecond);
		} else {
			monitor.setInterval(intervalSecond);
		}
		return monitor;
	}

	synchronized public static MonitorThread getInstance() {
		if (monitor == null) {
			monitor = new MonitorThread(1);
		}
		return monitor;
	}

	/**
	 * 构造方法
	 * 
	 * @param intervalSecond
	 *            监听间隔时间
	 */
	public MonitorThread(long intervalSecond) {
		this.interval = intervalSecond;
	}

	@SuppressWarnings("unchecked")
	public void addEvent(MonitorEvent event) {
		synchronized (lock) {
			if (!monitorEventList.contains(event)) {
				monitorEventList.add(event);
			}
		}
	}

	public void removeEvent(MonitorEvent event) {
		synchronized (lock) {
			if (monitorEventList.contains(event)) {
				monitorEventList.remove(event);
			}
		}
	}

	public void removeAllEvent() {
		synchronized (lock) {
			monitorEventList.clear();
		}
	}

	/**
	 * 监听主方法，每隔一段间隔触发事件列表
	 */
	public void run() {
		if (isMonitor) {
			return;
		}
		isMonitor = true;
		try {
			while (isMonitor) {

				synchronized (lock) {
					for (Iterator it = monitorEventList.iterator(); it
							.hasNext();) {
						MonitorEvent event = (MonitorEvent) it.next();
						event.fireEvent();
					}
				}
				Thread.sleep(interval);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			isMonitor = false;
		}
	}

	/**
	 * 结束监听，并不是马上结束只是把标致设为结束
	 */
	public void end() {
		isMonitor = false;
	}

	/**
	 * 是否正在监听
	 * 
	 * @return
	 */
	public boolean isMonitor() {
		return isMonitor;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}
}
