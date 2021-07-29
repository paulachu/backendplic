package com.example.converter;

import com.sun.istack.NotNull;

public interface ConverterUpdate<FROM_TYPE, TO_TYPE> {
    TO_TYPE convertNotNull(final @NotNull FROM_TYPE from, final TO_TYPE to);

    default TO_TYPE convert(final FROM_TYPE from, final TO_TYPE to) {
        if (from == null)
            return null;
        return convertNotNull(from, to);
    }
}