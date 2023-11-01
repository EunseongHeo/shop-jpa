package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            /* 데이터 중심 설계의 문제점
                • 현재 방식은 객체 설계를 테이블 설계에 맞춘 방식
                • 테이블의 외래키를 객체에 그대로 가져옴
                • 객체 그래프 탐색이 불가능
                • 참조가 없으므로 UML도 잘못됨
             */

            Order order = em.find(Order.class, 1L);
            Long memberId =  order.getMemberId();
            Member member = em.find(Member.class, memberId);

//            - 식별자가 있기 때문에 이를 통해 필요한 값을 얻어오는 데에 불편함이 있음
//            - 그래서 이어서 학습할 것이 연관관계 매핑

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
