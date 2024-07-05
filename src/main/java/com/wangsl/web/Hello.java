package com.wangsl.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
public class Hello {

	@GetMapping("/")
	public String index(HttpServletRequest request) {
		StringBuilder info = new StringBuilder();
		// 获取请求方法
		info.append("Request Method: ").append(request.getMethod()).append("\n");

		// 获取请求 URI
		info.append("Request URI: ").append(request.getRequestURI()).append("\n");

		// 获取请求参数
		info.append("Request Parameters: \n");
		request.getParameterMap().forEach((key, value) ->
				info.append("  ").append(key).append(": ").append(String.join(", ", value)).append("\n")
		);

		// 获取请求头
		info.append("Request Headers: \n");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			info.append("  ").append(headerName).append(": ").append(headerValue).append("\n");
		}

		// 获取请求的远程地址
		info.append("Remote Address: ").append(request.getRemoteAddr()).append("\n");

		return info.toString();
	}
}
