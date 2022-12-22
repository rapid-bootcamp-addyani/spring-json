package com.rapidtech.springjson.service.impl;

import com.rapidtech.springjson.entity.SchoolEntity;
import com.rapidtech.springjson.model.SchoolModel;
import com.rapidtech.springjson.repository.SchoolRepo;
import com.rapidtech.springjson.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {
    private SchoolRepo repo;

    @Autowired
    public SchoolServiceImpl(SchoolRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<SchoolModel> getAll() {
        return this.repo.findAll().stream().map(SchoolModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SchoolModel> getById(Long id) {
        if(id == 0) {
            return Optional.empty();
        }
        Optional<SchoolEntity> result = this.repo.findById(id);
        return result.map(SchoolModel::new);
    }

    @Override
    public Optional<SchoolModel> save(SchoolModel model) {
        if(model == null) {
            return Optional.empty();
        }
        SchoolEntity entity = new SchoolEntity(model);
        try {
            this.repo.save(entity);
            return Optional.of(new SchoolModel(entity));
        }catch (Exception e){
            log.error("Customer save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SchoolModel> update(Long id, SchoolModel model) {
        if(id == 0) {
            return Optional.empty();
        }

        SchoolEntity result = this.repo.findById(id).orElse(null);
        if(result == null){
            return Optional.empty();
        }

        // copy property
        BeanUtils.copyProperties(model, result);
        try {
            this.repo.save(result);
            return Optional.of(new SchoolModel(result));
        }catch (Exception e){
            log.error("Customer update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<SchoolModel> delete(Long id) {
        if(id == 0) {
            return Optional.empty();
        }

        SchoolEntity result = this.repo.findById(id).orElse(null);
        if(result == null){
            return Optional.empty();
        }

        try {
            this.repo.delete(result);
            return Optional.of(new SchoolModel(result));
        }catch (Exception e){
            log.error("Customer delete is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
