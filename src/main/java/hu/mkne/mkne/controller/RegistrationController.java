package hu.mkne.mkne.controller;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RegistrationController {

    private final MemberService memberService;

    @PostMapping("/register")
    public Member register(@RequestBody Member member) {
        return memberService.register(member);
    }
}
