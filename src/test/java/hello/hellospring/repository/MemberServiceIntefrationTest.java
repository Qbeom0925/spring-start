package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.serivce.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional //롤백을 해준다.
public class MemberServiceIntefrationTest {

   @Autowired
   MemberService service;
   @Autowired
   MemberRepository repository;

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(member.getName()).isEqualTo(result.getName());
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }

}
