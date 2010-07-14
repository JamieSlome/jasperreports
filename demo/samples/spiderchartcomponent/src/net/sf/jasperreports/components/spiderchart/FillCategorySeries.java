/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.components.spiderchart;

import net.sf.jasperreports.charts.JRCategorySeries;
import net.sf.jasperreports.charts.fill.JRFillCategorySeries;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.fill.JRCalculator;
import net.sf.jasperreports.engine.fill.JRExpressionEvalException;
import net.sf.jasperreports.engine.fill.JRFillHyperlinkHelper;
import net.sf.jasperreports.engine.fill.JRFillObjectFactory;


/**
 * @author sanda zaharia (shertage@users.sourceforge.net)
 * @version $Id$
 */
public class FillCategorySeries extends JRFillCategorySeries
{

	/**
	 *
	 */
	protected JRCategorySeries parent = null;

	private Comparable series = null;
	private Comparable category = null;
	private Number value = null;
	private String label = null;
	private JRPrintHyperlink itemHyperlink;
	
	
	/**
	 *
	 */
	public FillCategorySeries(
		JRCategorySeries categorySeries, 
		JRFillObjectFactory factory
		)
	{
		super(categorySeries, factory);
//		factory.put(categorySeries, this);
//
//		parent = categorySeries;
	}


	
	/**
	 *
	 */
	public Comparable getSeries()
	{
		return series;
	}
		
	/**
	 *
	 */
	public Comparable getCategory()
	{
		return category;
	}
		
	/**
	 *
	 */
	public Number getValue()
	{
		return value;
	}
		
	/**
	 *
	 */
	public String getLabel()
	{
		return label;
	}
	
	public JRPrintHyperlink getPrintItemHyperlink()
	{
		return itemHyperlink;
	}
	
	
	/**
	 *
	 */
	public void evaluate(JRCalculator calculator) throws JRExpressionEvalException
	{
		series = (Comparable)calculator.evaluate(getSeriesExpression()); 
		category = (Comparable)calculator.evaluate(getCategoryExpression()); 
		value = (Number)calculator.evaluate(getValueExpression());
		label = (String)calculator.evaluate(getLabelExpression());
		
		if (hasItemHyperlinks())
		{
			evaluateItemHyperlink(calculator);
		}
	}


	public void evaluateItemHyperlink(JRCalculator calculator) throws JRExpressionEvalException
	{
		try
		{
			itemHyperlink = JRFillHyperlinkHelper.evaluateHyperlink(getItemHyperlink(), calculator, JRExpression.EVALUATION_DEFAULT);
		}
		catch (JRExpressionEvalException e)
		{
			throw e;
		}
		catch (JRException e)
		{
			throw new JRRuntimeException(e);
		}
	}

}
