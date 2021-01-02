import java.sql.*;

// 学习使用jdbc

/**
 * @author ningth
 */
public class MyDBUtil {

	private String url = "";

	private Connection conn = null;

	public MyDBUtil(String url) {
		this.url = url;
	}

	public Connection getConnection() {
		if (this.conn == null) {
			return this.connection();
		}
		return this.conn;
	}

	public void close() {
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection connection() {
		try {
			//1.加载驱动程序
			Class.forName("org.sqlite.JDBC");
			//2.获得数据库的连接
			this.conn = DriverManager.getConnection(this.url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return this.conn;
	}

	/**
	 * 创建天气预备表
	 * @return ture 成功 false 失败
	 */
	public boolean createTable() {
		if (this.getConnection() != null) {
			try {
				Statement statement = this.conn.createStatement();
				String dropSql = "drop table watcher_lives";
				String createSql =
					"create table watcher_lives\n" +
					"(\n" +
					"    id integer primary key not null,\n" +
					"    province text,\n" +
					"    city text,\n" +
					"    adcode text,\n" +
					"    windpower text,\n" +
					"    weather text,\n" +
					"    temperature text,\n" +
					"    humidity text,\n" +
					"    reporttime text,\n" +
					"    winddirection text\n" +
					")\n";

				this.dropTable();
				statement.executeUpdate(createSql);
				statement.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void dropTable() throws SQLException {
		Statement statement = null;
		try {
			statement = this.conn.createStatement();
			String dropSql = "drop table watcher_lives";
			statement.executeUpdate(dropSql);
			statement.close();
		} catch (SQLException e) {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public boolean insertLivesItem() {
		Statement statement = null;
		try {
			statement = this.conn.createStatement();
			String dropSql =
				"insert into watcher_lives(id, province, city, adcode, windpower, weather, temperature, humidity, reporttime, winddirection) " +
				"VALUES (1, 'province', 'city', 'adcode', 'windpower', 'weather', 'temperature', 'humidity','reporttime','winddirection')";
			statement.executeUpdate(dropSql);
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean selectLivesItem() {
		Statement statement = null;
		try {
			statement = this.conn.createStatement();
			String dropSql = "select * from watcher_lives";
			ResultSet resultSet = statement.executeQuery(dropSql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("province"));
			}
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	/*public static void main(String[] args) {
		System.out.println("hello");
		MyDBUtil myDbUtil = new MyDBUtil("jdbc:sqlite:identifier.sqlite");
		myDbUtil.createTable();
		myDbUtil.insertLivesItem();
		myDbUtil.selectLivesItem();
		myDbUtil.close();
	}*/
}
