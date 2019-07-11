package kata.resultset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {
    @Test
    public void query_查询订单收货人()
    {
        Order order = new Order();
        assertEquals("张先生", order.queryReceiverName("26655056069649850"));
    }
}
