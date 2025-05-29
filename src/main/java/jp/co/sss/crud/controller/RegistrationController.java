package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import jp.co.sss.crud.entity.Department;
import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.RegistrationForm;
import jp.co.sss.crud.repository.DepartmentRepository;
import jp.co.sss.crud.repository.EmployeeRepository;

@Controller
public class RegistrationController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    // inputフォーム
    @GetMapping("/regist/input")
    public String showInputForm(Model model) {
        RegistrationForm form = new RegistrationForm();
        model.addAttribute("registrationForm", form);
        model.addAttribute("departments", departmentRepository.findAll());
        return "regist/register";
    }

    // checkページへ　未選択見えるように、dept探す、ない場合未選択、true,false結果
    @PostMapping("/regist/check")
    public String processInput(@Valid @ModelAttribute RegistrationForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentRepository.findAll());
            return "regist/register";
        }
        model.addAttribute("registrationForm", form);

        Department department = departmentRepository.findById(form.getDeptId()).orElse(null);
        model.addAttribute("departmentName", (department != null) ? department.getDeptName() : "未選択");
        
        return "regist/regist_check";
    }

    // Completeへ
    @PostMapping("/regist/complete")
    public String completeRegistration(@ModelAttribute RegistrationForm form, RedirectAttributes redirectAttributes) {
        Department department = departmentRepository.findById(form.getDeptId()).orElse(null);

        Employee employee = new Employee();
        employee.setEmpPass(form.getEmpPass());
        employee.setEmpName(form.getEmpName());
        employee.setGender(form.getGender());
        employee.setAddress(form.getAddress());
        employee.setBirthday(form.getBirthday());
        employee.setAuthority(form.getAuthority());
        employee.setDepartment(department);

        employeeRepository.save(employee);

        return "redirect:/regist/complete";
    }

    // completeページ
    @GetMapping("/regist/complete")
    public String showCompletePage() {
        return "regist/regist_complete";
    }

    // 戻る
    @PostMapping("/regist/back")
    public String backToInput(@ModelAttribute RegistrationForm form, Model model) {
        model.addAttribute("registrationForm", form);
        model.addAttribute("departments", departmentRepository.findAll());
        return "regist/register";
    }
    
    
}
