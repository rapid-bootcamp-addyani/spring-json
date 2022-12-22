package com.rapidtech.springjson.entity;

import com.rapidtech.springjson.model.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_tab")
public class AddressEntity {
    @Id
    @TableGenerator(name = "address_id_generator", table = "sequence_tab",
            pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="address_id", initialValue=0, allocationSize=0)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "address_id_generator")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_name", length = 100, nullable = false)
    private String name;
    @Column(name = "address", length = 100, nullable = false)
    private String address;
    @Column(name = "village", length = 100, nullable = false)
    private String village;
    @Column(name = "district", length = 100, nullable = false)
    private String district;
    @Column(name = "city", length = 100, nullable = false)
    private String city;
    @Column(name = "province", length = 100, nullable = false)
    private String province;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private AddressEntity address_join;

    public AddressEntity(AddressModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
