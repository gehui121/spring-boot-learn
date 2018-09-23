package com.gehui.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gehui.entity.ApiResult;
import com.gehui.utils.ApiResultGenerator;

@RestController
public class IndexController {

	@RequestMapping(value = "/index/{number}")
	public ApiResult index(@PathVariable int number) {
		System.out.print(20/number);
		Map<String, String> result = new HashMap<>();
		return ApiResultGenerator.successResult(result);
	}
}
