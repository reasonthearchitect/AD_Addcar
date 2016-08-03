package com.rta.addcar.rest; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rta.addcar.stream.CarSource;
import com.rta.addcar.dto.Car;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/addcar")
public class CarRest {

        @Autowired
        private CarSource source;

        Map<String, String> vars = new HashMap<>();

        RestTemplate rt;

        @Value("${demo.carstore.uri}")
        String carstoreuri;

        public CarRest() {
                this.rt = new RestTemplate();
                this.rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                this.vars = new HashMap<>();
        }


        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> process(@RequestBody Car car ) {
                try {
                        Car returns = rt.postForObject(this.carstoreuri, car, Car.class, this.vars);
                        this.source.send(returns);
                        HttpHeaders httpHeaders = new HttpHeaders();
                        return new ResponseEntity<>(returns, httpHeaders, HttpStatus.CREATED);
                } catch (Exception e) {
                        System.out.println("Exception");
                        e.printStackTrace();
                        throw new RuntimeException();
                }
        }
}
