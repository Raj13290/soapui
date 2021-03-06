/*
 *  soapUI, copyright (C) 2004-2012 smartbear.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.submit.filters;

import org.apache.log4j.Logger;

import com.eviware.soapui.impl.support.AbstractHttpRequest;
import com.eviware.soapui.impl.wsdl.submit.transports.http.BaseHttpRequestTransport;
import com.eviware.soapui.model.iface.SubmitContext;
import com.eviware.soapui.support.xml.XmlUtils;

/**
 * RequestFilter for stripping whitespaces
 * 
 * @author Ole.Matzura
 */

public class StripWhitespacesRequestFilter extends AbstractRequestFilter
{
	private final static Logger log = Logger.getLogger( PropertyExpansionRequestFilter.class );

	public void filterAbstractHttpRequest( SubmitContext context, AbstractHttpRequest<?> wsdlRequest )
	{
		if( !wsdlRequest.isStripWhitespaces() )
			return;

		String content = ( String )context.getProperty( BaseHttpRequestTransport.REQUEST_CONTENT );
		if( content == null )
		{
			log.warn( "Missing request content in context, skipping stripWhitespaces" );
		}
		else
		{
			content = XmlUtils.stripWhitespaces( content );
			context.setProperty( BaseHttpRequestTransport.REQUEST_CONTENT, content );
		}
	}
}
