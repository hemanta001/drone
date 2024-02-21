package com.drone.api.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private Object data;
    private List<Object> errors;
    private Map<String, Object> meta = new LinkedHashMap<>();

    public ResponseDto addMeta(String key, Object value) {
        meta.put(key, value);
        return this;
    }

    public ResponseDto(Object data) {
        this.data = data;
    }

    public void addError(Object error) {
        if (this.errors == null) {
            this.errors = new ArrayList();
        }
        this.errors.add(error);
    }
}
