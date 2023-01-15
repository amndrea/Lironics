package progetto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


/**
 * Classe che ilmplementa il menu di scelta e l'interazione vera e propria con il DB
 * @author amndr
 *
 */
public class Menu {
	
	private String menu = 
			"\n 1 per registrare un nuovo cliente"
			+ "\n 2 per inserire un nuovo prodotto"
			+ "\n 3 per inserire una nuova recensione"
			+ "\n 4 per visualizzare tutti gli ordini di un cliente"
			+ "\n 5 per visualizzare tutte le recensioni di un prodotto"
			+ "\n 1997 per terminare \n \n ";
	private boolean continua = true;

	

	public Menu(Connection conn) throws SQLException, IOException, ParseException {
		
		while (isContinua()) {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println(menu);
			
			String scelta = br.readLine();
			String sql;
			PreparedStatement stmt;
			
			switch(scelta) {
			
			/********************************************************************/
			/********************************************************************/
			case "1": 
				String username, passsword ,citta,via,c,cf,email,nome,cognome;
				short civico;
				System.out.println("inserire username");
				username = br.readLine();
				System.out.println("inserire password");
				passsword = br.readLine(); 
				System.out.println("inserire citta");
				citta = br.readLine();
				System.out.println("inserire via");
				via = br.readLine();
				System.out.println("inserire civico");
				c = br.readLine();
				civico = Short.parseShort(c);
				System.out.println("inserire codice fiscale");
				cf = br.readLine();
				System.out.println("inserire email");
				email = br.readLine();
				System.out.println("inserire nome");
				nome = br.readLine();
				System.out.println("Inserire cognome");
				cognome = br.readLine();
					
				sql = "INSERT INTO  cliente  VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? ) ";
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, username);
				stmt.setString(2, passsword);
				stmt.setString(3, citta);
				stmt.setString(4, via);
				stmt.setShort(5, civico);
				stmt.setString(6, cf);
				stmt.setString(7, email);
				stmt.setString(8, nome);
				stmt.setString(9, cognome);
				
				stmt.executeUpdate();
				stmt.close();
				System.out.println("inserimento avvenuto con successo");
				break;
			/********************************************************************/
			/********************************************************************/

			
			/********************************************************************/
			/********************************************************************/
			case "2":
				String codice, q, descrizione, pnome, marca, categoria, pr;
				int quantita;
				float prezzo;
				System.out.println("inserire il codice del prodotto");
				codice = br.readLine();
				System.out.println("inserire la quantità del prodotto");
				q = br.readLine();
				quantita = Integer.parseInt(q);
				System.out.println("inserire la descrizione del prodotto");
				descrizione = br.readLine();
				System.out.println("inserire il nome del prodotto");
				pnome = br.readLine();
				System.out.println("inserire la marca del prodotto");
				marca = br.readLine();
				System.out.println("inserire la categoria del prodotto");
				categoria = br.readLine();
				System.out.println("inserire il prezzo del prodotto");
				pr = br.readLine();
				prezzo = Float.parseFloat(pr);
				
				sql = "INSERT INTO  prodotto  VALUES ( ?, ?, ?, ?, ?, ?, ? )";
				stmt = conn.prepareStatement(sql);
				
				stmt.setString (1, codice);
				stmt.setInt(2, quantita);
				stmt.setString(3, descrizione);
				stmt.setString(4, pnome);
				stmt.setString(5, marca);
				stmt.setString(6, categoria);
				stmt.setFloat(7,prezzo);
				
				stmt.executeUpdate();
				stmt.close();
				System.out.println("inserimento avvenuto con successo");
				break;
			/********************************************************************/
			/********************************************************************/
			
				
			/********************************************************************/
			/********************************************************************/
			case "3":
				String usern, cod, s, titolo, testo;
				Short stelle;
				System.out.println("inserire l'username ");
				usern = br.readLine();
				System.out.println("inserire il codice del prodotto");
				cod = br.readLine();
				System.out.println("inserire il nuemro di stelle assegnate al prodotto");
				s = br.readLine();
				stelle = Short.parseShort(s);
				System.out.println("inserire il titolo della recensione");
				titolo = br.readLine();
				System.out.println("inserire il testo della recensione");
				testo = br.readLine();
				
				sql = "INSERT INTO RECENSIONE VALUES (?, ?, ?, ?, ?) ";
				stmt = conn.prepareStatement(sql);
				
				stmt.setString(1, usern);
				stmt.setString(2, cod);
				stmt.setShort(3, stelle);
				stmt.setString(4, titolo);
				stmt.setString(5, testo);
				
				stmt.executeUpdate();
				stmt.close();
				System.out.println("inserimento avvenuto con successo");
				break;
			/********************************************************************/
			/********************************************************************/
			
				
			/********************************************************************/
			/********************************************************************/
			case "4":
				String us;
				System.out.println("inserire l'username del cliente ");
				us = br.readLine();
				
				sql = "SELECT * FROM ORDINE WHERE USERNAME = ? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, us);
				
				ResultSet rs = stmt.executeQuery();
				int k=1;
				while (rs.next()) {
					String user = rs.getString(1);
					java.sql.Date data = rs.getDate(2);
					java.sql.Time ora = rs.getTime(3);
					String dest = rs.getString(4);
					String destvia = rs.getString(5);
					Short destciv = rs.getShort(6);
					System.out.println("Ordine Numero "+k+" ------------------------------");
					System.out.println(user + " "+data+" "+ora+" "+dest+" "+destvia+" "+destciv);
					k=k+1;
					System.out.println("-----------------------------------------");
				}
				rs.close();
				stmt.close();
				break;
			/********************************************************************/
			/********************************************************************/
			
				
			/********************************************************************/
			/********************************************************************/
			case "5":
				System.out.println("inserire il codice del prodotto");
				String codi = br.readLine();
				
				sql = "SELECT * FROM RECENSIONE WHERE CODICE_PRODOTTO = ? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, codi);
				
				int j=1;
				rs = stmt.executeQuery();
				while (rs.next()) {
					String User = rs.getString(1);
					String codic = rs.getString(2);
					Short stell = rs.getShort(3);
					String titol = rs.getString(4);
					String test = rs.getString(5);
					System.out.println("Recensione numero "+j+" ------------------------");
					System.out.println(User+" "+codic+" "+ stell+" "+titol+" "+test);
					j=j+1;
					System.out.println("---------------------------------------------");
				}
				rs.close();
				stmt.close();
				break;
			/********************************************************************/	
			/********************************************************************/
			
				
			/********************************************************************/
			/********************************************************************/
			case "1997":
				System.out.println("Arrivedderci");
				conn.close();
				setContinua(false);
				break;
			/********************************************************************/
			/********************************************************************/
			default: 
				System.out.println("scelta errata");
			}
		}
	
	}
	//METODI GETTER E SETTER PER LE VARIABILI PRIVATE
	public void setContinua(boolean c) { this.continua = c;}
	public boolean isContinua() { return this.continua; }
	public String getMenu() { return menu; }
}
