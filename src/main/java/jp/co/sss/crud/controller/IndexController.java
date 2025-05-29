package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.LoginForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class IndexController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HttpSession session;
    //↑sessionデータ、employeeデータにアクセス

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute LoginForm loginForm) {
        session.invalidate();
        return "index"; 
    }
    
    //↑lorginする場合消す,/にアクセスする時indexに

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@Validated @ModelAttribute LoginForm loginForm, 
                       BindingResult bindingResult, 
                       HttpSession session, 
                       Model model) {
        
//エラーがある場合戻ること
    	if (bindingResult.hasErrors()) {
            return "index";
        }
        
        
            int empId = loginForm.getEmpId();
            String empPass = loginForm.getEmpPass();
            Employee employee = employeeRepository.findByEmpIdAndEmpPass(empId, empPass);

            if (employee != null) {
                EmployeeBean employeeBean = new EmployeeBean();
                employeeBean.setEmpId(employee.getEmpId());
                employeeBean.setEmpName(employee.getEmpName());
                employeeBean.setAuthority(employee.getAuthority());
                session.setAttribute("user", employeeBean);
                return "redirect:/list";
            } else {
                model.addAttribute("errMessage", "社員ID、またはパスワードが間違っています。");
                return "index";
            }
        } 
    
    //↑ログイン出来た時
    
    @PostMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer empId) {
        employeeRepository.deleteById(empId);
        return "redirect:/list"; // 
    }
    //↑削除後にリストへ

    
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}