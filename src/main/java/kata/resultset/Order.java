package kata.resultset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Order {

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

            if (resultSet.next() == false) {
                receiverName = "";
            } else {
                do {
                    receiverName = resultSet.getString(1);
                    if (receiverName == null) {
                        receiverName = "";
                    }
                } while (resultSet.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return receiverName;
    }
}
