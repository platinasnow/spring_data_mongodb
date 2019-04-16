package org.sample.mongodb01.repository;

import org.sample.mongodb01.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface BoardRepository extends PagingAndSortingRepository<Board, String> {
	
	public Page<Board> findByTitleContaining(String title, Pageable pageable);
	
	public Page<Board> findByContentsContaining(String contents, Pageable pageable);
	
	public Page<Board> findByRegIdContaining(String regId, Pageable pageable);
	
	public Board findById(String id);
	
}
