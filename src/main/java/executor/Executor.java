package executor;

import java.sql.*;

public class Executor {
    private final Connection connection;
    private PreparedStatement preparedTransactStatement = null;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(update);
        stmt.close();
    }

    public void execUpdatePrepared(String update, Object[] params) throws SQLException {
        connection.setAutoCommit(false);
        preparedTransactStatement = connection.prepareStatement(update);
        for (int i = 0; i < params.length; i++) {
            preparedTransactStatement.setObject(i + 1, params[i]);
        }
        preparedTransactStatement.executeUpdate();
        preparedTransactStatement.close();
        connection.commit();
        connection.setAutoCommit(true);
    }

    public <T> T execQueryPrepared(String query, Object[] params,
                                   ResultHandler<T> handler)
            throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        ResultSet result = stmt.executeQuery();
        T value = handler.handle(result);
        result.close();
        stmt.close();

        return value;
    }

}
