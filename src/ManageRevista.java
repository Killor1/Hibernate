
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.IOException;

import Entitieis.Revista;


public class ManageRevista {
	
	private static SessionFactory factory;

	public static void main(String[] args) throws IOException, ParseException {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageRevista MR = new ManageRevista();
		FileAccesor fa;
		fa = new FileAccesor();
		fa.readMagazinesFile("file/revistes.txt");
		System.out.println("Revistes llegits des del fitxer");
		for (int i = 0; i < fa.llistatRevista.size(); i++) {
			System.out.println(fa.llistatRevista.get(i).toString());
			MR.addRevista(fa.llistatRevista.get(i));
		}
		System.out.println("Revista llegits de la base de dades");
		MR.listRevista();
		MR.deleteRevista(1);
		MR.updateRevista(2, false);
		System.out.println("Revista llegits de la base de dades després de des actualitzacions");
		MR.listRevista();

	}
	
	public void addRevista(Revista revista) {
                factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer RevistaID = null;
		try {
			tx = session.beginTransaction();
			RevistaID = (Integer) session.save(revista);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	
	public void listRevista() {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List revistas = session.createQuery("FROM Revista").list();

			for (Iterator iterator = revistas.iterator(); iterator.hasNext();) {
				Revista revista = (Revista) iterator.next();
				System.out.println(revista.toString());
			}

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE activity for an autor */
	public void updateRevista(Integer revistaID, boolean actiu) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Revista revista = (Revista) session.get(Revista.class, revistaID);
			session.update(revista);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteRevista(Integer RevistaID) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Revista revista = (Revista) session.get(Revista.class, RevistaID);
			session.delete(revista);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}

