package kata.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

public class Order {

    private static final Logger LOGGER = Logger.getLogger(Order.class.getName());

    public String queryReceiverName(String orderId) {
        String receiverName = "";
        Connection connection = null;
        try {

            String url = "jdbc:sqlserver://localhost:42588;";

            connection = DriverManager.getConnection(url, "admin", "root");

            PreparedStatement preparedStatement =
                    connection.prepareStatement("select receiver_name from orders where order_id = ?");
            preparedStatement.setString(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            if (resultSet.next() == false) {
                LOGGER.info("no records for querying receiver name of order id " + orderId);
                return "";
            }
            do {
                receiverName = resultSet.getString(1);
                if (receiverName == null) {
                    receiverName = "";
                }
                count++;
            } while (resultSet.next());
            if (count >= 2) {
                LOGGER.info("there are more than 2 receiver names for order id " + orderId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return receiverName;
    }
}
