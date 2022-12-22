package com.rapidtech.springjson.entity;

import com.rapidtech.springjson.model.SchoolModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school_tab")
public class SchoolEntity {
    @Id
    @TableGenerator(name = "school_id_generator", table = "sequence_tab",
            pkColumnName = "gen_name", valueColumnName = "gen_value",
            pkColumnValue="school_id", initialValue=0, allocationSize=0)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "school_id_generator")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "level", length = 100, nullable = false)
    private String level;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private SchoolEntity school_join;

    public SchoolEntity(SchoolModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
