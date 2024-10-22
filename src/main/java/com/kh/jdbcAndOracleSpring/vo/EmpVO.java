package com.kh.jdbcAndOracleSpring.vo;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter  // 모든 getter 만들어진거임
@Setter  // 모든 setter 만들어진거임
@AllArgsConstructor  // 모든 생성자 만들어진거임
@NoArgsConstructor  // 빈 생성자 만들어진거임
@ToString
public class EmpVO {
    private int empNO;
    private String name;
    private String job;
    private int mgr;
    private Date date;
    private BigDecimal sal;
    private BigDecimal comm;
    private int deptNO;
}
