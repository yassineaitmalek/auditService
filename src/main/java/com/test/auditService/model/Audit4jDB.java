package com.test.auditService.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Entity
@Immutable
@Builder
@Table(name = "Audit")
@NoArgsConstructor
@AllArgsConstructor
public class Audit4jDB {

  @Id
  @Column(name = "identifier", length = 200, nullable = false)
  private String id;

  @Column(columnDefinition = "LONGTEXT", nullable = false)
  private String elements;

  @Column(length = 200, nullable = false)
  private String origin;

  @Column(length = 200, nullable = false)
  private String actor;

  @Column(length = 200, nullable = false)
  private String action;

  @Column(columnDefinition = "TIMESTAMP", nullable = false)
  private Date timestamp;

  public static final List<String> header() {
    return Arrays.asList("actor", "origin", "action", "elements", "time");
  }

}
