package ru.vsu.noidle_server.model.domain;


import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

public class StatisticsSumEntity {
    String extravalue;


    String subtype;

    String type;

    public Long getSum() {
        return sum;
    }

    public StatisticsSumEntity setSum(Long sum) {
        this.sum = sum;
        return this;
    }

    Long sum;

    public StatisticsSumEntity(String extravalue, String subtype, String type,Long sum) {
        this.extravalue = extravalue;
        this.subtype = subtype;
        this.type = type;
        this.sum=sum;
    }

    public StatisticsSumEntity() {
    }

    public String getExtravalue() {
        return extravalue;
    }

    public StatisticsSumEntity setExtravalue(String extravalue) {
        this.extravalue = extravalue;
        return this;
    }

    public String getSubtype() {
        return subtype;
    }

    public StatisticsSumEntity setSubtype(String subtype) {
        this.subtype = subtype;
        return this;
    }

    public String getType() {
        return type;
    }

    public StatisticsSumEntity setType(String type) {
        this.type = type;
        return this;
    }


    // Long getSum();
}