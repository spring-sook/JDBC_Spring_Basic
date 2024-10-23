package com.kh.jdbcAndOracleSpring.controller;

import com.kh.jdbcAndOracleSpring.dao.EmpDAO;
import com.kh.jdbcAndOracleSpring.vo.EmpVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/emp")  // http://localhost:8112/emp
public class EmpController {
    private final EmpDAO empDAO;

    public EmpController(EmpDAO empDAO) {
        this.empDAO = empDAO;
    }

    @GetMapping("/select")  // http://localhost:8112/select
    public String selectViewEmp(Model model) {
        List<EmpVO> emps = empDAO.empSelect();
        model.addAttribute("employees", emps);
        return "thymeleaf/empSelect";
    }

    @GetMapping("/insert")
    public String insertViewEmp(Model model) {
        model.addAttribute("employees", new EmpVO());  // input 화면에서 정보를 받기 위해서 빈 객체 넘겨줌
        return "thymeleaf/empInsert";
    }

    @PostMapping("/insert")
    public String insertDBEmp(@ModelAttribute("employees") EmpVO empVO, Model model) { //EmpVO에 employees를 넘겨줌
        boolean isSucess = empDAO.empInsert(empVO);
        model.addAttribute("isSuccess", isSucess);
        return "thymeleaf/empResult";
    }

}
