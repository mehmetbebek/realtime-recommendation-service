package com.hepsiburada.realtime_recommendation_service_stream_reader.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SourceType {

    MOBILE_APP("mobile-app"),
    DESKTOP("desktop"),
    MOBILE_WEB("mobile-web");

    @JsonValue
    private String source;

}