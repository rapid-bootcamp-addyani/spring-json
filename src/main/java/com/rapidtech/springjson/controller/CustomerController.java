package com.rapidtech.springjson.controller;

import com.rapidtech.springjson.model.CustomerRequestModel;
import com.rapidtech.springjson.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }


    @PostMapping
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerRequestModel request){
        request.getCustomers().forEach(n -> {
            System.out.println(n);
        });
        return ResponseEntity.ok().body(request);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> save(@RequestBody CustomerRequestModel request){
        return ResponseEntity.ok().body(
                customerService.saveAll(request)
        );
    }

}
