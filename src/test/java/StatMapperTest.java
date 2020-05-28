import com.xjtuse.mall.bean.mall.Order;
import com.xjtuse.mall.mapper.admin.StatMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class StatMapperTest extends BaseTest {

    @Autowired
    StatMapper mapper;

    @Test
    public void queryDateTest(){
        List<Date> dates = mapper.queryAllOrderDate();
        for (Date date :
                dates) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            List<Order> orders = mapper.queryOrderByDate(java.sql.Date.valueOf(localDate).toString());
            System.out.println(orders.size());
        }
    }

    @Test
    public void queryGoodsTest(){
        Order order = new Order();
        order.setId(2);
//        int count = mapper.queryGoodsCount(order);
//        System.out.println(count);
    }
}
