package kr.ac.kumoh.ce.s20180260.crudtest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
    // 테스트용 이슈페이지 연결
    @GetMapping("/issue")
    public String issues(){
        return "issue";
    }

    @GetMapping("/")
    public String lendingPage(Model model) {
        return "lending"; // lending.html로 이동
    }

    @GetMapping("/home")
    public String home() {
        return "/common/home"; // home.html이 templates 폴더에 있어야 함
    }

    @GetMapping("/login")
    public String login() { return "/common/login"; }

    @GetMapping("/signup")
    public String signUp() { return "/common/signup"; }
    @GetMapping("/room/{roomId}")
    public String room(@PathVariable String roomId) {
        return "chatroom";
    }
}
