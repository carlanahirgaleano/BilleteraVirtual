package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioCuenta")
public class RepositorioCuentaImpl implements RepositorioCuenta{

	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioCuentaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public Cuenta consultarCuenta(Cuenta cuenta) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Cuenta) session.createCriteria(Cuenta.class)
				.add(Restrictions.eq("num", cuenta.getNum()))
				.uniqueResult();
	}
	
	@Override
	public void guardar(Cuenta cuenta) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(cuenta);
		
	}

	
	
}
