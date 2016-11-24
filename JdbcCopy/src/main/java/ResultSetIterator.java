import com.google.common.collect.Maps;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrey_Gladyshev on 24.11.2016.
 */
public class ResultSetIterator implements Iterable<Map<String, Object>> {

    private final ResultSet rs;

    public ResultSetIterator(ResultSet rs) {
        this.rs = rs;
    }

    @Override
    public Iterator<Map<String, Object>> iterator() {
        return new Iterator<Map<String, Object>>() {
            @Override
            public boolean hasNext() {
                try {
                    return rs.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public Map<String, Object> next() {
                final Map<String, Object> row = Maps.newHashMap();
                try {
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        final String columnName = rs.getMetaData().getColumnName(i);
                        final Object value = rs.getObject(i);
                        row.put(columnName, value);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return row;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove from here!");
            }
        };
    }
}
