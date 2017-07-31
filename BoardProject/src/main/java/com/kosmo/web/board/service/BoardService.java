package com.kosmo.web.board.service;

import java.util.List;

import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;

public interface BoardService {
	
	public abstract void insertBoard(BoardDto dto);

	public abstract int recordCount();
	
	public abstract int recordSearchCount(String searchOption, String keyword);

	public abstract List<BoardDto> selectAllList(BoardPagingDto pageDto);
	
	public abstract List<BoardDto> selectSearchList(BoardPagingDto pageDto, String searchOption, String keyword);

	public abstract void boardHitCount(int b_num);

	public abstract BoardDto selectBoard(int b_num);

	public abstract void updateBoard(BoardDto dto);

	public abstract void updateDelBoard(int b_num);

	public abstract BoardDto selectRef(int b_num);

	public abstract void updateReplyBoard(BoardDto refDto);

	public abstract void insertReplyBoard(BoardDto dto);

	public abstract int countRef(int b_num);

	public abstract void deleteBoard(int b_num);

	public abstract int countReRef(int lvl, int ref);

	public abstract List<BoardDto> listAllExcel();

}
