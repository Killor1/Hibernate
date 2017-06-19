

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entitieis.*;

public class ManageArticle {

	private static SessionFactory factory;
	public static void main(String[] args) throws ParseException {
		ArrayList<Revista> revistes = new ArrayList();
                Set<Article>  articl;
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		ManageAutor autors = new ManageAutor();
		ManageRevista reviste = new ManageRevista();
		ManageArticle article = new ManageArticle();
		
		
		FileAccesor fa;
		fa = new FileAccesor();
		fa.readAutorsFile("autors.txt");
                System.out.println("Autors llegits des del fitxer");
		fa.readMagazinesFile("revistes.txt");
                System.out.println("Revistes llegits des del fitxer " +fa.llistatRevista.size());
		revistes = fa.readArticlesFile("articles.txt");
		System.out.println("Articles llegits des del fitxer");

                for (int i = 0; i < fa.llistatRevista.size(); i++) {
			System.out.println(fa.llistatRevista.get(i).toString());
			reviste.addRevista(fa.llistatRevista.get(i));
                        
                        articl = fa.llistatRevista.get(i).getArticles();
                        
                      for(Article artic : articl){
                          Autor autr = artic.getAutor();
                          autors.addAutor(autr);
                          
                          article.addArticle(artic);
                      }
		}
	}
	

	public static void addArticle(Article autor) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer AutorID = null;
		try {
			tx = session.beginTransaction();
			AutorID = (Integer) session.save(autor);
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