package edu.sandbox.spring.web.onlinelibrary.converters;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

public interface Converter<I, O> {

    Optional<O> convert(I object);

    default List<O> convert(List<? extends I> objects) {
        return isEmpty(objects) ? emptyList() : objects.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .flatMap(Optional::stream)
                .filter(Objects::nonNull)
                .toList();
    }
}
