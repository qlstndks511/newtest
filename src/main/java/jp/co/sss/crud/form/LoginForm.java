package jp.co.sss.crud.form;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginForm {
	
	/** 社員ID */
	@NotNull(message = "社員IDを入力してください")
	@Max(value = 99999, message = "社員IDは99999までの整数値で入力してください。")
	private Integer empId;


	/** パスワード */
	@NotBlank(message = "パスワードを入力してください。")
	private String empPass;

	/**
	 * 社員IDの取得
	 *
	 * @return 社員ID
	 */
	public Integer getEmpId() {
		return empId;
	}

	/**
	 * 社員IDのセット
	 *
	 * @param empId 社員ID
	 */
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	/**
	 * パスワードの取得
	 *
	 * @return パスワード
	 */
	public String getEmpPass() {
		return empPass;
	}

	/**
	 * パスワードのセット
	 *
	 * @param empPass パスワード
	 */
	public void setEmpPass(String empPass) {
		this.empPass = empPass;
	}
}