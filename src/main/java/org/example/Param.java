package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Param {
    private Class<?> type;
    private Object value;
}
