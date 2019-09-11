package hu.mkne.mkne.repository;

import hu.mkne.mkne.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
