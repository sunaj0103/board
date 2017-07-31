package com.kosmo.web.board.dao;

import java.util.List;

import com.kosmo.web.board.dto.BoardDto;
import com.kosmo.web.board.dto.BoardPagingDto;

public interface BoardDao {

	//Board �� �ۼ�
	public void insertBoard(BoardDto dto);
	//Board �� ���ڵ� ��
	public int recordCount();
	//Board �˻� �� ���ڵ� ��
	public int recordSearchCount(String searchOption, String keyword);
	//Board ��ü ����Ʈ
	public List<BoardDto> selectAllList(BoardPagingDto pageDto);
	//Board �˻� ����Ʈ
	public List<BoardDto> selectSearchList(BoardPagingDto pageDto, String searchOption, String keyword);
	//�� ����
	public BoardDto selectBoard(int b_num);
	//��ȸ��
	public void boardHitCount(int b_num);
	//Board ����
	public void updateBoard(BoardDto dto);
	//Board ����(����� ���� ���)
	public void updateDelBoard(int b_num);
	//ref, step, lvl �Է�
	public BoardDto selectRef(int b_num);
	//��� step+1
	public void updateReplyBoard(BoardDto refDto);
	//��� �ۼ�
	public void insertReplyBoard(BoardDto dto);
	//����� �ִ��� Ȯ��(������ ���)
	public int countRef(int b_num);
	//����� �ִ��� Ȯ��(����� ���)
	public int countReRef(int lvl, int ref);
	//Board ����(����� ���� ���)
	public void deleteBoard(int b_num);
	//��ü ����Ʈ ������ ����
	public List<BoardDto> listAllExcel();
}
