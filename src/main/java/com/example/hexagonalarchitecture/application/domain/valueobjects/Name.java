package com.example.hexagonalarchitecture.application.domain.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name {

    private String firstName;
    private String lastName;
}
