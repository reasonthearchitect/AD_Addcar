package com.rta.addcar.test.stream

import com.rta.addcar.dto.Car
import com.rta.addcar.stream.CarSource

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.messaging.MessageChannel
import spock.lang.Specification

class CarSourceSpec extends Specification {

    CarSource carSource;

    def setup() {
        this.carSource           = new CarSource();
        this.carSource.mapper    = new ObjectMapper();
        this.carSource.post      = Mock(MessageChannel);
    }

    def "simple test for teh source"() {

        when:
        this.carSource.send([] as Car);

        then:
        1 * this.carSource.post.send(_)
    }
}
