package com.example.planner.domain.user;

import com.example.planner.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // Getter 생성
@NoArgsConstructor // Default 생성자
@Entity // Entity임을 명시
@Table(name = "users") // 테이블명 설정
public class User extends BaseTimeEntity { // BaseTimeEntity 상속
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false) // nullable하지 않도록 설정
    private String name;
    
    @Column(nullable = false)
    private String email;
    
    
    @Enumerated(EnumType.STRING) // Enum 값 저장
    @Column(nullable = false)
    private Role role;
    
    @Builder // Builder Pattern 사용
    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
    
    // update 함수 구현
    public User update(String name) {
        this.name = name;
        
        return this;
    }
    
    
    public String getRoleKey() {
        return this.role.getKey();
    }
}