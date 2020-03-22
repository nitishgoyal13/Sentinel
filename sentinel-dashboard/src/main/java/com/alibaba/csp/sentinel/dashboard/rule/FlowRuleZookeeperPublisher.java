package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component("flowRuleZookeeperPublisher")
public class FlowRuleZookeeperPublisher implements DynamicRulePublisher<List<FlowRuleEntity>> {

    @Autowired
    private CuratorFramework zkClient;
    @Autowired
    private Converter<List<FlowRuleEntity>, String> converter;
    @Autowired
    private ZookeeperSentinelConfig zkConfig;

    @Override
    public void publish(String app, List<FlowRuleEntity> rules) throws Exception {
        AssertUtil.notEmpty(app, "app name cannot be empty");

        String path = zkConfig.getFlowRulePath(app);
        Stat stat = zkClient.checkExists()
                .forPath(path);
        if (stat == null) {
            zkClient.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(path, null);
        }
        byte[] data = CollectionUtils.isEmpty(rules) ? "[]".getBytes() : converter.convert(rules)
                .getBytes();
        zkClient.setData()
                .forPath(path, data);
    }
}