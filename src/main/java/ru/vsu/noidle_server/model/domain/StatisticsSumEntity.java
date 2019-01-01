package ru.vsu.noidle_server.model.domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface StatisticsSumEntity {

    @Value("#{target.type}")
    String getType();

    @Value("#{target.sub_type}")
    String getSubType();

    @Value("#{target.extra_value}")
   String getExtraValue();

    @Value("#{target.sum}")
    Long getSum();
}