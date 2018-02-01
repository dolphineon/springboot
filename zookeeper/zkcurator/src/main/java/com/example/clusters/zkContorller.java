package com.example.clusters;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class zkContorller {
	RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
	CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.59.128:2181", retryPolicy);
	
	@GetMapping("get")
	public String a() throws Exception{
		client.start();
		byte[] mydata = {123};
		client.create().forPath("/aaa",mydata);
		byte[] forPath = client.getData().forPath("/aaa");
		System.out.println(new String(forPath));
		return "ok";
	}
}
