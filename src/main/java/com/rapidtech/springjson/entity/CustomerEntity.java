package com.rapidtech.springjson.entity;

import com.rapidtech.springjson.model.CustomerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_tab")
public class CustomerEntity {
    @Id
    @TableGenerator(name = "customer_id_generator", table = "sequence_tab",
            pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="customer_id", initialValue=0, allocationSize=0)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customer_id_generator")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "gender", length = 10, nullable = false)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @OneToMany(mappedBy = "address_join")
    private Set<AddressEntity> addressJoin = new java.util.LinkedHashSet<>();

    @OneToMany(mappedBy = "school_join")
    private Set<SchoolEntity> schoolJoin = new java.util.LinkedHashSet<>();

    public CustomerEntity(CustomerModel entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
