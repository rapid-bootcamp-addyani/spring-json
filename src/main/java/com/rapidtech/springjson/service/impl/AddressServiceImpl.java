package com.rapidtech.springjson.service.impl;

import com.rapidtech.springjson.entity.AddressEntity;
import com.rapidtech.springjson.model.AddressModel;
import com.rapidtech.springjson.repository.AddressRepo;
import com.rapidtech.springjson.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepo repo;

    @Autowired
    public AddressServiceImpl(AddressRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<AddressModel> getAll() {
        return this.repo.findAll().stream().map(AddressModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AddressModel> getById(Long id) {
        if(id == 0) {
            return Optional.empty();
        }
        Optional<AddressEntity> result = this.repo.findById(id);
        return result.map(AddressModel::new);
    }

    @Override
    public Optional<AddressModel> save(AddressModel model) {
        if(model == null) {
            return Optional.empty();
        }
        AddressEntity entity = new AddressEntity(model);
        try {
            this.repo.save(entity);
            return Optional.of(new AddressModel(entity));
        }catch (Exception e){
            log.error("Customer save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressModel> update(Long id, AddressModel model) {
        if(id == 0) {
            return Optional.empty();
        }

        AddressEntity result = this.repo.findById(id).orElse(null);
        if(result == null){
            return Optional.empty();
        }

        // copy property
        BeanUtils.copyProperties(model, result);
        try {
            this.repo.save(result);
            return Optional.of(new AddressModel(result));
        }catch (Exception e){
            log.error("Customer update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressModel> delete(Long id) {
        if(id == 0) {
            return Optional.empty();
        }

        AddressEntity result = this.repo.findById(id).orElse(null);
        if(result == null){
            return Optional.empty();
        }

        try {
            this.repo.delete(result);
            return Optional.of(new AddressModel(result));
        }catch (Exception e){
            log.error("Customer delete is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
