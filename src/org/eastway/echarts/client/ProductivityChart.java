package org.eastway.echarts.client;

import com.googlecode.gchart.client.GChart;

/**
 * Individual productivity chart
 */
public class ProductivityChart extends GChart {
	final String[] groupLabels = { "<html>&nbsp;" };
	final String[] barLabels = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	final String[] barColors = { "blue", "green", "blue", "green", "blue",
			"green", "blue", "green", "blue", "green", "blue", "green" };
	final int MAX_CREDITS = 120;
	final int WIDTH = 400;
	final int HEIGHT = 169;

	public ProductivityChart() {
		double tempValue = 0;
		setChartSize(WIDTH, HEIGHT);
		// setChartTitle("<small>&nbsp;</small>");

		for (int iCurve = 0; iCurve < barLabels.length; iCurve++) {
			addCurve(); // one curve per quarter
			getCurve().getSymbol().setSymbolType(SymbolType.VBAR_SOUTHWEST);
			// getCurve().setLegendLabel(barLabels[iCurve]);
			getCurve().getSymbol().setHovertextTemplate(
					GChart.formatAsHovertext(barLabels[iCurve]
							+ " productivity=${y}"));
			getCurve().getSymbol().setModelWidth(0.7);
			getCurve().getSymbol().setBorderColor("black");
			getCurve().getSymbol().setBorderWidth(2);
			for (int jGroup = 0; jGroup < groupLabels.length; jGroup++) {
				// the '+1' creates a bar-sized gap between groups
				tempValue = Math.random() * MAX_CREDITS;
				getCurve()
						.addPoint(1 + iCurve + jGroup * (barLabels.length + 1),
								tempValue);
				if (tempValue <= 87) {
					getCurve().getSymbol().setBackgroundColor("red");
				} else if (tempValue <= 92) {
					getCurve().getSymbol().setBackgroundColor("yellow");
				} else {
					getCurve().getSymbol().setBackgroundColor("green");
				}

				getCurve().getPoint().setAnnotationText(barLabels[iCurve]);
				getCurve().getPoint().setAnnotationLocation(
						AnnotationLocation.SOUTH);
			}
		}

		for (int i = 0; i < groupLabels.length; i++) {
			// formula centers tick-label horizontally on each group
			getXAxis().addTick(
					barLabels.length / 2. + i * (barLabels.length + 1),
					groupLabels[i]);
		}
		getXAxis().setTickLabelFontSize(20);
		getXAxis().setTickLabelThickness(40);
		getXAxis().setTickLength(6); // small tick-like gap...
		getXAxis().setTickThickness(0); // but with invisible ticks
		getXAxis().setAxisMin(0); // keeps first bar on chart

		getYAxis().setAxisMin(0); // Based on sim revenue range
		getYAxis().setAxisMax(MAX_CREDITS); // of 0 to MAX_REVENUE.
		getYAxis().setTickCount(13);
		getYAxis().setHasGridlines(true);
		getYAxis().setTickLabelFormat("###");
	}
}
