import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <K, T extends Entity> {
    public abstract List<T> findAll();
    public abstract T findEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);
    protected Connection connector;
    // методы добавления, поиска, замены, удаления
// методы закрытия коннекта и Statement
    public void close() throws SQLException {
        connector.close();
    }
    protected void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }
}


