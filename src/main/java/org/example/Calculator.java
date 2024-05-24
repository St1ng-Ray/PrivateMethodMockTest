package org.example;

public class Calculator {

    private final Integer bonus = 20;

    public Integer totalIncome( Employee employee){
        return totalIncomePrivateMethod(employee)+bonus;
    }

    private Integer totalIncomePrivateMethod(Employee employee){
        return employee.getSalary()+ employee.getExtraIncome();
    }
}
