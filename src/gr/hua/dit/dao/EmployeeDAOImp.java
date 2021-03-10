package gr.hua.dit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.transaction.Transactional;
import gr.hua.dit.entity.Applypoints;
import gr.hua.dit.entity.Changemail;
import gr.hua.dit.entity.Results;
import gr.hua.dit.entity.Rights;
import gr.hua.dit.entity.Student;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public String employeeCreateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		session.save(student);
		message = employeeCreateRights(student.getSname(), student.getSemail(), student.getYear()); 
		return message;
	}
	
	@Override
	@Transactional
	public String employeeCreateRights(String fullname, String email, int year) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		if(year >=4) {
			Rights rights = new Rights(fullname, email, "student", 0, 0, 0, 0, 0);
			session.save(rights);
			message = "success";
		}else if(year <4 && year>=1) {
			Rights rights = new Rights(fullname, email, "student", 0, 0, 0, 0, 1);
			session.save(rights);
			message = "success";
		}else {
			message = "failure";
		}
		return message;
	}
	
	@Override
	@Transactional
	public String employeeDeleteStudent(String email, int id) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		Student student = session.get(Student.class, id);
		session.delete(student);
		message = employeeDeleteRights(email);
		Query<Applypoints> query1 = session.createQuery("from Applypoints where email='" + email+"'");
		Applypoints app = new Applypoints();
		app = query1.uniqueResult();
		if(app!=null) {
			session.delete(app);
		}
		Query<Results> query2 = session.createQuery("from Results where stdemail='" + email+"'");
		Results res = new Results();
		res = query2.uniqueResult();
		if(res!=null) {
			session.delete(res);
		}
		return message;
	}

	@Override
	@Transactional
	public String employeeDeleteRights(String email) {
		String message;
		Session session = sessionFactory.getCurrentSession();
		Rights rights = new Rights();
		Query<Rights> query = session.createQuery("from Rights where email = '"+email+"'");
		rights = query.uniqueResult();
		if (rights == null ) {
			message = "fail";
		}else {
			session.delete(rights);
			message = "success";
		}
		return message;
	}
	
	@Override
	@Transactional
	public String employeeModifyStudent(String name, String oldemail, String newemail) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		session.createQuery("update Student set semail='" + newemail + "' where sname='" + name + "'and semail = '" + oldemail + "'").executeUpdate();
		session.createQuery("update Rights set email='" + newemail+ "' where email='" + oldemail + "'").executeUpdate();
		Query<Applypoints> query1 = session.createQuery("from Applypoints where email='" + oldemail+"'");
		Applypoints app = new Applypoints();
		app = query1.uniqueResult();
		if(app!=null) {
			session.createQuery("update Applypoints set email='" + newemail+ "' where email='" + oldemail + "'").executeUpdate();
		}
		Query<Results> query2 = session.createQuery("from Results where stdemail='" + oldemail+"'");
		Results res = new Results();
		res = query2.uniqueResult();
		if(res!=null) {
			session.createQuery("update Results set stdemail='" + newemail+ "' where stdemail='" + oldemail + "'").executeUpdate();
		}
		Query<Changemail> query3 = session.createQuery("from Changemail where stdname='" + name + "' and stdoldmail='" + oldemail + "' and stdnewmail='" + newemail + "'");
		Changemail ch = new Changemail();
		ch = query3.uniqueResult();
		if(ch!=null) {
			session.delete(ch);
		}
		message = "success";
		return message;
	}

	@Override
	public int employGetGrade(int personalIncome, int familyIncome, int workParents, int siblings,
			String sameCity, int freeHousingYears, int years) {
		int points = 0;
		if(personalIncome == 0 && workParents == 0) {
			points = 10000;
		}else {
			if(familyIncome < 10000){
				points += 100;
			}else if(familyIncome >= 10000 && familyIncome <= 15000) {
				points += 30;
			}else if(familyIncome > 15000) {
				points += 0;
			}
			if(siblings == 0) {
				points += 0;
			}else {
				points += siblings*20;
			}
			if(sameCity ==  "no") {
				points += 50;
			}else {
				points += 0;
			}
			if(freeHousingYears == 0) {
				points -= 0;
			}else {
				points -= freeHousingYears*10;
			}
		}
		if(years > 4) {
			points = -2000;
		}
		return points;
	}

	@Override
	@Transactional
	public int authorized(String email) {
		int result;
		Session session = sessionFactory.getCurrentSession();
		Query<Rights> r = session.createQuery("from Rights r where email = '" + email + "'");
		Rights rights = new Rights();
		rights = r.uniqueResult();
		if(rights == null) {
			result = 0;
		} else {
			result = rights.getCrtdelmodusers();
		}
		return result;
	}

	@Override
	@Transactional
	public String activateStudent(String email) {
		Session session = sessionFactory.getCurrentSession();
		int year;
		String message;
		Query<Student> s = session.createQuery("from Student s where semail = '" + email + "'");
		Student student = new Student();
		student = s.uniqueResult();
		year = student.getYear();
		if(year >= 4) {
			session.createQuery("update Rights set makeapp='0' where email='" + email + "'").executeUpdate();
		} else {
			session.createQuery("update Rights set makeapp='1' where email='" + email + "'").executeUpdate();
		}
		message = "success";
		return message;
	}

	@Override
	@Transactional
	public int authorizedrole(String email) {
		int result;
		Session session = sessionFactory.getCurrentSession();
		Query<Rights> r = session.createQuery("from Rights r where email = '" + email + "'");
		Rights rights = new Rights();
		rights = r.uniqueResult();
		if(rights == null) {
			result = 0;
		} else {
			result = rights.getModrole();
		}
		return result;
	}

	@Override
	@Transactional
	public String changerole(String email) {
		Session session = sessionFactory.getCurrentSession();
		String message;
		Rights rights = new Rights();
		Query<Rights> query = session.createQuery("from Rights r where r.email='" + email + "'");
		rights = query.uniqueResult();
		if(rights == null) {
			message = "failure";
		}else if(rights.getModrole() == 1) {
			session.createQuery("update Rights set modrole='0' where email='" + email + "'").executeUpdate();
			message = "success";
		}else {
			session.createQuery("update Rights set modrole='1' where email='" + email + "'").executeUpdate();
			message = "success";
		}
		return message;
	}

	@Override
	@Transactional
	public String applyPoints(String fullname, String stdemail, int points) {
		String message;
		Session session = sessionFactory.getCurrentSession();
		Student student = new Student();
		Query<Student> query = session.createQuery("from Student s where s.sname = '" + fullname + "' and s.semail = '" + stdemail + "'");
		student = query.uniqueResult();
		if(student==null) {
			message = "failure";
		} else {
			session.createQuery("update Student set grade = '" + points + "' where semail = '" + stdemail + "'").executeUpdate();
			message = "success";
		}
		Query<Applypoints> query3 = session.createQuery("from Applypoints where name='" + fullname + "' and email='" + stdemail + "' and points='" + points + "'");
		Applypoints ch = new Applypoints();
		ch = query3.uniqueResult();
		if(ch!=null) {
			session.delete(ch);
		}
		return message;
	}

	@Override
	@Transactional
	public List<Applypoints> pointsNotifications() {
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Applypoints> query = currentSession.createQuery("from Applypoints", Applypoints.class);
        List<Applypoints> list = query.getResultList();
		return list;
	}

	@Override
	@Transactional
	public List<Changemail> emailNotifications() {
		Session currentSession = sessionFactory.getCurrentSession();
        Query<Changemail> query = currentSession.createQuery("from Changemail", Changemail.class);
        List<Changemail> list = query.getResultList();
		return list;
	}
	
}
