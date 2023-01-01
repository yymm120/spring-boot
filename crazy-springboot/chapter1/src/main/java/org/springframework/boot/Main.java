package org.springframework.boot;


import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public class Main {

	/**
	 * RedisURI: 用于封装Redis服务器的连接信息。
	 * RedisClient：Redis客户端，如果是Redis集群则使用RedisClusterClient
	 * StatefulConnection<K,V>: 代表Redis连接的父接口，从它派生出来不少子接口，用来代表不同的连接。
	 * RedisCommands: 用于执行Redis命令的接口，它的方法几乎覆盖了所有Redis命令。相当与redis-cli
	 */


	public RedisURI testRedisURI(){
		RedisURI.create("redis://124.222.48.193:6379");
		RedisURI.Builder.redis("124.222.48.193", 6479).withPassword("haohao666".toCharArray()).withDatabase(0).build();
		new RedisURI("124.222.48.193", 6379, Duration.ofSeconds(60));
		return new RedisURI("", 1, Duration.ofSeconds(1));

	}


	public void testCluster() {
		Set<RedisURI> redisURISet = new HashSet<>();
		redisURISet.add(RedisURI.builder().withHost("127.0.0.1").withPort(7000).build());
		redisURISet.add(RedisURI.builder().withHost("127.0.0.1").withPort(7001).build());
		redisURISet.add(RedisURI.builder().withHost("127.0.0.1").withPort(7002).build());

		RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURISet);
		StatefulRedisClusterConnection<String, String> connect = redisClusterClient.connect();
		RedisAdvancedClusterCommands<String, String> clusterCommands = connect.sync();
		clusterCommands.set("name","bobo");
		System.out.println(clusterCommands.get("name"));
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		RedisURI redis = RedisURI.create("redis");
		System.out.println("Hello world!");
	}
}