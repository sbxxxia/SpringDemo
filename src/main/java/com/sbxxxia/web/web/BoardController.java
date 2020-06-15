package com.sbxxxia.web.web;

import com.sbxxxia.web.entity.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 사용자의 URL 요청을 처리하겠다.
@RestController
@Controller
// 공통적으로 사용되는 URL이라면 RequestMapping을 통해 묶어버릴 수 있다.
// 즉 자동으로 URL이 /board/~~~ 형태로 만들어주게 된다는 의미
@RequestMapping("/board")
public class BoardController {
    // static이 붙어있으면 메모리에 상주하고 있게 된다.
    // 쉽게 생각하자면 메모리에 통채로 박혀 있다고 생각하면 된다.
    // 결국 data 영역에 상시 배치되어 있는 것이다.

    // static으 남발하는 것은 자제해야 한다.
    // 이유는 메모리에 상주하게 되어 그만큼 메모리를 소비하므로
    // 대규모 시스템이 동작할 때 static의 남발은 성능 저하를 유발한다.

    // new라는 것은 Heap에 할당하는 것이고
    // Heap에 할당한다는 것은 동적으로 할당을 한다는 것이므로
    // 실제 Run-Time에 실행하면서 할당을 하게되어 속도가 느리다.
    // 그러나 static으로 박아 놓은 것은 메모리는 낭비하지만
    // 프로그램이 동작할 때 초기부터 세팅되어 있는 것이라
    // 별도의 동적 할당 메커니즘이 없어 속도 측면에서 이점을 얻을 수 있다.
    static Logger log = LoggerFactory.getLogger(BoardController.class);

    // 사용자의 URL 요청 중 /test 로 들어오는 녀석이 있다면 나한테 보내줘~
    @GetMapping("/test")
    public String test(Model model) {
        // HTML 태그 중 템플릿으로 받을 수 있는 태그가 있는데
        // 해당 태그에서 message를 받을 수 있게 해주는 작업
        // 즉 여기서 message는 일종의 key값이라고 생각하면 되고
        // 그 뒤쪽에 있는 내용이 실제 value라고 생각하면 되겠다.
        model.addAttribute("message","Hello Spring-Vue-MySQL-WebGL");

        // 이것은 Thymeleaf에 의해서
        // 자동으로 resources/templates/test.html로 정보를 출력하겠다는 의미가 된다.
        return "test";
    }

    @GetMapping("/")
    public String home(){
        log.info("home");

        return "index";
    }

    // @RestController에 의해 리턴하는 것이 json화 되어 넘어간다.
    // Awesome JSON Viewer를 통해 살펴보면
    // 실제 여기서 처리되는 데이터 값들을 눈으로 확인할 수 있다.
    // 우리가 만든 Member 엔티티가 객체화되어 json으로 넘어가는 것을 볼 수 있다.
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Member retJSONMember(){
        log.info("retJSONMember");

        Member member = new Member();

        return member;
    }

    // List인 경우에는 데이터가 배열처럼 들어오게 되고
    // 나머지는 위의 경우와 동일하다.
    @GetMapping("/memberlist")
    public List<Member> retListJSONMember(){
        log.info("retListJSONMember");

        List<Member> list = new ArrayList<Member>();

        Member mem1 = new Member();
        list.add(mem1);

        Member mem2 = new Member();
        list.add(mem2);

        return list;
    }

    // Map을 기반으로 하기 때문에
    // 단순히 키값에서 특정한 값으로 접근한다기 보다는
    // 밸류값으로 지정된 녀석이 동일한 해쉬인지 리스트인지 파악하는 것이 중요하다.
    // 넘어온 정보가 리스트라면 바로 접근이 가능할 것이고
    // 해쉬라면 키값을 한 번 더 타야할 것이다.
    @GetMapping("/memberMap")
    public Map<String, Member> retMapJSONMember() {
        log.info("retMapJSONMember");
        Map<String, Member> map = new HashMap<String, Member>();
        Member mem1 = new Member();
        map.put("key1", mem1);
        Member mem2 = new Member();
        map.put("key2", mem2);
        return map;
    }

    // ResponseEntity는 우리가 처리한 Entity(엔티티)를 가지고
    // 현재 HTTP의 상태에 대한 응답을 처리할 수 있도록 도와주는 클래스
    // ResponseEntity<엔티티> <--- 이와 같은 형식으로 많이 사용함
    // DB의 경우: 서비스 -> 저장소 -> ReponseEntity<엔티티>에 배치할 엔티티를 가져옴
    // 엔티티: 데이터(어떤 경우에도 오염되어서는 안된다)
    // 여기서 오염의 정의: 데이터가 정해졌으면
    // 이 데이터를 가공하려고 하는
    // 기타 여러가지 것들에 의해 엔티티가 변경되어서는 안된다는 의미
    // Generic을 요약하자면 데이터 타입에 종속적이지 않게 만들겠다는 뜻
    @GetMapping("/responseTest")
    public ResponseEntity<Void> responseTest() {
        log.info("Response Test");
        // HttpStatus.OK <---- 성공 응답 200번
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // 사용자의 URL 요청 중 /board/register를 보내면 registerForm() 메서드가 동작
    @GetMapping("/register")
    public String registerGetForm() {
        log.info("registerGetForm");

        return "register";
    }

    @PostMapping("/register")
    public String registerPostForm() {
        log.info("registerPostForm");

        return "register";
    }

    @GetMapping("/modify")
    public String modifyGetForm() {
        log.info("modifyForm");

        return "modify";
    }

    @PostMapping("/modify")
    public String modifyPostForm() {
        log.info("modifyForm");

        return "modify";
    }

    @PostMapping("/remove")
    public String removeForm(){
        log.info("removeForm");

        return "remove";
    }

    @GetMapping("/list")
    public String listForm() {
        log.info("listForm");

        return "list";
    }

    @GetMapping("/read")
    public String readForm(){
        log.info("read");

        return "read";
    }

    // 우리가 특정 게시글을 읽을 때
    // 1번째 글, 100번째 글, 1000번째 글
    // 각각을 기억하고 URL에 /board/read/100을 할 수 있는 것이 아니다.
    // 그러므로 선택한 제목의 내용을 읽고자 할 때
    // 해당 글에 대한 URL 처리를 아래와 같이 가변적으로 할 필요가 있다.
    // 이런 경우 가변적인 숫자를 처리하기 위한 것이 @PathVariable 이다.
    // 즉 URL {boardNo}으로 들어온 것을 @PathVariable이 받아서 int boardNo로 준다는 의미
    @RequestMapping(value = "/read/{boardNo}")
    public String readForm(@PathVariable("boardNo") int boardNo) {
        log.info("readForm: " + boardNo);

        return "read";
    }

}
