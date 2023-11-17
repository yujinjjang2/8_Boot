package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Mapper
public interface BoardMapper {

	/** 게시판 종류 조회
	 * @return boardTypeList
	 */
	List<Map<String, Object>> selectBoardTypeList();
	
	/** 특정 게시판의 삭제되지 않은 게시글 수 조회
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode);


	/** 특정 게시판에서 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardList(int boardCode, RowBounds rowBounds);


	/** 게시글 상세 조회
	 * @param map
	 * @return board
	 */
	public Board selectBoard(Map<String, Object> map);


	/** 좋아요 여부 확인 DAO
	 * @param map
	 * @return result
	 */
	public int boardLikeCheck(Map<String, Object> map);


	/** 조회수 증가 DAO
	 * @param boardNo
	 * @return result
	 */
	public int updateReadCount(int boardNo);


	/** 좋아요 테이블 삽입
	 * @param paramMap
	 * @return
	 */
	public int insertBoardLike(Map<String, Integer> paramMap);


	/** 좋아요 삭제
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardLike(Map<String, Integer> paramMap);


	/** 좋아요 개수 조회
	 * @param integer
	 * @return
	 */
	public int countBoardLike(Integer boardNo);


	/** 게시글 수 조회(검색)
	 * @param paramMap
	 * @return
	 */
	public int getSearchListCount(Map<String, Object> paramMap);
	
	// 원하는 곳 커서 올린 후 alt + shift + r -> Enter 
	// 				= 호출하는 부분도 자동으로 이름 바꿔줌.


	/** 게시글 목록 조회 (검색)
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public List<Board> selectSearchBoardList(Map<String, Object> paramMap, RowBounds rowBounds);


	/** DB 이미지 파일 목록 조회
	 * @return
	 */
	public List<String> selectImageList();

}
