package jp.co.sss.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession; // Import HttpSession
import jp.co.sss.crud.bean.EmployeeBean; // Import EmployeeBean
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class ListController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HttpSession session;
    // httpセッション

    @GetMapping("/list")
    public String showEmployeeList(Model model) {
        // セッションからユーザー、権限
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("authority", user.getAuthority());
        }

        List<Employee> employeeList = employeeRepository.findAllByOrderByEmpIdAsc();
        model.addAttribute("employeeList", employeeList);
        return "list/list";
    }
    
    //↑リストを整理して見せる

    @GetMapping("/list/empName")
    public String searchByEmployeeName(@RequestParam("empName") String empName, Model model) {
        // セッションからユーザー、権限
        EmployeeBean user = (EmployeeBean) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("authority", user.getAuthority());
        }
        
        List<Employee> employeeList;

        if (empName != null && !empName.isEmpty()) {
            employeeList = employeeRepository.findByEmpNameContainingOrderByEmpIdAsc(empName);
            model.addAttribute("searchTerm", empName);
        } else {
            employeeList = employeeRepository.findAllByOrderByEmpIdAsc();
        }

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("isSearchResult", true);

        return "list/list";
    }
}


		//↑empNameの検索　model.addAtributeはmodelに結果追加（httpで利用）