package org.sample.mongodb01.service;

import org.sample.mongodb01.domain.Board;
import org.sample.mongodb01.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class BoardService {

	@Autowired private BoardRepository boardRepository;
	
	public Page<Board> findAll(Board board) {
		Page<Board> list = null;
		PageRequest pageRequest = new PageRequest(board.getPage() - 1, board.getItemPerPage());
		
		if (!StringUtils.isEmpty(board.getSearchOption())) {
			switch (board.getSearchOption()) {
			case "name":
				list = boardRepository.findByRegIdContaining(board.getSearchInput(), pageRequest);
				break;
			case "title":
				list = boardRepository.findByTitleContaining(board.getSearchInput(), pageRequest);
				break;
			case "contents":
				list = boardRepository.findByContentsContaining(board.getSearchInput(), pageRequest);
				break;
			}
		} else {
			list = boardRepository.findAll(pageRequest);
		}

		board.setTotalItemCount(list.getTotalElements());
		return list;
	}
	
	public Board findById(Board board) {
		return boardRepository.findById(board.getId());
	}

	public void save(Board board) throws Exception {
		try {
			boardRepository.save(board);
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
}
