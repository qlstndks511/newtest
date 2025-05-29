package jp.co.sss.crud.form;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationForm {

    private Integer empId;

    @NotBlank(message = "パスワードを入力してください。")
    @Size(min = 1, max = 16, message = "パスワードは16文字以内で入力してください。")
    @Pattern(regexp = "^$|^[0-9]+$", message = "パスワードは整数値で入力してください。")
    private String empPass;

    @NotBlank(message = "社員名を入力してください。")
    @Size(min =1, max = 30, message = "社員名は30文字以内で入力してください。")
    private String empName;

    @NotNull(message = "性別を選択してください。")
    private Integer gender;

    @NotBlank(message = "住所を入力してください。")
    @Size(min = 1,max = 60, message = "住所は60文字以内で入力してください。")
    private String address;

    @NotNull(message = "生年月日を入力してください。")
    @DateTimeFormat(pattern = "yyyy-MM-dd")  
    private Date birthday;

    @NotNull(message = "権限を選択してください。")
    private Integer authority;

    @NotNull(message = "部署を選択してください。")
    private Integer deptId;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}