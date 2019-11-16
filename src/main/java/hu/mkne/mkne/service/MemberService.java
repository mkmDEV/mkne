package hu.mkne.mkne.service;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member getFirstUser() {
        return memberRepository.findById(1L).orElse(null);
    }
}
