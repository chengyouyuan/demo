package com.springcloud.common.version;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 根据版本优先选择微服务
 *
 * @author lixiaodong
 */
public class VersionedZoneAvoidanceRule extends ZoneAvoidanceRule {
    private MyPredicate myPredicate;

    public VersionedZoneAvoidanceRule() {
        super();
        myPredicate = new MyPredicate(super.getPredicate());
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return myPredicate;
    }

    class MyPredicate extends AbstractServerPredicate {
        private AbstractServerPredicate compositePredicate;

        MyPredicate(AbstractServerPredicate compositePredicate) {
            this.compositePredicate = compositePredicate;
        }

        @Override
        public List<Server> getEligibleServers(List<Server> servers, Object loadBalancerKey) {
            List<Server> serverList = super.getEligibleServers(servers, loadBalancerKey);
            if (serverList == null || serverList.size() <= 1) {
                return serverList;
            }

            String msVer = VersionContext.getVersion();
            if (msVer == null) {
                msVer = StringUtils.EMPTY;
            }

            List<Server> matchedServers = new ArrayList<>(serverList.size());
            List<Server> normalServers = new ArrayList<>(serverList.size());
            for (Server s : serverList) {
                if (s instanceof DiscoveryEnabledServer) {
                    String ver = ((DiscoveryEnabledServer) s).getInstanceInfo().getMetadata().get(VersionContext.HEADER_NAME);
                    if (StringUtils.isNotBlank(ver)) {
                        if (msVer.equals(ver)) {
                            matchedServers.add(s);
                        }
                        continue;
                    }
                }
                normalServers.add(s);
            }
            if (!CollectionUtils.isEmpty(matchedServers)) {
                return matchedServers;
            } else if (!CollectionUtils.isEmpty(normalServers)) {
                return normalServers;
            } else {
                return serverList;
            }
        }

        @Override
        public boolean apply(PredicateKey predicateKey) {
            return compositePredicate.apply(predicateKey);
        }
    }
}


