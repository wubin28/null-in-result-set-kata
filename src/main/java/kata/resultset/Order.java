package kata.resultset;

import java.sql.*;

public class Order {

    public String queryReceiverName(String orderId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:42588;", "admin", "root");
            preparedStatement =
                    connection.prepareStatement("select receiver_name from orders where order_id = ?");
            preparedStatement.setString(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false) {
                return "";
            }
            return resultSet.getString(1) == null ? "" : resultSet.getString(1);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
