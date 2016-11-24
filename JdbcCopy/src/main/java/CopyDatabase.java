import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
                            final Iterable<Map<String, Object>> rows = new ResultSetIterator(rs);

                            System.out.println("Writing table " + tableName + "...");
                            int count = 0;
                            for (Map<String, Object> row : rows) {
                                final Iterable<String> keys = sortedKeys(row);

                                if (!headerPresent) {
                                    final String header = Joiner.on('\t').join(keys);
                                    writer.write(header);
                                    writer.write('\n');
                                    headerPresent = true;
                                }

                                final String rowString = Joiner.on('\t').join(orderedByKeyValues(row, keys));
                                writer.write(rowString);
                                writer.write('\n');

                                count++;
                                if (count % 500000 == 0) {
                                    System.out.println(count + " elements...");
                                }
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
            final List<String> sortedTableNames = Ordering.natural().sortedCopy(Iterables.transform(new ResultSetIterator(tablesRs),
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
