
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import Entitieis.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class FileAccesor {
	
	public static ArrayList<Autor> llistaAutors = new ArrayList<Autor>();
	public static ArrayList<Revista> llistatRevista = new ArrayList<Revista>();
	
	
	public void FileAccessor(){
		
	}
	
	public void readAutorsFile(String file){
		int id;
		String name, year, country;
		boolean active;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while((s = br.readLine())!= null){
				String[] data = s.split(",");
				id = Integer.parseInt(data[0]);
				name = data[1];
				year = data[2];
				country = data[3];
				active = Boolean.parseBoolean(data[4]);
				
				llistaAutors.add(new Autor(id,name,year,country,active));
			}
			br.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readMagazinesFile(String file){
		int id;
		String titol;
		Date date;
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while((s = br.readLine())!= null){
				String[] data = s.split(",");
				id = Integer.parseInt(data[0]);
				titol = data[1];
				date = dateformat.parse(data[2]);
				llistatRevista.add(new Revista(id,titol,date));
			}
			br.close();
		} catch ( IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Revista> readArticlesFile(String file) throws ParseException{
		int idArticle, idRevista, idAutor;
		String titol;
		Date data_creacio;
		boolean publicable;
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s;
			while((s = br.readLine())!= null){
				String[] data = s.split(",");
				idArticle = Integer.parseInt(data[0]);
				idRevista = Integer.parseInt(data[1]);
				idAutor = Integer.parseInt(data[2]);
				titol = data[3];
				data_creacio = (Date) dateformat.parse(data[4]);
				publicable = Boolean.parseBoolean(data[5]);
				
				
				Autor autor = (Autor) llistaAutors.get(idAutor);
				Article art = new Article(idArticle,titol,data_creacio,publicable,autor);
				llistatRevista.get(idRevista-1).addArticle(art);;
				
			}
			br.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return llistatRevista;
		
	}
}