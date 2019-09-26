package hu.mkne.mkne.controller;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Member getFirstUser() {
        return memberService.getFirstUser();
    }
}
