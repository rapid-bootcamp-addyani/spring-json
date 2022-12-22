package com.rapidtech.springjson.model;

import com.rapidtech.springjson.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressModel {
    private Long id;
    private String name;
    private String address;
    private String village;
    private String district;
    private String city;
    private String province;

    public AddressModel(Long id, String name, String address, String village, String district, String city, String province) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.village = village;
        this.district = district;
        this.city = city;
        this.province = province;
    }

    public AddressModel(AddressEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
