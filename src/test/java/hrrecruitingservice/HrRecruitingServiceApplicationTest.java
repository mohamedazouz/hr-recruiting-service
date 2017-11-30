package hrrecruitingservice;

import com.azouz.hrrecruitingservice.HrRecruitingServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mazouz
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HrRecruitingServiceApplication.class)
public class HrRecruitingServiceApplicationTest {


  @Test
  public void applicationContextLoaded() {
  }

  @Test
  public void applicationContextTest() {
    HrRecruitingServiceApplication.main(new String[]{});
  }
}

