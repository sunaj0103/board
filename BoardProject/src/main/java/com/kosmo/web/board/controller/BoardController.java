package com.kosmo.web.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;
import com.kosmo.web.board.service.BoardService;
import com.kosmo.web.util.WriteListToExcelFile;

@Controller
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;
	
	@Value("${board.title}")
	private String title;
	
	@RequestMapping("/boardWrite")
	public String boardWrite(){
		return "boardWrite";
	}
	
	@RequestMapping("/error")
	public String error(){
		return "error";
	}
	
	@RequestMapping("/boardWriteOk")
	public String boardWriteOk(BoardDto dto, RedirectAttributes redirectAttributes) {
		try {
			boardService.insertBoard(dto);
			redirectAttributes.addFlashAttribute("ok", "write");
			return "redirect:/board";
		} catch (Exception e) {
			logger.error("Error at boardWriteOk", e);
			redirectAttributes.addFlashAttribute("msg","���� ��ϵ��� �ʾҽ��ϴ�.");
			return "redirect:/error";
		}
	}
	
	@RequestMapping("/board")
	public ModelAndView board(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		List<BoardDto> list = new ArrayList<BoardDto>();
		
		int cPage = 1;
		int count = 0;
		
		if(request.getParameter("currentPage") != null && !request.getParameter("currentPage").equals("")){
			cPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		String searchOption = request.getParameter("searchOption");
		String keyword = request.getParameter("keyword");
		
		if(searchOption != null && !searchOption.equals("")){
			count = boardService.recordSearchCount(searchOption, keyword);
		}else{
			count = boardService.recordCount();
		}
		
		BoardPagingDto pageDto = new BoardPagingDto();
		pageDto.makePaging(cPage, count);
		
		//������������ �϶�
		if(pageDto.getCurrentPage() == pageDto.getTotalPageCount()){
			//�������������� ���� ���ڵ� ��
			int modRecord = pageDto.getTotalRecord() % pageDto.getOnePageRecord();
			if(modRecord!=0){
				pageDto.setLastPageRecord(modRecord);
			}
		}
		
		if(searchOption != null && !searchOption.equals("")){
			list = boardService.selectSearchList(pageDto, searchOption, keyword);			
			mav.addObject("searchOption", searchOption);
			mav.addObject("keyword", keyword);
			logger.info("searchOption: " + searchOption);
			logger.info("keyword: " + keyword);
		}else{
			list = boardService.selectAllList(pageDto);
		}
		
		mav.addObject("pageDto", pageDto);
		mav.addObject("list", list);
		mav.addObject("title", title);
		mav.setViewName("board");
		return mav;
	}
	
	@RequestMapping("/allExcel")
	public String allExcel(RedirectAttributes redirectAttributes){
		try {
			List<BoardDto> allList = boardService.listAllExcel();
			WriteListToExcelFile.WriteListToExcelFile("board_list.xls", allList);
			
			redirectAttributes.addFlashAttribute("ok", "excel");
			return "redirect:/board";
			
		} catch (Exception e) {
			logger.error("Error at boardExcel", e);
			redirectAttributes.addFlashAttribute("msg", "���� ������ �������� �ʾҽ��ϴ�.");
			return "redirect:/error";
		}
	}
	
	@RequestMapping("/boardListView")
	public ModelAndView boardListView(@RequestParam("num") int b_num){
		ModelAndView mav = new ModelAndView();
		boardService.boardHitCount(b_num);
		BoardDto dto = boardService.selectBoard(b_num);
		
		mav.addObject("dto", dto);
		mav.setViewName("boardListView");
		return mav;
		
	}
	
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(@RequestParam("num") int b_num){
		BoardDto dto = boardService.selectBoard(b_num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("boardEdit");
		return mav;
	}
	
	@RequestMapping("/boardEditOk")
	public String boardEditOk(BoardDto Dto, RedirectAttributes redirectAttributes){
		try {
			boardService.updateBoard(Dto);
			redirectAttributes.addFlashAttribute("ok", "edit");
			return "redirect:/board";
			
		} catch (Exception e) {
			logger.error("Error at boardEditOk", e);
			redirectAttributes.addFlashAttribute("msg", "���� �������� �ʾҽ��ϴ�.");
			return "redirect:/error";
		}
	}
	
	@RequestMapping("/boardDel")
	public String boardDel(@RequestParam("num") int b_num, @RequestParam("lvl") int lvl, @RequestParam("ref") int ref, RedirectAttributes redirectAttributes){
		try {
			if(lvl==0){ //����� �ƴ� ���
				int countRefNum = boardService.countRef(b_num);
				if(countRefNum <= 1){ //����� ���� ���
					boardService.deleteBoard(b_num);
				}else{
					boardService.updateDelBoard(b_num);
				}
				
			}else{ //����� ���
				int countRefNum = boardService.countReRef(lvl, ref);
				if(countRefNum <= 0){ //����� ���� ���
					boardService.deleteBoard(b_num);
				}else{
					boardService.updateDelBoard(b_num);
				}
			}
			
			redirectAttributes.addFlashAttribute("ok", "del");
			return "redirect:/board";
			
		} catch (Exception e) {
			logger.error("Error at boardDel", e);
			redirectAttributes.addFlashAttribute("msg", "���� �������� �ʾҽ��ϴ�.");
			return "redirect:/error";
		}
	}
	
	@RequestMapping("/boardReplyWrite")
	public ModelAndView boardReplyWrite(@RequestParam("b_num") int b_num, @RequestParam("lvl") int lvl){
		ModelAndView mav = new ModelAndView();
		mav.addObject("b_num", b_num);
		mav.addObject("lvl", lvl);
		mav.setViewName("boardReplyWrite");
		return mav;
	}
	
	@RequestMapping("/boardReplyWriteOk")
	public String boardReplyWriteOk(BoardDto Dto, RedirectAttributes redirectAttributes){
		try {
			BoardDto refDto = boardService.selectRef(Dto.getB_num());
			refDto.setB_num(Dto.getB_num());
			
			boardService.updateReplyBoard(refDto);
			Dto.setRef(refDto.getRef());
			Dto.setStep(refDto.getStep()+1);
			Dto.setLvl(refDto.getLvl()+1);
			
			boardService.insertReplyBoard(Dto);
			
			redirectAttributes.addFlashAttribute("ok", "reWrite");
			return "redirect:/board";
			
		} catch (Exception e) {
			logger.error("Error at boardReplyWriteOk", e);
			redirectAttributes.addFlashAttribute("msg", "����� ��ϵ��� �ʾҽ��ϴ�.");
			return "redirect:/error";
		}
	}
	
}
