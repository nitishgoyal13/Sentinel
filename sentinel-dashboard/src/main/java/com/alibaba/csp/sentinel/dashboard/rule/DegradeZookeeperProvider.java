package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import java.util.ArrayList;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("degradeRuleZookeeperProvider")
public class DegradeZookeeperProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private CuratorFramework zkClient;
    @Autowired
    private Converter<String, List<DegradeRuleEntity>> converter;
    @Autowired
    private ZookeeperSentinelConfig zkConfig;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        String zkPath = zkConfig.getDegradeRulePath(appName);
        byte[] bytes = zkClient.getData()
                .forPath(zkPath);
        if (null == bytes || bytes.length == 0) {
            return new ArrayList<>();
        }
        String s = new String(bytes);

        return converter.convert(s);
    }
}