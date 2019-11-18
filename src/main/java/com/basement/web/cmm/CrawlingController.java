package com.basement.web.cmm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crawl")
public class CrawlingController {
	@GetMapping("/naver")
	public String naverCrawling() {
		System.out.println("네이버");
		return "";
	}
	@GetMapping("/cgv")
	public String cgvCrawling() {
		System.out.println("cgv");
		return "";
	}
	@GetMapping("/bugs")
	public String bugsCrawling() {
		System.out.println("벅스");
		return "";
	}
}
