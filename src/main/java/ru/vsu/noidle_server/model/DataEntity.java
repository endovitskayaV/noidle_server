package ru.vsu.noidle_server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "data")
@Proxy(lazy = false)
public class DataEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

   // @Column(name = "newcolumn")
  //  private String newcolumn;
}
