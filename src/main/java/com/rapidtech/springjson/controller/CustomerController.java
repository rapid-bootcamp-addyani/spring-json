package com.rapidtech.springjson.controller;

import com.rapidtech.springjson.model.CustomerModel;
import com.rapidtech.springjson.model.CustomerRequestModel;
import com.rapidtech.springjson.model.ResponseModel;
import com.rapidtech.springjson.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;
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
                    ArrayList<Map<String, String>> list = new ArrayList<>();
                    JSONArray array = new JSONArray(n.getAddress());
                    for (int i = 0; i < array.length(); i++) {
                        try {
                            JSONObject object = array.getJSONObject(i);
                            Map<String, String> map = new LinkedHashMap<>();
                            map.put("key", object.getString("key"));
                            map.put("value", object.getString("value"));
                            list.add(map);
                        } catch (Exception e) {
                            log.error("Error Get Array Address: {}", e.getMessage());
                        }
                    }

                    // Print the key-value pairs
                    for (Map<String, String> map : list) {
                        System.out.println("key: " + map.get("key") + ", value: " + map.get("value"));
                    }
        }
        );


        return ResponseEntity.ok().body(request);
    }



//    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> saveCustomerDetail(@RequestBody CustomerRequestModel request){
//        try {
//            Optional<CustomerModel> result = customerService.save(request);
//            return ResponseEntity.ok().body(
//                    new ResponseModel(200,"SUCCESS", result)
//            );
//        }catch (Exception e){
//            log.error("Error Save", e.getMessage());
//            return ResponseEntity.ok().body(
//                    new ResponseModel(200,"SUCCESS", "e")
//            );
//        }
//    }
}
