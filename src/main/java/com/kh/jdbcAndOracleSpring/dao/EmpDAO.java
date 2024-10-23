package com.kh.jdbcAndOracleSpring.dao;

import com.kh.jdbcAndOracleSpring.vo.EmpVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository  // stream bin에다가 주입하는 방식??
public class EmpDAO {
    private final JdbcTemplate jdbcTemplate;

    public EmpDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<EmpVO> empSelect() {
        String sql = "SELECT * FROM EMP";
        return jdbcTemplate.query(sql, new EmpRowMapper());
    }

    public boolean empInsert(EmpVO vo) {
        int result = 0;
        String sql = "INSERT INTO EMP (EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            result = jdbcTemplate.update(sql, vo.getEmpNO(), vo.getName(), vo.getJob(),
                    vo.getMgr(), vo.getDate(), vo.getSal(), vo.getComm(), vo.getDeptNO());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    public boolean empDelete(String name) {
        int result = 0;
        String query = "DELETE FROM EMP WHERE ENAME = ?";
        try {
            result = jdbcTemplate.update(query, name);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    public boolean empUpdate(EmpVO emp) {
        int result = 0;
        try {
            String query = "UPDATE EMP SET JOB = ?, SAL = ?, COMM = ? WHERE ENAME = ?";
            jdbcTemplate.update(query, emp.getJob(), emp.getSal(), emp.getComm(), emp.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result > 0;
    }

    private static class EmpRowMapper implements RowMapper<EmpVO> {
        @Override
        public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new EmpVO(
                    rs.getInt("EMPNO"),
                    rs.getString("ENAME"),
                    rs.getString("JOB"),
                    rs.getInt("MGR"),
                    rs.getDate("HIREDATE"),
                    rs.getBigDecimal("SAL"),
                    rs.getBigDecimal("COMM"),
                    rs.getInt("DEPTNO")
            );
        }
    }

    public void empSelectResult(List<EmpVO> list) {
        System.out.println("----------------------------------------------");
        System.out.println("             사원 정보");
        System.out.println("----------------------------------------------");
        for(EmpVO e : list) {
            System.out.print(e.getEmpNO() + " ");
            System.out.print(e.getName() + " ");
            System.out.print(e.getJob() + " ");
            System.out.print(e.getMgr() + " ");
            System.out.print(e.getDate() + " ");
            System.out.print(e.getSal() + " ");
            System.out.print(e.getComm() + " ");
            System.out.print(e.getDeptNO());
            System.out.println();
        }
        System.out.println("----------------------------------------------");
    }

}
