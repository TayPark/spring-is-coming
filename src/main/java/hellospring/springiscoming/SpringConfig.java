package hellospring.springiscoming;

import hellospring.springiscoming.repository.MemberRepository;
import hellospring.springiscoming.repository.MemoryMemberRepository;
import hellospring.springiscoming.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
