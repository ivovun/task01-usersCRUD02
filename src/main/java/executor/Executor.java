package executor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdatePrepared(String update, Object[] params) throws SQLException {
        // https://stackoverflow.com/questions/15761791/transaction-rollback-on-sqlexception-using-new-try-with-resources-block
        boolean initialAutocommit = false;
        try {
            initialAutocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement preparedTransactStatement = connection.prepareStatement(update);
            for (int i = 0; i < params.length; i++) {
                preparedTransactStatement.setObject(i + 1, params[i]);
            }
            preparedTransactStatement.executeUpdate();
            connection.commit();
        } catch (Throwable throwable) {
            // You may not want to handle all throwables, but you should with most, e.g.
            // Scala has examples: https://github.com/scala/scala/blob/v2.9.3/src/library/scala/util/control/NonFatal.scala#L1
            if (connection != null) {
                connection.rollback();
            }
            throw throwable;
        } finally {
            if (connection != null) {
                try {
                    if (initialAutocommit) {
                        connection.setAutoCommit(true);
                    }
                    connection.close();
                } catch (Throwable e) {
                    // Use your own logger here. And again, maybe not catch throwable,
                    // but then again, you should never throw from a finally ;)
                    StringWriter out = new StringWriter();
                    e.printStackTrace(new PrintWriter(out));
                    System.err.println("Could not close connection " + out.toString());
                }
            }
        }
    }

    public <T> T execQueryPrepared(String query, Object[] params,
                                   ResultHandler<T> handler)
            throws SQLException {
        // https://stackoverflow.com/questions/15761791/transaction-rollback-on-sqlexception-using-new-try-with-resources-block
        T returnValue;
        boolean initialAutocommit = false;
        try {
            initialAutocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            PreparedStatement stmt = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            ResultSet result = stmt.executeQuery();
            returnValue = handler.handle(result);
            connection.commit();
            return returnValue;
        } catch (Throwable throwable) {
            // You may not want to handle all throwables, but you should with most, e.g.
            // Scala has examples: https://github.com/scala/scala/blob/v2.9.3/src/library/scala/util/control/NonFatal.scala#L1
            if (connection != null) {
                connection.rollback();
            }
            throw throwable;
        } finally {
            if (connection != null) {
                try {
                    if (initialAutocommit) {
                        connection.setAutoCommit(true);
                    }
                    connection.close();
                } catch (Throwable e) {
                    // Use your own logger here. And again, maybe not catch throwable,
                    // but then again, you should never throw from a finally ;)
                    StringWriter out = new StringWriter();
                    e.printStackTrace(new PrintWriter(out));
                    System.err.println("Could not close connection " + out.toString());
                }
            }
        }


//
//
//
//        PreparedStatement stmt = connection.prepareStatement(query);
//        for (int i = 0; i < params.length; i++) {
//            stmt.setObject(i + 1, params[i]);
//        }
//        ResultSet result = stmt.executeQuery();
//        T value = handler.handle(result);
//        result.close();
//        stmt.close();
//
//        return value;
    }

}
