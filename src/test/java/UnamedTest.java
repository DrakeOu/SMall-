
import org.junit.jupiter.api.Test;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.io.File;

public class UnamedTest extends BaseTest {

    @Test
    public void filePahtTest(){
        System.out.println(System.getProperty("user.dir"));
    }
}
