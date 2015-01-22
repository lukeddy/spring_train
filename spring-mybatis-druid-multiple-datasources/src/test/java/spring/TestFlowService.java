package spring;

import com.tangzq.mybatis.domain2.Flow;
import com.tangzq.mybatis.service.FlowService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Created by Administrator on 2015/1/22.
 */
public class TestFlowService extends TestBase{

    @Autowired
    private FlowService flowService;


    @Test
    public void testGet(){
        Flow flow=flowService.get("94003d29-a7b0-42f0-839c-fa609b209ff1");
        Assert.notNull(flow);
        System.out.println(flow.getCodeId()+","+flow.getOperatingTime());
    }
}
