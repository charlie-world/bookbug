package com.charlieworld.bookbug.config.converter;

import com.charlieworld.bookbug.entity.TargetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TargetTypeConverter implements Converter<String, TargetType> {

    @Override
    public TargetType convert(String value) {
        return TargetType.valueOf(value.toUpperCase());
    }
}
