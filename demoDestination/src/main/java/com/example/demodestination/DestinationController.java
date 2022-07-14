package com.example.demodestination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
@Slf4j
public class DestinationController {
    @GetMapping("/destination")
    public String destination(RequestEntity resquest){
        log.info("m=destination, message=Destination begin");
        HttpHeaders headers = resquest.getHeaders();
        headers.forEach((key, value) ->
                log.info("m=destination, message=key:{} -> value:{}", key, value));
        if(Math.abs(new Random().nextInt()) % 3 == 0)
            log.warn("m=destination, message=Something happened");
        log.info("m=destination, message=Destination end");
        return "Mars";

    }
}
