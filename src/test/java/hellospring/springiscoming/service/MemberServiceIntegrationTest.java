package hellospring.springiscoming.service;

import hellospring.springiscoming.domain.Member;
import hellospring.springiscoming.repository.MemberRepository;
import hellospring.springiscoming.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // TC에 해당 어노테이션이 붙었을 경우 TC가 종료되면 쿼리를 롤백함. 커밋하려면 @Commit 사용
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member targetMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(targetMember.getName());
    }

    @Test
    void 중복_회원_가입() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("Already Exists");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            Assertions.assertThat()
//        }

        //then
    }
}