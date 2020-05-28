import com.xjtuse.mall.bean.mall.Brand;
import com.xjtuse.mall.service.admin.MallService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class MallServiceTest extends BaseTest {

    @Autowired
    MallService service;

    @Test
    public void updateBrandTest(){

        Brand brand = new Brand();
        brand.setId(1001000);
        brand.setName("MUJI制造商");
        brand.setDesc("严选精选了MUJI制造商和生产原料，用几乎零利润的价格，剔除品牌溢价，让用户享受原品牌的品质生活。");
        brand.setFloorPrice(BigDecimal.valueOf(12.90));
        brand.setPicUrl("http://yanxuan.nosdn.127.net/1541445967645114dd75f6b0edc4762d.png");
        service.updateBrandInfo(brand);
    }
}
