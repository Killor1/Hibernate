import java.io.IOException;
import java.text.ParseException;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entitieis.Revista;

public class Main {
	private static SessionFactory factory;	

	  public static void main(String[] args) throws IOException {
	    try {
	      factory = new Configuration().configure().buildSessionFactory();
	    } catch (Throwable ex) {
	      System.err.println("Failed to create sessionFactory object." + ex);
	      throw new ExceptionInInitializerError(ex);
	    }
	    
	    ManageRevista MR = new ManageRevista();
	    FileAccesor fa = new FileAccesor();
	    
	    fa.readAutorsFile("autors.txt");
	    fa.readMagazinesFile("revistes.txt");
	    try {
			fa.readArticlesFile("articles.txt");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    for(Revista r: fa.llistatRevista){
	      MR.addRevista(r);
	    }
	  }

	  public static SessionFactory getFactory() {
	    return factory;
	  }

	  public static void setFactory(SessionFactory factory1) {
	    factory = factory1;
	  }

	}