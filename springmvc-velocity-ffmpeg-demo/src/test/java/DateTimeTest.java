import com.tzq.util.DateUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: tzq
 * Date: 13-5-31
 * Time: 下午8:08
 * To change this template use File | Settings | File Templates.
 */
public class DateTimeTest {
    @Test
    public void testTime(){
      System.out.println(DateUtils.getDate(DateUtils.DD_MM_YYYY_HH_MM_AA));
      //result like: 05/29/2013, 11:30am
    }
}
