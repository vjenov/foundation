package com.basement.web.pxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data@Lazy
@Component("pager")
public class PageProxy extends Proxy{
	@Autowired CrawlingProxy crawler;
	private int rowCount, startRow, endRow,
	pageSize, pageCount, nowPage, startPage, endPage,
	blockSize, blockCount, nowBlock, prevBlock, nextBlock;
	private boolean existPrev, existNext;
	private String search;

	public void paging() {
		pageCount = (rowCount % pageSize == 0) ? rowCount / pageSize : rowCount / pageSize + 1;
		blockCount = (pageCount % blockSize ==0) ? pageCount / blockSize : pageCount / blockSize +1;
		startRow = nowPage * pageSize;
		endRow = (nowPage != (pageCount-1)) ? startRow + (pageSize-1) : rowCount-1;
		nowBlock = nowPage / blockSize;
		startPage = nowBlock * blockSize;
		endPage = (nowBlock != (blockCount-1)) ? startPage + (blockSize-1) : pageCount-1;
		prevBlock = startPage - blockSize;
		nextBlock = startPage + blockSize;
		existPrev = nowBlock > 0;
		existNext = nowBlock < (blockCount-1);
	}
}
