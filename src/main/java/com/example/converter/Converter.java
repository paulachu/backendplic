package com.example.converter;

import com.sun.istack.NotNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface Converter<FROM_TYPE, TO_TYPE>  {

    TO_TYPE convertNotNull(final @NotNull FROM_TYPE from);

    default TO_TYPE convert(final FROM_TYPE from) {
        if (from == null)
            return null;
        return convertNotNull(from);
    }

    default List<TO_TYPE> convertList(final List<FROM_TYPE> fromList) {
        if (fromList == null)
        {
            return null;
        }
        return fromList.stream().map(this::convert).collect(Collectors.toList());
    }

    default Set<TO_TYPE> convertSet(final Set<FROM_TYPE> fromList) {
        if (fromList == null)
        {
            return null;
        }
        return fromList.stream().map(this::convert).collect(Collectors.toSet());
    }
}