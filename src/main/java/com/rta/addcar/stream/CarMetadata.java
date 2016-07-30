package com.rta.addcar.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface CarMetadata {
	 
		@Output("newcar")
    	MessageChannel post();
}