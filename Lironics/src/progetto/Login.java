package progetto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

/**
 * CLASSE CHE IMPLEMENTA IL LOGIN DI UN UTENTE
 * VIENE CHIESTO DI INSERIRE USERNAME E PASSWORD
 * @author amndr
 *
 */
public class Login {
	
	private String username, password;
	private boolean continua = true;
	
	
	public  Login() throws IOException, ParseException {
		while ( isContinua()) {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String u="", p="";
			try {
				System.out.println("inserire username per connessione al DB");
				u = br.readLine();
				System.out.println("inserire password per connessione al DB ");
				p = br.readLine();
			}
			catch (Exception e ) {
				e.printStackTrace();
				setContinua(true);
				
			}
			setContinua(false);
			setUsername (u);
			setPassword(p);	
			/*
			 * TENTO LA CONNESSIONE AL DB CON LE CREDENZIALI INSERITE
			 */
			@SuppressWarnings("unused")
			Connections c = new Connections (this.getUsername(), this.getPassword());
		}
	}


	public String getUsername() { return username; }
	public String getPassword() { return password; }
	public boolean isContinua() { return continua; }
	
	public void setUsername(String username) { this.username = username; }
	public void setPassword(String password) { this.password = password; }
	public void setContinua(boolean continua) { this.continua = continua; }


}
