package com.kosmo.web.board.dao;

import java.util.List;

import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;

public interface BoardDao {

	//Board 글 작성
	public void insertBoard(BoardDto dto);
	//Board 총 레코드 수
	public int recordCount();
	//Board 검색 총 레코드 수
	public int recordSearchCount(String searchOption, String keyword);
	//Board 전체 리스트
	public List<BoardDto> selectAllList(BoardPagingDto pageDto);
	//Board 검색 리스트
	public List<BoardDto> selectSearchList(BoardPagingDto pageDto, String searchOption, String keyword);
	//글 선택
	public BoardDto selectBoard(int b_num);
	//조회수
	public void boardHitCount(int b_num);
	//Board 수정
	public void updateBoard(BoardDto dto);
	//Board 삭제(답글이 있을 경우)
	public void updateDelBoard(int b_num);
	//ref, step, lvl 입력
	public BoardDto selectRef(int b_num);
	//답글 step+1
	public void updateReplyBoard(BoardDto refDto);
	//답글 작성
	public void insertReplyBoard(BoardDto dto);
	//답글이 있는지 확인(원글인 경우)
	public int countRef(int b_num);
	//답글이 있는지 확인(답글일 경우)
	public int countReRef(int lvl, int ref);
	//Board 삭제(답글이 없을 경우)
	public void deleteBoard(int b_num);
	//전체 리스트 엑셀로 저장
	public List<BoardDto> listAllExcel();
}
