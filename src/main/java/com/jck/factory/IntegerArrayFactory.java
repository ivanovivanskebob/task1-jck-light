package com.jck.factory;

import com.jck.entity.*;
import com.jck.entity.builder.IntegerArrayBuilder;
import com.jck.parser.*;
import com.jck.repository.ArrayRepository;

public class IntegerArrayFactory extends ArrayFactory {
    private ArrayParser parser;
    private int counter;

    public IntegerArrayFactory() {
        parser = new ArrayParserImpl();
        counter = 0;
    }

    @Override
    public NumberArray create(String line) {
        counter++;
        String id = "array_" + counter;
        String name = "Array " + counter;

        int[] values = parser.parse(line);

        IntegerArray array = new IntegerArrayBuilder()
                .setId(id)
                .setName(name)
                .setValues(values)
                .build();

        ArrayRepository.getInstance().add(array);
        return array;
    }
}
