package ru.vsu.noidle_server.model.domain;

import org.apache.logging.log4j.message.StringFormattedMessage;

public interface StatisticsDashboardEntity {
    String getType();

    String getSubtype();

    String getExtravalue();

    Long getValue();

    void setType(String type);

    void setSubtype(String subType);

    void setExtravalue(String extraValue);

    void setValue(Long value);
}