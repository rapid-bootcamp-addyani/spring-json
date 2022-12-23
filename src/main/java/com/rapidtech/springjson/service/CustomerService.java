package com.rapidtech.springjson.service;

import com.rapidtech.springjson.model.AddressModel;
import com.rapidtech.springjson.model.CustomerModel;
import com.rapidtech.springjson.model.CustomerRequestModel;
import com.rapidtech.springjson.model.CustomerResponseModel;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerModel> getAll();
    Optional<CustomerModel> getById(Long id);

    Optional<CustomerModel> save(CustomerModel model);
    Optional<CustomerResponseModel> saveAll(CustomerRequestModel request);
    Optional<CustomerModel> update(Long id, CustomerModel model);
    Optional<CustomerModel> delete(Long id);
}
