package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.board.model.dao.BoardMapper;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;

	// 게시판 종류 조회
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {
		return mapper.selectBoardTypeList();
	}
	
	// alt + shift + r = 원하는 곳 커서 올린 후 지우면 다 같이 수정됨.
	
	// 게시글 목록 조회
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 1. 특정 게시판의 삭제되지 않은 게시글 수 조회
		int listCount = mapper.getListCount(boardCode);
		
		// 2. 1번 조회 결과 + cp 를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp);
		
		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// ex) 100개
		//	   10개 씩 보여준다
		//	   1page -> 100 ~ 91
		//     2page -> 90  ~ 81
		// 어떤 게시판에서(boardCode)
		// 몇페이지(pagination.currentPage)에 대한
		// 게시글 몇개(pagination.limit)인지 조회
		
		// RowBounds 객체
		// - 마이바티스에서 페이징처리를 위해 제공하는 객체
		// - offset 만큼 건너뛰고
		// 그 다음 지정된 행 개수만큼(limit) 만큼 조회
		
		// 1) offset 계산
		int offset
			= (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Board> boardList = mapper.selectBoardList(boardCode, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		return map;
	}

	// 게시글 상세 조회
	@Override
	public Board selectBoard(Map<String, Object> map) {
		return mapper.selectBoard(map);
	}

	// 좋아요 여부 확인 서비스
	@Override
	public int boardLikeCheck(Map<String, Object> map) {
		return mapper.boardLikeCheck(map);
	}

	// 조회수 증가 서비스
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateReadCount(int boardNo) {
		return mapper.updateReadCount(boardNo);
	}

	// 좋아요 처리 서비스
	@Transactional(rollbackFor = Exception.class)
	@Override                            // paramMap => boardNo, loginMemberNo, check
	public int like(Map<String, Integer> paramMap) {
		
		// check == 0 / 1
		// check 값이 무엇이냐에 따라서 BOARD_LIKE 테이블 INSERT / DELETE
		
		// 결과 저장용 변수선언
		int result = 0;
		
		if(paramMap.get("check") == 0) { // 좋아요 상태 X
			// BOARD_LIKE 테이블 INSERT ( dao.insertBoardLike() )
			result = mapper.insertBoardLike(paramMap);
			
		} else { // 좋아요 상태 O
			// BOARD_LIKE 테이블 DELETE ( dao.deleteBoardLike() )
			result = mapper.deleteBoardLike(paramMap);
			
		}
		
		if(result == 0) return -1;
		
		// 현재 게시글의 좋아요 개수 조회
		int count = mapper.countBoardLike(paramMap.get("boardNo"));
		
		return count;
	}

	// 게시글 목록 조회(검색)
	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> paramMap, int cp) {
		
		// 1. 특정 게시판의 삭제되지 않았고, 검색 조건이 일치하는 게시글 수 조회
		int listCount = mapper.getSearchListCount(paramMap); // 오버로딩
		
		// 2. 1번 조회 결과 + cp 를 이용해서 Pagination 객체 생성
		// -> 내부 필드가 모두 계산되어 초기화됨
		Pagination pagination = new Pagination(listCount, cp);
		
		// 3. 특정 게시판에서
		// 현재 페이지에 해당하는 부분에 대한 게시글 목록 조회
		// 단 , 검색 조건 일치하는 글만
		
		// 1) offset 계산
		int offset
			= (pagination.getCurrentPage() - 1) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		List<Board> boardList = mapper.selectSearchBoardList(paramMap, rowBounds);
		
		// 4. pagination, boardList를 Map에 담아서 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		
		return map;
	}

	// DB 이미지 파일 목록 조회
	@Override
	public List<String> selectImageList() {
		return mapper.selectImageList();
	}

}
