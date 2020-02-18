package com.deloitte.ford.bindypoc.service.impl;


import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Service;

import com.deloitte.ford.bindypoc.model.Order;
import com.deloitte.ford.bindypoc.service.BindyService;

@Service
public class BindyServiceImpl implements BindyService{

	@Override
	public Order getOrder() throws Exception {
		
		Order order=null;

		final BindyCsvDataFormat bindy=new BindyCsvDataFormat(Order.class);
		RouteBuilder rb = new RouteBuilder() {

			
			/*
			 * @Override public void configure() throws Exception { from("file:parse.txt")
			 * .convertBodyTo(Order.class) .to("file:parse_out.txt"); }
			 */
			
			
			  @Override public void configure() throws Exception { from("direct:start")
			  .unmarshal(bindy); }
			 
		};

		CamelContext camelContext=new DefaultCamelContext();
		camelContext.addRoutes(rb);

		camelContext.start();
		try {
	        //ConsumerTemplate consumer = camelContext.createConsumerTemplate();
	        ProducerTemplate producer = camelContext.createProducerTemplate();
	        order = producer.requestBody("direct:start", "1,Ford,World,500", Order.class);
	        //order = consumer.receiveBody("file:parse_out.txt", Order.class);
	        camelContext.stop();
	    } finally {
	    	
	    }
		
		return order;
	}
}
