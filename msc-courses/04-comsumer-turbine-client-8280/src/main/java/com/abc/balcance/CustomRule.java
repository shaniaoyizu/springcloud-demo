package com.abc.balcance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by sunbc on 2020-02-29
 */
public class CustomRule implements IRule {

    private ILoadBalancer lb;

    //记录所有要排除的端口号
    private List<Integer> excludePort;

    public CustomRule() {
    }

    public CustomRule(List<Integer> excludePort) {
        this.excludePort = excludePort;
    }

    @Override
    public Server choose(Object o) {
        // 获取所以UP状态的server
        List<Server> servers = lb.getReachableServers();
        // 获取到排除了指定端口的所有剩余Servers
        List<Server> availableServers = getAvailableServers(servers);
        // 对剩余的Servers通过随机方式获取一个Server
        return getAvailableRandomServer(availableServers);
    }

    /**
     * 对剩余的Servers通过随机方式获取一个Server
     *
     * @param servers
     * @return
     */
    private Server getAvailableRandomServer(List<Server> servers) {
        int index = new Random().nextInt(servers.size());
        return servers.get(index);
    }

    /**
     * 获取到排除了指定端口的所有剩余Servers
     * 普通代码方式
     * @param servers
     * @return
     */
//    private List<Server> getAvailableServers(List<Server> servers) {
//        if (excludePort == null || excludePort.size() == 0){
//            return  servers;
//        }
//        List<Server> aservers = new ArrayList<>();
//        for (Server server : servers) {
//            boolean isExclude = false;
//            for (Integer port : excludePort){
//                if (server.getPort() == port){
//                    isExclude = true;
//                    break;
//                }
//            }
//            if (!isExclude){
//                aservers.add(server);
//            }
//        }
//        return aservers;
//    }

    /**
     * 获取到排除了指定端口的所有剩余Servers
     * lambda方式
     * @param servers
     * @return
     */
    private List<Server> getAvailableServers(List<Server> servers) {
        if (excludePort == null || excludePort.size() == 0){
            return  servers;
        }
        List<Server> aservers = servers.stream() //将list变为stream
            .filter(server -> excludePort.stream().noneMatch(port -> server.getPort() == port))
            .collect(Collectors.toList());
        return aservers;
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.lb = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return null;
    }
}
