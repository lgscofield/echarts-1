package org.eastway.echarts.client;

import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CachingDispatchAsync {
	public <A extends Action<R>, R extends Result> void execute(A action, AsyncCallback<R> callback);
	public <A extends Action<R>, R extends Result> void executeWithCache(final A action, final AsyncCallback<R> callback);
	public void clear();
}
