package jp.co.sss.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sss.crud.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllByOrderByEmpIdAsc();
	
	List<Employee> findByEmpNameContainingOrderByEmpIdAsc(String empName);
	
	Employee findByEmpIdAndEmpPass(Integer empId, String empPass);//ログイン情報
	

	}

