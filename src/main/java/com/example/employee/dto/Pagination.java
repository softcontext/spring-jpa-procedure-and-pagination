package com.example.employee.dto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 
 * @author Seokwon Song (softcontext@gmail.com)
 * @since 2017.4.12.
 * 
 * @param <T>
 * 	T는 처리 대상인 Value Object의 자료형으로 선언한다.
 * 
 * 이용예제 : 1-base paging index 을 사용한다.
 * ---------------------------------------------
 * 	int page = pageable.getPageNumber() > 0 ? (pageable.getPageNumber() - 1) : pageable.getPageNumber();
 * 	Pageable p = new PageRequest(page, pageable.getPageSize(), pageable.getSort());
 * 	Page<Dept> result = deptRepository.findAll(p);
 * 	
 * 	Pagination<Dept> pagination = new Pagination<>();
 * 	pagination.proceed(result, bsize, pagination); 
 * 	return pagination;
 */
public class Pagination<T> {
	private long totalRows;

	private long thisPage;
	private int rowsPerPage;
	private int pagesPerBlock;

	private long totalPages;
	private long totalBlocks;

	private long thisBlock;
	private long thisBlockEndPage;
	private long thisBlockStartPage;

	private boolean isZeroBase; // 0-base 페이징 여부
	private boolean isFirst; // 첫 페이지 여부
	private boolean isLast; // 마지막 페이지 여부
	private int numberOfElements; // 현재 페이지의 로우 수
	private Sort sort; // 정렬 기준
//	"sort": [
//		{
//			"direction": "ASC",
//			"property": "deptno",
//			"ignoreCase": false,
//			"nullHandling": "NATIVE",
//			"ascending": true,
//			"descending": false
//		}
//	]
	private List<T> content;
	
	/**
	 * 
	 * @param result
	 * 	Spring Data JPA 기술로부터 얻은 페이징 정보가 담겨 있는 DTO 객체를 받는다. 
	 * 	이전, 현재, 다음 페이징의 간단한 처리의 적합한 상태이다. 
	 * 	이 데이터를 이용 계산하여 페이징 넘버링 기능을 제공하기 위한 페이징 처리 시 필요한 내용을 구한다.
	 * @param bsize
	 * 	블록 당 페이지 수
	 * @param p
	 * 	빈 DTO 객체를 받는다.
	 * @return Pagination<T>
	 * 	빈 DTO 객체에 페이징 처리 시 필요한 내용을 채워 넣은 후 리턴한다.
	 */
	public Pagination<T> proceed(Page<T> result, int bsize, Pagination<T> p){ // bsize : 블록 당 페이지 수[사용자 지정]
		p.setTotalRows(result.getTotalElements()); // 전체 로우 수[디비 조회]
		
		p.setThisPage(result.getNumber()+1); // 현재 페이지 (1-base 로 보정) [사용자 지정]
		p.setRowsPerPage(result.getSize()); // 페이지 당 로우 수 [사용자 지정]
		
		p.setPagesPerBlock(bsize);
		
		p.setTotalPages(result.getTotalPages()); // 전체 페이지 수
		p.setTotalBlocks((long) Math.ceil(p.getTotalPages() / (double) p.getPagesPerBlock())); // 전체 블록 수
		
		p.setThisBlock((long) Math.ceil(p.getThisPage() / (double) p.getPagesPerBlock())); // 현재 블록
		p.setThisBlockEndPage(p.getThisBlock() * p.getPagesPerBlock()); // 현재 블록 마지막 페이지 번호
		p.setThisBlockStartPage(p.getThisBlockEndPage() - p.getPagesPerBlock() + 1); // 현재 블록 첫 페이지 번호
		
		p.setZeroBase(false); // 1-base paging index
		p.setFirst(result.isFirst());
		p.setLast(result.isLast());
		p.setNumberOfElements(result.getNumberOfElements());
		p.setSort(result.getSort());
		p.setContent(result.getContent());
		
		return p;
	}
	
	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public long getThisPage() {
		return thisPage;
	}

	public void setThisPage(long thisPage) {
		this.thisPage = thisPage;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPagesPerBlock() {
		return pagesPerBlock;
	}

	public void setPagesPerBlock(int pagesPerBlock) {
		this.pagesPerBlock = pagesPerBlock;
	}

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalBlocks() {
		return totalBlocks;
	}

	public void setTotalBlocks(long totalBlocks) {
		this.totalBlocks = totalBlocks;
	}

	public long getThisBlock() {
		return thisBlock;
	}

	public void setThisBlock(long thisBlock) {
		this.thisBlock = thisBlock;
	}

	public long getThisBlockEndPage() {
		return thisBlockEndPage;
	}

	public void setThisBlockEndPage(long thisBlockEndPage) {
		this.thisBlockEndPage = thisBlockEndPage;
	}

	public long getThisBlockStartPage() {
		return thisBlockStartPage;
	}

	public void setThisBlockStartPage(long thisBlockStartPage) {
		this.thisBlockStartPage = thisBlockStartPage;
	}

	public boolean isZeroBase() {
		return isZeroBase;
	}

	public void setZeroBase(boolean isZeroBase) {
		this.isZeroBase = isZeroBase;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

}
