package kata.resultset;

import java.sql.*;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class Order {

    public String queryReceiverName(String orderId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String receiverName = "";
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
            int count = 0;
            do {
                receiverName = resultSet.getString(1) == null ? "" : resultSet.getString(1);
                count++;
            } while (resultSet.next());
            if (count >= 2) {
                LOGGER.info("there are more than 2 receiver names for order id " + orderId);
            }
        } catch (SQLException e) {
            LOGGER.warning(e.getSQLState() + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.warning(e.getSQLState() + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.warning(e.getSQLState() + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.warning(e.getSQLState() + e.getMessage());
                }
            }
        }
        return receiverName;
    }
}
