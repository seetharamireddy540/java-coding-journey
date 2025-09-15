package com.example.javacodingjourney.domain;

import java.time.ZonedDateTime;

public record Journey(String origin,
                      String destination,
                      ZonedDateTime arrivalTime,
                      ZonedDateTime departureTime){

}