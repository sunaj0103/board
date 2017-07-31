package com.kosmo.web.board.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosmo.web.board.dao.BoardDao;
import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public void insertBoard(BoardDto dto) {
		sqlSession.insert("board.insertBoard", dto);
	}

	@Override
	public int recordCount() {
		return (Integer)sqlSession.selectOne("board.recordCount");
	}
	
	@Override
	public int recordSearchCount(String searchOption, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return (Integer)sqlSession.selectOne("board.recordSearchCount", map);
	}
	
	@Override
	public List<BoardDto> selectAllList(BoardPagingDto pageDto) {
		return sqlSession.selectList("board.selectAllList", pageDto);
	}

	@Override
	public List<BoardDto> selectSearchList(BoardPagingDto pageDto, String searchOption, String keyword) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageDto", pageDto);
		map.put("searchOption", searchOption);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.selectSearchList", map);
	}

	@Override
	public BoardDto selectBoard(int b_num) {
		return sqlSession.selectOne("board.selectBoard", b_num);
	}

	@Override
	public List<BoardDto> listAllExcel() {
		return sqlSession.selectList("board.listAllExcel");
	}

	@Override
	public void boardHitCount(int b_num) {
		sqlSession.update("board.boardHitCount", b_num);
	}

	@Override
	public void updateBoard(BoardDto dto) {
		sqlSession.update("board.updateBoard", dto);
	}

	@Override
	public void updateDelBoard(int b_num) {
		sqlSession.update("board.updateDelBoard", b_num);
	}

	@Override
	public BoardDto selectRef(int b_num) {
		return sqlSession.selectOne("selectRef", b_num);
	}

	@Override
	public void updateReplyBoard(BoardDto refDto) {
		sqlSession.update("updateReplyBoard", refDto);
	}

	@Override
	public void insertReplyBoard(BoardDto dto) {
		sqlSession.insert("insertReplyBoard", dto);
	}

	@Override
	public int countRef(int b_num) {
		return sqlSession.selectOne("countRef", b_num);
	}
	
	@Override
	public int countReRef(int lvl, int ref) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("lvl", lvl);
		map.put("ref", ref);
		
		return sqlSession.selectOne("countReRef", map);
	}

	@Override
	public void deleteBoard(int b_num) {
		sqlSession.delete("deleteBoard", b_num);
	}

}
