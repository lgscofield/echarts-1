/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.resources.client.ImageResource;

public class GlobalResources {

	public static Resources resources;
	public static CssResource styles;

	public interface Resources extends ClientBundle {
		@NotStrict
		@Source("echarts.css")
		Styles css();
		@Source("default_photo.jpg")
		ImageResource defaultPhoto();
	}

	public interface Styles extends CssResource {
		String table();
	}

	static {
		resources = GWT.create(Resources.class);
	}

	public static Resources resources() {
		return resources;
	}

	public static Styles styles() {
		return resources.css();
	}

	public static DateTimeFormat getDateFormat() {
		return DateTimeFormat.getFormat("M/d/y");
	}

	public static DateTimeFormat getExtendedISODateTimeFormat() {
		return DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");
	}

	public static DateTimeFormat getBasicISODateTimeFormat() {
		return DateTimeFormat.getFormat("yyyyMMdd'T'HHmmss.SSSZ");
	}

	public static DateTimeFormat getDateTimeFormat() {
		return DateTimeFormat.getFormat("h:mm a M/d/y");
	}

	public static DateTimeFormat getTimeFormat() {
		return DateTimeFormat.getFormat("h:mm a");
	}
}
