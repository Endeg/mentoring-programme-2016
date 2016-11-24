import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
        final File file = new File("output.txt");
        try (final Connection conn = DriverManager.getConnection(DB_URL, Props.get("USER"), Props.get("PASS"));
             final OutputStream fos = new FileOutputStream(file);
             final Writer writer = new OutputStreamWriter(fos)) {
            for (String tableName : getTables(conn, Props.get("SCHEMA"))) {
                writer.write(tableName + ":\n");

                final String sql = "select * from " + tableName;

                try (final Statement statement = conn.createStatement()) {
                    if (statement.execute(sql)) {
                        boolean headerPresent = false;
                        try (final ResultSet rs = statement.getResultSet()) {
                            final List<Map<String, Object>> rows = extractResultSet(rs);

                            System.out.println("Writing table " + tableName + " (" + rows.size() + " elements)...");

                            for (Map<String, Object> row : rows) {
                                final Iterable<String> keys = sortedKeys(row);

                                if (!headerPresent) {
                                    writer.write(Joiner.on('\t').join(keys));
                                    writer.write('\n');
                                    headerPresent = true;
                                }

                                writer.write(Joiner.on('\t').join(orderedByKeyValues(row, keys)));
                                writer.write('\n');
                            }
                        }
                    }
                }

                writer.flush();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static Iterable<String> sortedKeys(Map<String, ?> row) {
        return Ordering.natural().sortedCopy(row.keySet());
    }

    private static Iterable<String> orderedByKeyValues(Map<String, Object> row, Iterable<String> keys) {
        final List<String> orderedValues = Lists.newArrayList();
        for (String key : keys) {
            orderedValues.add("\"" + row.get(key) + "\"");
        }
        return orderedValues;
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
