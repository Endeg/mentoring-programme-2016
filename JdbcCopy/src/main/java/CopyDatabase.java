import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            for (String tableName : getTables(conn, Props.get("SCHEMA"))) {
                System.out.println(tableName + " ----------------------");

                final String sql = "select * from " + tableName;

                try (final Statement statement = conn.createStatement()) {
                    if (statement.execute(sql)) {
                        try (final ResultSet rs = statement.getResultSet()) {
                            final List<Map<String, Object>> rows = extractResultSet(rs);
                            for (Map<String, Object> row : rows) {
                                System.out.println(row.toString());
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getTables(Connection conn, String schema) throws SQLException {
        try (final ResultSet tablesRs = conn.getMetaData().getTables(null, schema, null, new String[]{"TABLE"})) {
            final List<Map<String, Object>> tableNames = extractResultSet(tablesRs);
            final List<String> sortedTableNames = Ordering.natural().sortedCopy(Iterables.transform(tableNames,
                    new Function<Map<String, Object>, String>() {
                        @Override
                        public String apply(Map<String, Object> stringObjectMap) {
                            return stringObjectMap.get("TABLE_NAME").toString();
                        }
                    }));
            return sortedTableNames;
        }
    }
}
