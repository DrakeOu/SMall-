import com.xjtuse.mall.bean.mall.*;
import com.xjtuse.mall.bean.user.User;
import com.xjtuse.mall.mapper.admin.MallMapper;
import com.xjtuse.mall.utils.PageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MallMapperTest extends BaseTest {

    @Autowired
    MallMapper mallMapper;

    @Test
    public void updateBrandTest(){
        Brand brand = new Brand();
        brand.setId(1001000);
        brand.setName("MUJI制造商");
        brand.setDesc("严选精选了MUJI制造商和生产原料，用几乎零利润的价格，剔除品牌溢价，让用户享受原品牌的品质生活。");
        brand.setFloorPrice(BigDecimal.valueOf(12.10));
        brand.setPicUrl("http://yanxuan.nosdn.127.net/1541445967645114dd75f6b0edc4762d.png");
        mallMapper.updateBrandInfo(brand);
    }

    @Test
    public void createCategoryTest(){
        Category category = new Category();
        category.setAddTime(new Date());
        category.setUpdateTime(new Date());
        category.setDesc("????");
        category.setKeywords("八千");
        category.setIconUrl("-----");
        category.setPicUrl("-----");
        category.setName("nmsl");
        category.setPid(0);
        mallMapper.createCategory(category);
    }

    @Test
    public void updateCategoryTest(){
        Category category = new Category();
        category.setId(1036006);
        category.setName("新网恋交付");
        category.setKeywords("日本天皇");
        category.setDesc("---");
        category.setPid(1013001);
        category.setIconUrl("http://localhost:6001/admin/storage/fetch/27dd89a288e94ab095787d5078f440be.png");
        category.setPicUrl("http://localhost:6001/admin/storage/fetch/27dd89a288e94ab095787d5078f440be.png");
        category.setUpdateTime(new Date());
        category.setLevel("L2");
        category.setDeleted(false);
        mallMapper.updateCategory(category);
    }

    @Test
    public void queryOrderTest(){
        PageUtil pageUtil = new PageUtil();
//        pageUtil.setPage(1);
//        pageUtil.setOrder("desc");
//        pageUtil.setLimit(10);
//        pageUtil.setSort("add_time");
//        pageUtil.initStart();
        Order order1 = new Order();
        int[] statusArray = {};
//        order1.setOrderSn("sad");
        order1.setUserId(1);
//        statusArray = new int[]{100, 102, 103};
        List<Order> orders = mallMapper.queryOrder(pageUtil, order1, statusArray);
        for (Order order :
                orders) {
            System.out.println(order);
        }
    }

    @Test
    public void OrderGoodsTest(){
        Order order = new Order();
        order.setId(1);
        List<OrderAndGoods> orderAndGoods = mallMapper.queryOAGByOrderId(order);
        System.out.println(orderAndGoods);
    }

    @Test
    public void queryUserTest(){
        Order order = new Order();
        order.setUserId(1);
        User user = mallMapper.queryUserById(order);
        System.out.println(user);
    }

    @Test
    public void queryIssueTest(){
        PageUtil pageUtil = new PageUtil();
        pageUtil.setSort("add_time");
        pageUtil.setLimit(10);
        pageUtil.setPage(1);
        pageUtil.setOrder("desc");
        pageUtil.initStart();
        List<Issue> issues = mallMapper.queryIssue(pageUtil, new Issue());
        for (Issue issue :
                issues) {
            System.out.println(issue);
        }

    }
}
