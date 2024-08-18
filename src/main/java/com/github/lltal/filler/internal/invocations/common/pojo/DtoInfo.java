package com.github.lltal.filler.internal.invocations.common.pojo;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
public class DtoInfo {

    @NonNull
    private Class<?> dto;

    private Map<Integer, DtoFieldInfo> fields;
}
