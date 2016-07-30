package com.rta.addcar.test.rest

import com.rta.addcar.dto.Car
import com.rta.addcar.rest.CarRest
import com.rta.addcar.stream.CarSource

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification


class CarRestSpec extends Specification {

    CarRest carRest;

    def setup() {
        this.carRest        = new CarRest();
        this.carRest.source = Mock(CarSource);
        this.carRest.rt     = Mock(RestTemplate);
    }

    def "simple test for the rest endpoint"() {

        when:
        ResponseEntity responseEntity = this.carRest.process([] as Car);

        then:
        1 * this.carRest.source.send(_);

        // TODO: Make this work with the verify code.
        // 1 * this.carRest.rt.postForObject(_ as String, _ as Object, _ as Class ,_ as Map) >> [id:"not null"] as Car;
        responseEntity != null;
        responseEntity.getStatusCode() == HttpStatus.CREATED;
    }
}
