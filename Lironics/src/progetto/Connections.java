package progetto;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * CLASSE CHE REALIZZA LA CONNESSIONE AL DB  
 * @author amndr
 *
 */
public class Connections {
	public Connections (String user, String password) throws IOException, ParseException {

			try{
				Class.forName ("org.postgresql.Driver");  // Load the Driver
				Connection conn = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/Lironics", user, password );
				@SuppressWarnings("unused")
				Menu m = new Menu(conn);
		
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		catch (SQLException e) {
			//System.err.println("username o password errati");
			//Login l = new Login();
			e.printStackTrace();
		}
	}}
