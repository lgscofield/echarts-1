package org.eastway.echarts.client.common;

public abstract class ColumnDefinition<T> {
	public abstract void render(T t, StringBuilder sb);

	public boolean isClickable() {
		return false;
	}

	public boolean isSelectable() {
		return false;
	}

	public boolean isContextMenu() {
		return false;
	}

	public String getHeader(T t) {
		return null;
	}
}
