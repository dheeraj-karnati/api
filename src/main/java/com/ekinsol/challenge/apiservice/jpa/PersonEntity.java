package com.ekinsol.challenge.apiservice.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ekinsol.challenge.apiservice.bo.Person;
import com.ekinsol.challenge.apiservice.jpa.types.JsonType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "PERSON_TABLE")
@org.hibernate.annotations.TypeDef(name = "JsonType", typeClass = JsonType.class)
@Data
@EqualsAndHashCode
@ToString
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    @Type(type = "JsonType")
    private Person data;

}
