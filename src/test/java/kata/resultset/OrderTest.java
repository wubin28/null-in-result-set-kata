package kata.resultset;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class OrderTest {
    @Test
    public void query_查询订单金额()
    {
        Order order = new Order();
        assertEquals(27.4, order.querySum("26655056069649850"), 0.005);
    }
}
