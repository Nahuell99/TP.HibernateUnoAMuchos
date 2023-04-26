package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import datos.Cliente;
import datos.Prestamo;

public class ClienteDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public int agregar(Cliente objeto) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(Cliente objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public void eliminar(Cliente objeto) throws HibernateException {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}

	public Cliente traer(long idCliente) throws HibernateException {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.get(Cliente.class, idCliente);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Cliente traer(int dni) throws HibernateException {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.createQuery("from Cliente c where c.dni=" + dni).uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> traer() throws HibernateException {
		List<Cliente> lista = null;
		try {
			iniciaOperacion();
			lista = (List<Cliente>) session.createQuery("from Cliente c order by c.apellido asc, c.nombre asc").list();
		} finally {
			session.close();
		}
		return lista;
	}

	public Cliente traerClienteYPrestamos(long idCliente) throws HibernateException {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			String hql = "from Cliente c where c.idCliente =" + idCliente;
			objeto = (Cliente) session.createQuery(hql).uniqueResult();
			Hibernate.initialize(objeto.getPrestamos());
		} finally {
			session.close();
		}
		return objeto;
	}

	// 1. Traet todos los prestamos de un cliente (Sin usar el set de Prestamos en
	// Cliente)
	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamos(Cliente cliente) {
		List<Prestamo> lista = null;
		System.out.println(cliente.getIdCliente());
		try {
			iniciaOperacion();
			lista = session.createQuery("from Prestamo p join fetch p.cliente c where c.idCliente=" + cliente.getIdCliente()).list();
			
		} finally {
			session.close();
		}
		return lista;
	}

	// 2. Traer todos los prestamos de un cliente que esten impagos
	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamosPorEstado(Cliente cliente, boolean prestamoFinalizado) {
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p join fetch p.cliente c where c.idCliente=" + cliente.getIdCliente()
					+ "AND p.cancelado =" + prestamoFinalizado;
			lista = session.createQuery(hql).list();
		} finally {
			session.close();
		}
		return lista;
	}

	// 3. Traer todos los prestamos de una fecha
	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamosFecha(LocalDate fecha) {
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p where p.fecha='" + fecha + "'";
			lista = session.createQuery(hql).list();
		} finally {
			session.close();
		}
		return lista;
	}

	// 4. Traer todos los prestamos de un rango
	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamosEntreFecha(LocalDate desde, LocalDate hasta) {
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p where p.fecha > '" + desde + "' and p.fecha < '" + hasta + "'";
			lista = session.createQuery(hql).list();
		} finally {
			session.close();
		}
		return lista;
	}

	// 5. Traer todos los prestamos que tengan una cuota que vence en una Fecha
	@SuppressWarnings("unchecked")
	public List<Prestamo> traerPrestamosCuotaAVencer(LocalDate fechavencimientoCuuota) {
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p join fetch p.cuota c where c.fechaVencimiento = '" + fechavencimientoCuuota + "'";
			lista = session.createQuery(hql).list();
		} finally {
			session.close();
		}
		return lista;
	}

}
