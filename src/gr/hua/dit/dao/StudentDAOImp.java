package gr.hua.dit.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Employee;
import gr.hua.dit.entity.Results;
import gr.hua.dit.entity.Student;

@Repository
public class StudentDAOImp implements StudentDAO {
	
	 @Autowired
     private SessionFactory sessionFactory;

	@Override
	@Transactional
	public String getLogin(String email, String password) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		Student student = new Student();
		Query<Student> query = session.createQuery("from Student s where s.semail='" + email + "' and s.spass='" + password + "'");
		student = query.uniqueResult();
		if(student == null) {
			Employee employee = new Employee();
			Query<Employee> employeequery = session.createQuery("from Employee e where e.eemail='" + email + "' and e.epass='" + password + "'");
			employee = employeequery.uniqueResult();
			if(employee == null) {
				message = "failure";
			} else {
				message = "admin";
			}
		}else {
			message = "student";
		}
		return message;
	}
	
	@Override
	@Transactional
	public String changeEmail(Changemail changemail) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		session.save(changemail);
		message = "success";
		return message;
	}

	@Override
	@Transactional
	public String application(Applypoints application) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		session.save(application);
		message = "success";
		return message;
	}
	
	@Override
	@Transactional
	public int getStudentByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		Student student = new Student();
		Query<Student> query = session.createQuery("from Student s where s.semail='" + email + "'");
		student = query.uniqueResult();
		return student.getSid();
	}

	@Override
	@Transactional
	public int getRank(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query<Student> query = session.createQuery("from Student order by grade desc");
		List<Student> std =query.getResultList();
		Results res = new Results();
		Query<Results> query1 = session.createQuery("from Results where stdemail = '" + email + "'");
		res = query1.uniqueResult();
		if(res==null) {
			for(int i = 0; i<std.size(); i++) {
				Results r = new Results(i+1,std.get(i).getSname(),std.get(i).getSemail(),std.get(i).getGrade());
				session.save(r);
			}
		}
		return res.getRid();
	}

}
