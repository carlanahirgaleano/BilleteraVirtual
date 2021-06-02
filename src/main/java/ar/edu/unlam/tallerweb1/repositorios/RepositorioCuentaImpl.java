package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Transaccion;
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


	@Override
	public List<Cuenta> traerCuentas() {
		
		final Session session = sessionFactory.getCurrentSession();
		
		return (List<Cuenta>) session.createQuery("FROM cuenta").list();
		

	}


	@Override
	public Cuenta consultarCuentaPorUsuarioId(Long uid) {

		final Session session = sessionFactory.getCurrentSession();
		return (Cuenta) session.createCriteria(Cuenta.class)
				.add(Restrictions.eq("id_usuario", uid))
				.uniqueResult();
	}


	@Override
	public void guardarTransaccion(Transaccion transaccion) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(transaccion);
	}


	@Override
	public Cuenta consultarCuentaPorNum(Long num) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Cuenta) session.createCriteria(Cuenta.class)
				.add(Restrictions.eq("num", num))
				.uniqueResult();
	}

	
	
}
