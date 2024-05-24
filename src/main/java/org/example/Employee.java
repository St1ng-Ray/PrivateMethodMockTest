package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Employee {

    private String name;

    private Integer salary;

    private Integer extraIncome;

}