import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey_Gladyshev on 23.11.2016.
 */
public class CopyDatabase {

    static final String JDBC_DRIVER = Props.get("JDBC_DRIVER");
    static final String DB_URL = Props.get("DB_URL");

    private static List<Map<String, Object>> extractResultSet(ResultSet rs) throws SQLException {
        final List<Map<String, Object>> rows = Lists.newArrayList();
        while (rs.next()) {
            final Map<String, Object> row = Maps.newHashMap();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                final String columnName = rs.getMetaData().getColumnName(i);
                final Object value = rs.getObject(i);
                row.put(columnName, value);
            }
            rows.add(row);
        }
        return rows;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(DB_URL, Props.get("USER"), Props.get("PASS"))) {
            getTableTypes(conn);

            getSchemas(conn);

            System.out.println("getCatalogs(conn) = " + getCatalogs(conn));

            try (final ResultSet tablesRs = conn.getMetaData().getTables(null, Props.get("SCHEMA"), null, new String[]{"TABLE"})) {
                final List<Map<String, Object>> tableNames = extractResultSet(tablesRs);
                System.out.println("tableNames = " + tableNames);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, Object>> getCatalogs(Connection conn) throws SQLException {
        try (final ResultSet catalogsRs = conn.getMetaData().getCatalogs()) {
            return extractResultSet(catalogsRs);
        }
    }

    private static List<Map<String, Object>> getSchemas(Connection conn) throws SQLException {
        try (final ResultSet schemasRs = conn.getMetaData().getSchemas()) {
            return extractResultSet(schemasRs);
        }
    }

    private static List<Map<String, Object>> getTableTypes(Connection conn) throws SQLException {
        try (final ResultSet tableTypesRs = conn.getMetaData().getTableTypes()) {
            return extractResultSet(tableTypesRs);
        }
    }
}
