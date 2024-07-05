package com.wangsl.redis;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public static final String METHOD = "method";
	public static final String URI = "uri";
	public static final String NUM = "num";

	@RequestMapping()
	public Map<String, String> redis(HttpServletRequest request) {
		String ip = request.getRemoteAddr() + ":";
		redisTemplate.opsForValue().set(ip + METHOD, request.getMethod());
		redisTemplate.opsForValue().set(ip + URI, request.getRequestURI());
		redisTemplate.opsForValue().increment(ip + NUM, 1);

		Map<String, String> map = new HashMap<>();
		map.put(METHOD, redisTemplate.opsForValue().get(ip + METHOD));
		map.put(URI, redisTemplate.opsForValue().get(ip + URI));
		map.put(NUM, redisTemplate.opsForValue().get(ip + NUM));

		return map;
	}

	@RequestMapping("/up")
	public Map<String, Object> index(HttpServletRequest request) {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++){
			redis(request);
		}
		long end = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<>();
		map.put("code", 200);
		map.put("msg", "success");
		map.put("time", end - start);
		map.put("data", null);
		return map;
	}
}
