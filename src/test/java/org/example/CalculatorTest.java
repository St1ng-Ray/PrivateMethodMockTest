package org.example;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    private final Calculator calculator = spy(Calculator.class);

    @Test
    public void returnTotalIncome(){
        Employee employee = spy(Employee.class);
        employee.setSalary(25);
        employee.setExtraIncome(5);
        int result = calculator.totalIncome(employee);


        assertEquals(50, result);
    }

    @Test
    public void returnTotalIncomeWithoutBonus() throws Throwable {
        Employee employee = spy(Employee.class);
        employee.setSalary(25);
        employee.setExtraIncome(5);
        var result = Executor.executePrivateMethod(Calculator.class,           "totalIncomePrivateMethod", Param.builder().type(Employee.class)
                .value(employee).build());

        assertEquals(30, result);
    }
}