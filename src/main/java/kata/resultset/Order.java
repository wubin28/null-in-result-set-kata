package kata.resultset;

import java.sql.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

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
                LOGGER.info("no records for querying receiver name of order id " + orderId);
                return "";
            }
            String receiverName = "";
            int count = 0;
            do {
                receiverName = resultSet.getString(1) == null ? "" : resultSet.getString(1);
                count++;
            } while (resultSet.next());
            if (count >= 2) {
                LOGGER.info("there are more than 2 receiver names for order id " + orderId);
            }
            return receiverName;
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
