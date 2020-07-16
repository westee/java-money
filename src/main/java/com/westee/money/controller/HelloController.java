package com.westee.money.controller;

import com.westee.money.model.service.Greeting;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Api(value = "Hello Controller")
public class HelloController {

    private AtomicLong counter = new AtomicLong();

    @ApiOperation(value = "测试", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Hello Controller")
    })
    @GetMapping("v1.0/greeting")
    public Greeting sayHello(@RequestParam("name") String name){
        return new Greeting(counter.incrementAndGet(), name);
    }
}
