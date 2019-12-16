import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {

    private static final int BATCH_SIZE = 40_000;

    private static Connection connection;
    private static PreparedStatement preparedStatement = null;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbName = "skillbox";
            String dbUser = "root";
            String dbPass = "alexvasin123";
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + dbName +
                            "?user=" + dbUser + "&password=" + dbPass +
                            "&useSSL=false" +
                            "&requireSSL=false" +
                            "&useLegacyDatetimeCode=false" +
                            "&amp" +
                            "&serverTimezone=UTC" +
                            "&allowPublicKeyRetrieval=true");
            connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
            connection.createStatement().execute("CREATE TABLE voter_count(" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name TINYTEXT NOT NULL, " +
                    "birthDate DATE NOT NULL, " +
                    "count INT NOT NULL, " +
                    "PRIMARY KEY(id));");
            String insertSQL = "INSERT INTO voter_count (name, birthDate,count) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(insertSQL);
        }
        return connection;
    }

    public static void countVoter(String name, String birthDay, int counter) throws SQLException {
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, birthDay);
        preparedStatement.setInt(3, 1);
        preparedStatement.addBatch();
        if (counter % BATCH_SIZE == 0) {
            executeBatch();
        }
    }

    public static void executeBatch() throws SQLException {
        preparedStatement.executeBatch();
        connection.commit();
    }

    public static void printVoterCounts() throws SQLException {
        long start = System.currentTimeMillis();
        int counter = 0;
        String sql = "select name,birthDate,vote_num from (select name,count,birthDate," +
                "count(count) as vote_num from voter_count group by name,birthDate order " +
                "by vote_num Desc) as result where vote_num > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append("\t")
                    .append((counter++))
                    .append(" - ")
                    .append(rs.getString("name"))
                    .append(" ")
                    .append(rs.getString("birthDate"))
                    .append(" ")
                    .append(rs.getInt("vote_num"))
                    .append("\n");
        }
        System.out.println((System.currentTimeMillis() - start)/1000 + "- search duplicates");
        rs.close();
    }

    public static void customSelect(String name) throws SQLException {
        long start = System.currentTimeMillis();
        String sql = "SELECT name, birthDate FROM voter_count WHERE name ='" + name + "'";

        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        StringBuilder result = new StringBuilder();
        while (rs.next()) {
            result.append("\t")
                    .append(rs.getString("name"))
                    .append(" ")
                    .append(rs.getString("birthDate"))
                    .append("\n");
        }
        System.out.println(result.toString());
        System.out.println((System.currentTimeMillis() - start)/1000 + "- simple select");
        rs.close();
    }

    static void connectionClose() throws SQLException {
        connection.close();
    }

    static void createIndex() throws SQLException {
        String sql = "CREATE INDEX name ON voter (name);";
        preparedStatement = connection.prepareStatement(sql);
    }
}
