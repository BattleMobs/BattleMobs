package bernhard.scharrer.battlemobs.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.bukkit.Bukkit;

import bernhard.scharrer.battlemobs.properties.PluginProperties;
import bernhard.scharrer.battlemobs.properties.Props;

public class Database {
	
	private static String username;
	private static String password;
	private static String database;
	private static String host;
	private static int port;
	private static Connection con;
	
	public static void loadAuthFile() {
		
		/*
		 * load data from auth file
		 */
		username = PluginProperties.getString(Props.SQL_NAME);
		password = PluginProperties.getString(Props.SQL_PASSWORD);
		database = PluginProperties.getString(Props.SQL_DATABASE);
		host = PluginProperties.getString(Props.SQL_HOST);
		port = PluginProperties.getInt(Props.SQL_PORT);
	}
	
	/**
	 * connects with database
	 */
	public static void connect() {
		/**
		 * load in class from API
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			Bukkit.getConsoleSender().sendMessage("Failed to load database driver!");
			System.exit(-1);
		}
		
		Bukkit.getConsoleSender().sendMessage("Loaded database driver.");
		
		/**
		 * build connection
		 */
		String jdbc = "jdbc:mysql://" + host + ":" + port + "/" + database;
		try {
			Properties props = new Properties();
			props.put("user", username);
			if (!PluginProperties.getBoolean(Props.SQL_NOPASSWORD)) {
				props.put("password", password);
			}
			con = DriverManager.getConnection(jdbc, props);
		} catch (SQLException e) {
			Bukkit.getConsoleSender().sendMessage("Failed to connect to database! (JDBC: "+jdbc+")");
			e.printStackTrace();
			System.exit(-1);
		}
		if (isConnected()) Bukkit.getConsoleSender().sendMessage("Succesfully connected with Database!");
		
	}
	
	/**
	 * create default tables
	 */
	public static void createDefaultTables() {
		if (isConnected()) {
			createTable("`PLAYERS`",
					"`UUID` VARCHAR(100)",
					"`NAME` VARCHAR(50)",
					"`EXP` INT",
					"`MONEY` INT",
					"`RANK` VARCHAR(20)",
					"`KILLS` INT",
					"`DEATHS` INT",
					"PRIMARY KEY (`UUID`)");
			createTable("`MOBS`",
					"`UUID` VARCHAR(100)",
					"`MOB` VARCHAR(50)",
					"`EXP` INT",
					"`TIER` INT",
					"`KILLS` INT",
					"`DEATHS` INT");
		}
	}
	
	private static void createTable(String name, String... params) {
		String parameter = "";
		for (String param : params) {
			if (!parameter.isEmpty()) parameter = parameter + ", ";
			parameter = parameter + param;
		}
		execute("CREATE TABLE IF NOT EXISTS "+name + "(" + parameter + ")");
	}

	private static boolean isConnected() {
		return con!=null;
	}
	
	/**
	 * 
	 * pushes sql commands to server
	 * 
	 */
	public static void execute(String command) {
		if (isConnected()) try {
			//Bukkit.getConsoleSender().sendMessage("§8[§bSQL§8]§7 " + command);
			con.createStatement().executeUpdate(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * pushes sql query commands to server with result
	 * 
	 */
	public static ResultSet query(String command) {
		if (isConnected()) try {
			//Bukkit.getConsoleSender().sendMessage("§8[§bSQL§8]§7 " + command);
			return con.createStatement().executeQuery(command);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @param table
	 * @param column
	 * @param condition
	 * @return string
	 */
	public static String getString(String table, String column, String condition) {
		try {
			ResultSet result = query("SELECT `"+column+"` FROM `"+table+"` WHERE " + condition);
			if (result.next()) return result.getString(column);
			else return "";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * @param table
	 * @param column
	 * @param condition
	 * @return int
	 */
	public static int getInt(String table, String column, String condition) {
		try {
			ResultSet result = query("SELECT `"+column+"` FROM `"+table+"` WHERE " + condition);
			if (result.next()) return result.getInt(column);
			else return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * @param table
	 * @param column
	 * @param condition
	 * @return boolean
	 */
	public static boolean getBoolean(String table, String column, String condition) {
		try {
			ResultSet result = query("SELECT `"+column+"` FROM `"+table+"` WHERE " + condition);
			if (result.next()) return result.getBoolean(column);
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * closes connection
	 * 
	 */
	public static void disconnect() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("Failed to close connection to database!");
		}
		Bukkit.getConsoleSender().sendMessage("Closed Connection to database!");
	}
	
}