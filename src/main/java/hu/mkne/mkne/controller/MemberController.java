package hu.mkne.mkne.controller;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public Member getFirstUser() {
        return memberService.getFirstUser();
    }

    @PostMapping("/logged-in-member")
    public Member getLoggedInMember(@RequestBody Member member) {
        return memberService.getLoggedInUser(member);
    }

    @PostMapping("/member")
    public Member getMember(@RequestBody Member member) {
        return memberService.getMemberById(member.getId());
    }
}
