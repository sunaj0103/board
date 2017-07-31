package com.kosmo.web.board.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;
import com.kosmo.web.board.dao.BoardDao;
import com.kosmo.web.board.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	//Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="boardDao")
    private BoardDao boardDao;

	@Override
	public void insertBoard(BoardDto dto) {
		boardDao.insertBoard(dto);
	}

	@Override
	public int recordCount() {
		return boardDao.recordCount();
	}
	
	@Override
	public int recordSearchCount(String searchOption, String keyword) {
		return boardDao.recordSearchCount(searchOption, keyword);
	}

	@Override
	public List<BoardDto> selectAllList(BoardPagingDto pageDto) {
		return boardDao.selectAllList(pageDto);
	}

	@Override
	public List<BoardDto> selectSearchList(BoardPagingDto pageDto, String searchOption, String keyword) {
		return boardDao.selectSearchList(pageDto, searchOption, keyword);
	}

	@Override
	public List<BoardDto> listAllExcel() {
		return boardDao.listAllExcel();
	}

	@Override
	public void boardHitCount(int b_num) {
		boardDao.boardHitCount(b_num);
	}

	@Override
	public BoardDto selectBoard(int b_num) {
		return boardDao.selectBoard(b_num);
	}

	@Override
	public void updateBoard(BoardDto dto) {
		boardDao.updateBoard(dto);
	}

	@Override
	public void updateDelBoard(int b_num) {
		boardDao.updateDelBoard(b_num);
	}

	@Override
	public BoardDto selectRef(int b_num) {
		return boardDao.selectRef(b_num);
	}

	@Override
	public void updateReplyBoard(BoardDto refDto) {
		boardDao.updateReplyBoard(refDto);
	}

	@Override
	public void insertReplyBoard(BoardDto dto) {
		boardDao.insertReplyBoard(dto);
	}

	@Override
	public int countRef(int b_num) {
		return boardDao.countRef(b_num);
	}

	@Override
	public void deleteBoard(int b_num) {
		boardDao.deleteBoard(b_num);
	}

	@Override
	public int countReRef(int lvl, int ref) {
		return boardDao.countReRef(lvl, ref);
	}

}
