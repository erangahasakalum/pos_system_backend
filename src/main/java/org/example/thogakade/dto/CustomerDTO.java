package org.example.thogakade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    private String id;
    private String name;
    private String city;
    private String tel;
}
