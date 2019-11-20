package com.basement.web.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basement.web.pxy.Box;
import com.basement.web.pxy.CrawlingProxy;
import com.basement.web.pxy.PageProxy;

@RestController
@RequestMapping("/crawler")
public class CrawlingController {
	@Autowired CrawlingProxy crawler;
	@Autowired PageProxy pager;
	@Autowired Box<Object> box;
	@GetMapping("/naver")
	public ArrayList<HashMap<String, String>> naverCrawling() {
		System.out.println("네이버");
		return crawler.engCrawling("https://endic.naver.com/?sLn=kr");
	}
	@GetMapping("/cgv")
	public ArrayList<HashMap<String, String>> cgvCrawling() {
		System.out.println("cgv");
		return crawler.cgvCrawling("http://www.cgv.co.kr/movies/?lt=3");
	}
	@GetMapping("/bugs")
	public Map<?,?> bugsCrawling() {
		System.out.println("벅스");
		ArrayList<HashMap<String, String>> list = crawler.bugsCrawling("https://music.bugs.co.kr/chart");
		pager.setRowCount(list.size());
		pager.setPageSize(10);
		pager.setBlockSize(5);
		pager.setNowPage(0);
		pager.paging();
		ArrayList<HashMap<String, String>> temp = new ArrayList<>();
		for(int i = 0 ; i < list.size(); i++) {
			if(i >= pager.getStartRow() && i <= pager.getEndRow()) {
				temp.add(list.get(i));
			} else if(i > pager.getEndRow()) {
				break;
			}	
		}
		box.put("pager", pager);
		box.put("list", temp);
		return box.get();
	}
}
