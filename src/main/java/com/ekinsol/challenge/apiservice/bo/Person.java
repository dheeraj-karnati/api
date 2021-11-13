package com.ekinsol.challenge.apiservice.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class Person {
    private String firstName;
    private String lastName;
    private String role;
}
