package org.sample.mongodb01;

import org.sample.mongodb01.domain.Board;
import org.sample.mongodb01.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired private BoardService boardService;
	
	@GetMapping(value = "/list")
	public void list(Model model, Board board) {
		model.addAttribute("list", boardService.findAll(board));
		model.addAttribute("pagination", board);
	}
	
	@GetMapping(value = "/register")
	public void register() {
	}
	
	@PostMapping(value = "/register-proc")
	public String registerProc(Board board) {
		board.setRegId("test");
		try {
			boardService.save(board);
		} catch (Exception e) {
			e.printStackTrace();
			//redirect error page?
		}
		return "redirect:list";
	}
	@GetMapping(value = "/modify")
	public void modify(Model model, Board board) {
		model.addAttribute("item", boardService.findById(board));
	}
	
	@GetMapping(value = "/view")
	public void view(Model model, Board board) {
		model.addAttribute("item", boardService.findById(board));
	}
}
