package hellospring.springiscoming.repository;

import hellospring.springiscoming.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 상속하는 인터페이스만 있어도 Spring이 구현체를 자동으로 만들고 컨테이너에 자동으로 등록한다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
//  findByName은 공통 클래스에 존재하지 않으므로 따로 구현한다.
//  spring-data-jpa가 메소드 이름을 확인하고 JPQL을 알아서 만들어준다.
//  예를 들어서 findByName이면 "select m from Member m where m.name = ?"
//  결국 인터페이스를 통해 기본 CRUD를 제공하고, 메서드 이름만으로 조회 기능과 페이징 기능도 제공한다.
    @Override
    Optional<Member> findByName(String name);
}
