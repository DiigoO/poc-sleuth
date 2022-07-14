package com.example.demo;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/spaceship")
@Slf4j
@RequiredArgsConstructor
public class SpaceShipRestController {

    private final DestinationClient destinationClient;

    @GetMapping("/flyway/{fuel}")
    public void fly(@PathVariable Float fuel){
        flyNow(fuel);
    }

    private void flyNow(Float fuel) {
        log.info("m=flyNow, nessage=FLY BEGIN {}", fuel);
        int work = Math.abs(new Random().nextInt()% 30000000) + 1000000;
        for (int i = 0; i < work; i++)
            log.debug("m=flyNow, message=Working..., percentage={}", i);
        if(fuel < 30)
            throw new RuntimeException("Not goog");

        try {
            ResponseEntity<String> response = destinationClient.destination();

            log.info("m=flyNow, message=Wooooosh let us fly to {}", response.getBody());
        } catch (FeignException e) {
            log.error("m=flyNow, message={}", e.getMessage());
        }

        log.info("m=flyNow, message=Fly end");

    }

}
