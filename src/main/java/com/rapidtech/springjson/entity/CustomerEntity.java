package com.rapidtech.springjson.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rapidtech.springjson.model.AddressModel;
import com.rapidtech.springjson.model.CustomerModel;
import com.rapidtech.springjson.model.SchoolModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_tab")
public class CustomerEntity {
//    @Id
//    @TableGenerator(name = "customer_id_generator", table = "sequence_tab",
//            pkColumnName = "gen_name", valueColumnName = "gen_value",
//            pkColumnValue="customer_id", initialValue=0, allocationSize=0)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id_generator")
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "customer_name", length = 100, nullable = false)
//    private String fullName;
//    @Column(name = "address", length = 200)
//    private String address;
//
//    @Column(name = "gender", length = 10, nullable = false)
//    private String gender;
//
//    @Temporal(TemporalType.DATE)
//    @Column(name = "date_of_birth")
//    private Date dateOfBirth;
//
//    @Column(name = "place_of_birth")
//    private String placeOfBirth;
//
//    @OneToMany(mappedBy = "address_join")
//    private Set<AddressEntity> addressJoin = new java.util.LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "school_join")
//    private Set<SchoolEntity> schoolJoin = new java.util.LinkedHashSet<>();
//
//    public CustomerEntity(CustomerModel entity) {
//        BeanUtils.copyProperties(entity, this);
//    }
//
//    public void addAddressList(List<AddressModel> models){
//        for(AddressModel model: models){
//            this.addAddress(new AddressEntity(model));
//        }
//    }
//
//    public void addAddress(AddressEntity address){
//        this.address.add(address);
//        address.setCustomer(this);
//    }
//
//    public void addSchoolList(List<SchoolModel> models){
//        for(SchoolModel model: models){
//            this.addSchool(new SchoolEntity(model));
//        }
//    }
//
//    public void addSchool(SchoolEntity school){
//        this.schools.add(school);
//        school.setCustomer(this);
//    }

    @Id
    @TableGenerator(name = "cus_id_generator", table = "sequence_tab",
            pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="customer_id", initialValue=0, allocationSize=0)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "cus_id_generator")
    private Long id;

    @Column(name = "full_name", length = 100)
    private String fullName;
    @Column(name = "gander", length = 10)
    private String gender;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "place_of_birth", length = 100)
    private String placeOfBirth;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AddressEntity> address = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SchoolEntity> schools = new ArrayList<>();

    public CustomerEntity(CustomerModel model) {
        BeanUtils.copyProperties(model,this);
    }

    public void addAddressList(List<AddressModel> models){
        for(AddressModel model: models){
            this.addAddress(new AddressEntity(model));
        }
    }

    public void addAddress(AddressEntity address){
        this.address.add(address);
        address.setCustomer(this);
    }

    public void addSchoolList(List<SchoolModel> models){
        for(SchoolModel model: models){
            this.addSchool(new SchoolEntity(model));
        }
    }

    public void addSchool(SchoolEntity school){
        this.schools.add(school);
        school.setCustomer(this);
    }
}
