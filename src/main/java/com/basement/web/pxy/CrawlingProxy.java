package com.basement.web.pxy;

import java.util.ArrayList;
import java.util.HashMap;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("crawler")@Lazy
public class CrawlingProxy extends Proxy{
	@Autowired Inventory<HashMap<String, String>> inven;
	@Autowired Box<String> box;
	public ArrayList<HashMap<String, String>> engCrawling(String url) {
		inven.clear();
		try {
			Document rawData = Jsoup.connect(url).timeout(10*1000).get();
			Elements txtOrigin = rawData.select("div[class=\"txt_origin\"] a");
			Elements txtTrans = rawData.select("div[class=\"txt_trans\"]");
			HashMap<String, String> map = null;
			for(int i=0; i < txtOrigin.size(); i++) {
				map = new HashMap<>();
				map.put("origin", txtOrigin.get(i).text());
				map.put("trans", txtTrans.get(i).text());
				inven.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------크롤링 결과-------------");
		System.out.println(inven.get().size());
		inven.get().forEach(System.out::println);
		return inven.get();
	}
	public ArrayList<HashMap<String, String>> cgvCrawling(String url) {
		inven.clear();
		try {
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		Connection.Response homePage = Jsoup.connect(url) 
				.method(Connection.Method.GET) 
				.userAgent(USER_AGENT) 
				.execute();
		Document rawData = homePage.parse();
		Elements element = rawData.select("div.sect-movie-chart");
		Elements title = element.select("strong.title");
		Elements percent = element.select("strong.percent");
		Elements textinfo= element.select("span.txt-info");
		Elements photo = rawData.select("span.thumb-image");
		HashMap<String, String> map = null;
//		Consumer<Cgv> c = t -> hrMapper.insertCgvRank(t);		
		for (int i = 0; i < title.size(); i++) {
			map = new HashMap<>();
			map.put("title", title.get(i).toString());
			map.put("percent", percent.get(i).toString());
			map.put("textinfo", textinfo.get(i).toString());
			map.put("photo", photo.get(i).select("img").attr("src"));
			inven.add(map);
			/*
			 * tempCgvs.setCgvseq(String.valueOf(cgvseq+1));
			 * tempCgvs.setTitle(tempTitle.text());
			 * tempCgvs.setContent(tempforPrecent.get(cgvseq).text() +
			 * "/"+tempforTextinfo.get(cgvseq).text());
			 * tempCgvs.setImg(tempforphoto.get(cgvseq).select("img").attr("src"));
			 * tempList.add(tempCgvs);
			 */
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("-------------크롤링 결과-------------");
		System.out.println(inven.get().size());
		inven.get().forEach(System.out::println);
		return inven.get();
	}
	public ArrayList<HashMap<String, String>> bugsCrawling(String url) {
		inven.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			Connection.Response homePage = Jsoup.connect(url).method(Connection.Method.GET).userAgent(USER_AGENT)
					.execute();
			Document temp = homePage.parse();
			Elements tempforTitle = temp.select("p.title");
			Elements tempforContent = temp.select("p.artist");
			Elements tempforphoto = temp.select("a.thumbnail");
			HashMap<String, String>map=null;
			for (int i = 0; i<tempforTitle.size(); i++) {
				map = new HashMap<>();
				map.put("seq", string(i+1));
				map.put("title", tempforTitle.get(i).text());
				map.put("artist", tempforContent.get(i).text());
				map.put("thumbnail", tempforphoto.get(i).select("img").attr("src"));
				inven.add(map);
			}
		} catch (Exception e) {
		}
		System.out.println("********************크롤링결과********************");
		inven.get().forEach(System.out :: println);
		return inven.get();
	}
}
