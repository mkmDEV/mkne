package hu.mkne.mkne.service;

import hu.mkne.mkne.model.Member;
import hu.mkne.mkne.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public Member getFirstUser() {
        return memberRepository.findById(1L).orElse(null);
    }

    public Member register(Member member) {
        Member newMember = null;

        Optional<Member> optionalMember = this.memberRepository.findByEmail(member.getEmail());
        if (optionalMember.isEmpty()) {
            newMember = Member.builder()
                    .firstName(member.getFirstName())
                    .lastName(member.getLastName())
                    .email(member.getEmail())
                    .username(member.getUsername())
                    .password(passwordEncoder.encode(member.getPassword()))
                    .avatar(member.getAvatar())
                    .roles(Set.of("USER"))
                    .regDate(member.getRegDate())
                    .build();
            this.memberRepository.save(newMember);
        }
        return newMember;
    }

    public Member getLoggedInUser(Member member) {
        return this.memberRepository.findByEmail(member.getEmail()).orElse(null);
    }
}
