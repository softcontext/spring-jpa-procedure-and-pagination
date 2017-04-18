package com.example.employee.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.employee.dto.Pagination;
import com.example.employee.model.Dept;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class DeptRepositoryTest {
	@Autowired
	private DeptRepository deptRepository;
	
	@Test
	public void testMultiply() {
		int value = 10;
		int result = deptRepository.multiply(value);
		// {call proc_multiply(10,'<OUT>')}
		
		System.out.println("result = "+result);
	}
	
	@Test
	public void testPagination() throws JsonProcessingException {
		int page = 1 - 1; // 0-base 로 보정
		int size = 2;
		
		Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "deptno");
		Page<Dept> result = deptRepository.findAll(pageable);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(result));
		
//		{
//			"content": [
//				{
//					"deptno": 10,
//					"dname": "ACCOUNTING",
//					"loc": "NEW YORK"
//				},
//				{
//					"deptno": 20,
//					"dname": "RESEARCH",
//					"loc": "DALLAS"
//				}
//			],
//			"last": false, // 현재 페이지가 마지막 페이지인지 여부
//			"totalPages": 2, // 전체 페이지 수
//			"totalElements": 4, // 전체 로우 수 [디비 조회]
//			"number": 0, // 현재 페이지 (0-base) [사용자 지정]
//			"size": 2, // 페이지 당 로우 수 [사용자 지정]
//			"sort": [ // 정렬 기준
//				{
//					"direction": "ASC",
//					"property": "deptno",
//					"ignoreCase": false,
//					"nullHandling": "NATIVE",
//					"ascending": true,
//					"descending": false
//				}
//			],
//			"first": true, // 현재 페이지가 첫 페이지인지 여부
//			"numberOfElements": 2 // 현재 페이지의 로우 수
//		}
		
		List<Dept> depts = result.getContent();
		depts.forEach(System.out::println);
		
		/**
		 * TotalRows = 11 // [디비 조회]
		 * 
		 * ThisPage = 1 // 현재 페이지 (1-base) [사용자 지정]
		 * RowsPerPage(size) = 2 // 페이지 당 로우 수 [사용자 지정]
		 * PagesPerBlock = 2 // 블록 당 페이지 수 [사용자 지정]
		 * 
		 * TotalPages = TotalRows/RowsPerPage = 11/2 = 5.5 ==올림==> 6
		 * TotalBlocks = TotalPages/PagesPerBlock = 6/2 = 3 ==올림==> 3
		 * 
		 * ThisBlock = ThisPage/PagesPerBlock = 1/2 = 0.5 ==올림==> 1
		 * ThisBlockEndPage = ThisBlock*PagesPerBlock = 1*2 = 2
		 * ThisBlockStartPage = ThisBlockEndPage-PagesPerBlock+1 = 2-2+1(보정) = 1
		 */
		int bsize = 2; // 블록 당 페이지 수[사용자 지정]
		
		Pagination<Dept> p = new Pagination<>(result, bsize);
		
		System.out.println(mapper.writeValueAsString(p));
//		{
//			"totalRows":11,
//			"thisPage":1,
//			"rowsPerPage":2,
//			"pagesPerBlock":2,
//			"totalPages":6,
//			"totalBlocks":3,
//			"thisBlock":1,
//			"thisBlockEndPage":2,
//			"thisBlockStartPage":1,
//			"numberOfElements":2,
//			"sort":[
//				{
//					"direction":"ASC",
//					"property":"deptno",
//					"ignoreCase":false,
//					"nullHandling":"NATIVE",
//					"ascending":true,"descending":false
//				}],
//			"content":[
//				{
//					"deptno":10,
//					"dname":"ACCOUNTING",
//					"loc":"NEW YORK"
//				},
//				{
//					"deptno":20,
//					"dname":"RESEARCH",
//					"loc":"DALLAS"
//				}],
//			"first":true,
//			"last":false,
//			"zeroBase":false
//		}

	}

}
