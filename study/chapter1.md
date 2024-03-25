
# 1.1 스프링 부트 소개
## 1.1.1 왜 스프링 부트인가
스프링부트 = 스프링 프레임워크의 오픈소스 확장 기능
현장에서 쓸 수 있을 정도의 독립 실행형 애플리케이션 설정을 알아서 해줌 
- 생산성을 높혀준다고 이해하였음. 스프링보다 더 기능이 추가 된 것~
- 개발자가 보다 **비즈니스 로직**에만 집중 할 수 있게끔 도와준다
## 1.1.2 스프링 부트는 무엇인가
스프링 - 개발자 사이에 존재하는 계층![[스크린샷 2024-03-25 오후 4.51.12.png]]

- 2014년 출시. 오래되지 않은 프레임워크
- 개발자가 보다 비즈니스 로직에만 집중 할 수 있게끔 도와준다.
## 1.1.3 스프링 부트 핵심 기능
- 빠른 시동
- 자동 구성
- 미리 정의된 방식
- 독립 실행형
- 실제 서비스 환경에 사용 가능

## 1.1.4 스프링 부트 컴포넌트

> - 컴포넌트란? 
> 여러 개의 프로그램 함수들을 모아 하나의 특정한 기능을 수행할 수 있도록 구성한 작은 기능적 단위
> 재사용이 가능한 각각의 독립된 모듈



- 스프링부트 컴포넌트 종류![[스크린샷 2024-03-25 오후 5.02.14.png]]
	- spring-boot: 스프링부트 기본 컴포넌트
	- spring-boot-autoconfigure: 빈 생성 
	- spring-boot-starter: 의존 관계 기술서 
	- spring-boot-CLI: dependency를 메이븐이나 그레이들에서 안하고 그루비 코드를 컴파일할 수 있는 개발자 친화적 명령형 도구 라는데 난 잘 모르겠음
	- spring-boot-actuator: 스프링부트 애플리케이션을 모니터링하고 감시할 수 있는 액추에이터 
	- spring-boot-actuator-autoconfigure:
	- spring-boot-test
	- spring-boot-test-autoconfigure:
	- spring-boot-loader:
	- spring-boot-devtools:


# 1.2 코드 예제
## 1.2.1 ~~메이븐~~ vs. 그레이들

메이븐 
![[Pasted image 20240325171739.png]]

그레이들 
![[Pasted image 20240325171844.png]]


> 왜 메이븐에서 그레이들로 갈아탈까?
> 
> 그레이들이 가독성이 좋기 때문! 
> 
> 메이븐은 XML 형식인데, 계층 구조를 타고 타고 depth가 깊어지는 형식으로 가는 문서 형식이라서 가독성이 좋지 않다. 

## 1.2.2 자바 vs. 코틀린

자바 코틀린 혼용이 한 프로젝트 안에서 가능하다고는 하나, 빈 생성이 안되어서 하나의 컨트롤러 단위로 혼용이 된다고 알고 있다. 

~~코틀린 언어에 대한 수요가 많다면 스터디 개설을 고려해보겠다. 나는 코틀린을 좋아한다. 

## 1.2.3 데이터베이스 지원

- H2 인메모리 SQL 데이터베이스 
- MySQL이나 PostgreSQL을 사용해본 경험이 있음

## 1.2.4 롬복
어노테이션을 통해 POJO 클래스를 정의할 수 있게 도와준다.

> POJO(Plain Old Java Object)란?
> 즉, **Java로 생성하는 순수한 객체**
> 
>토비 왈 **"진정한 POJO란 객체지향적인 원리에 충실하면서, 환경과 기술에 종속되지 않고 필요에 따라 재활용될 수 있는 방식으로 설계된 오브젝트이다"**

```java
public class UserDTO {
	private String userName; 
	private String userId; 
	private String userPassword; 
	
	public String getUserName() { return userName; } 
	
	public void setUserName(String userName) {
	 this.userName = userName; 
	 } 
	 
	public String getUserId() { return userId; } 
	
	public void setUserId(String userId) { 
	this.userId = userId; 
	} 
	
	public String getUserPassword() { 
		return userPassword; 
		}
		 
	public void setUserPassword(String userPassword) { 
		this.userPassword = userPassword; 
		} 
	}

```

게터 세터를 다 구현한 모습이다. 하지만, 롬복 어노테이션을 통해 짧게 줄일 수 있다. (사견이지만, 이게 다 자바에 데이터 클래스가 없어서 발생한 일이 아닐까.. 코틀린에는 데이터 클래스가 있는데!)

그래서 롬복은? POJO 객체의 생성자, getter, setter, toString을 어노테이션 하나만 추가하면 자동으로 추가해준다!! 와아
![[스크린샷 2024-03-25 오후 7.33.59.png]]

**@Data**
```java
package com.example.demo.dto; 


public class Member { 

	private Integer id; 
	private String name; 
	private Integer age; 
	private String dept; 
	
	public Member() { 
	super(); // TODO Auto-generated constructor stub } 
	
	public Integer getId() { return id; } 
	
	public void setId(Integer id) { this.id = id; } 
	
	public String getName() { return name; } 
	
	public void setName(String name) { this.name = name; } 
	
	public Integer getAge() { return age; } 
	
	public void setAge(Integer age) { this.age = age; } 
	
	public String getDept() { return dept; } 
	
	public void setDept(String dept) { this.dept = dept; } @Override 
	
	public String toString() { return "Member [id=" + id + ", name=" + name + ", age=" + age + ", dept=" + dept + "]"; } }
```


@Data를 붙이면 게터세터, 기본 생성자, toString이 붙는다. 
```java 
package com.example.demo.dto; 

import lombok.Data; 
@Data 
public class Member { 
	private Integer id; 
	private String name; 
	private Integer age; 
	private String dept; 
	}
```

**@AllArgsConstructor**
@Data로는 파라미터를 가지지 않은 기본 생성자를 만든다.
하지만 모든 변수를 파라미터로 받는 생성자가 필요할 수 있다.
이 때에 @AllArgsConstructor을 사용한다.
(단, 이 경우 @Data가 자동 생성한 기본 생성자는 사라진다.
 양쪽을 모두 사용하고 싶다면 @NoArgsConstructor와 @AllArgsConstructor을 함께 사용해야한다.)

```java 
package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
	private Integer id;
	private String name;
	private Integer age;
	private String dept;
}
```


>POJO가 아닌 것
>
>순수 자바가 아니라 WindowAdapter를 상속 받기 때문에, WindowAdapter가 바뀌면 안에 내용도 바뀜. 종속적이다.
```java
public class WinExam extends WindowAdapter { 
	@Override public void windowClosing(WindowEvent e) { 
	System.exit(0); 
	} 
}
```


롬복으로 생성된 거나 직접 구현해서 작성된거나 작동은 동일하게 된다.

롬복은 서드파티인데, 서드파티를 사용하고 싶지 않다면 자바 레코드도 있다고 한다. 




# 1.3 스프링 부트 시작하기

## 1.3.1 첫 번째 스프링 부트 프로젝트

https://start.spring.io/


## 1.3.2 ~~스프링 부트 프로젝트 구조~~(메이븐 제외, 18 페이지 부터 정리)

- 메인 클래스![[스크린샷 2024-03-25 오후 5.39.50.png]]

1. main()
	별다른 war, ear 파일을 만들 필요 없이, main 메소드만 실행한다면 로컬에서 웹 애플리케이션을 돌려볼 수 있다. 
	기본적으로 서블릿 기반 웹 애플리케이션은 아파치 톰캣이나 제티와 같은 서블릿 컨테이너 위에서만 실행할 수 있다.

	원래대로 라면 톰캣 서버 위에 우리가 만든 웹 애플리케이션을 올려야 작동하지만, 스프링부트와 함께라면! 이야기는 달라진다. 

	- 요약
		main()을 통해 스프링부트 애플리케이션을 실행하면 스프링부트가 내장된 기본 서블릿 컨테이너인 톰캣 시작하고, 그 위에서 우리의 웹 애플리케이션을 실행한다. 
2. @SpringBootAppLication 사용
	아래의 3 어노테이션을 하나로 줄여 사용할 수 있다. 
	1. @EnableAutoConfiguration: jar 파일을 바탕으로 애플리케이션을 자동으로 구성해주는 스프링부트 자동 구성 기능을 활성화 합니다. 
	2. @ComponentScan: 애플리케이션에 있는 스프링 컴포넌트(빈, 컴포넌트 등)를 탐색하고 그것들을 자바 빈으로서 스프링으로 관리한다. 라이프 사이클도 따르며, 루트에서 시작해서 하위 패키지까지 모두 탐색한다.
		>빈이란? 
		>
		>빈(Bean)은 스프링 컨테이너에 의해 관리되는 재사용 가능한 소프트웨어 컴포넌트이다. 즉, 스프링 컨테이너가 관리하는 자바 객체를 뜻하며, 하나 이상의 빈(Bean)을 관리한다.
		>
		>**빈은 인스턴스화된 객체를 의미하며, 스프링 컨테이너에 등록된 객체를 스프링 빈이라고 한다.**
		>@Bean 어노테이션을 통해 메서드로부터 반환된 객체를 스프링 컨테이너에 등록한다. 빈은 클래스의 등록 정보, Getter/Setter 메서드를 포함하며, 컨테이너에 사용되는 설정 메타데이터로 생성된다.
		
	3. @SpringBootConfiguration: 스프링부트 애플리케이션 설정을 담당하는 클래스에 이 어노테이션을 붙임. -> 이 어노테이션 때문에 메인 클래스가 반드시 Root에 위치해야함. 그래야 루트부터 시작해서 다른 스프링 어노테이션이 붙어있는 컴포넌트들을 찾아서 로딩할 수가 있다. 
	
3. SpringApplication클래스의 역할
	run()이 실행 될 때 수행되는 작업들
	1. 클래스 패스에 있는 라이브러리를 기준으로 ApplicationContext 클래스 인스턴스를 생성한다.
	2. CommandLinePropertySource를 등록해서 명령행 인자를 스프링 프로퍼티로 읽어 들인다.
	3. 1에서 생성한 ApplicationContext를 통해 모든 싱글톤 빈을 로딩한다.
	4. 애플리케이션에 설정된 ApplicationRunners와 commandRunner를 실행한다.

	>ApplicationContext를 싱글톤으로 만들어야한다. 
	싱글톤은 하나의 클래스에 오직 하나의 인스턴스만 가지는 패턴이고, 싱글톤을 하는 이유는, 간단히 말해서 객체를 생성할 때 비용이 들기 때문에, 그 비용을 줄이기 위해  하나의 인스턴스를 가지고 돌려 쓰려고 한다. 그러기 위해서 의존성 주입을 한다. 
	
	SpringApplication클래스는 클래스 패스에 있는 jar 의존 관계를 바탕으로  ApplicationContext 인스턴스를 생성한다.
	
스프링 부트 웹 애플리케이션은 서블릿/리액티브 타입 중에 하나다.
스프링 클래스 패스에 있는 클래스를 바탕으로 어떤 타입인지 유추할 수 잇다.

 ApplicationContext를 로딩할 때
 - 서블릿 기반 일 때 AnnotationConfigServletWebServerApplicationContext 클래스 인스턴스 생성
 - 리액티브 일 때 AnnotationConfigReactiveWebServerApplicationContext 클래스 인스턴스 생성
 - 둘 다 아니면, AnnotationConfigApplicationContex 클래스 인스턴스 생성

SpringApplication클래스안에 있는 main()은 정적 메서드이다. run()이 유용하긴 하지만, 개발자의 의도하는 바가 특별히 있다면, SpringApplication클래스 인스턴스를 직접 생성해서 동작되게 할 수도 있다. 
![[스크린샷 2024-03-25 오후 7.30.04.png]]
직접 인스턴스를 생성하고, 리액티브 타입으로 설정하는 코드이다.


- 애플리케이션 정보 관리 
	src/main/resources 경로에 application.yml을 올린다.
	
	주로 어떤 포트를 쓰는지, base url은 뭔지, jwt 키값, 토큰 유효 시간 등을 담아놓는다. 
	
	![[스크린샷 2024-03-25 오후 7.31.07.png]]
	애플리케이션 설정을 담는데, 보통 gitinore해서 사용하거나, 특정 변수만 환경변수로 빼서 레포지토리에 올려서 사용할 수가 있다.
	
	yml에 있는 내용을 변경하고서 팀원들과 공유하지 않는다면, 왜 안되? 상황이 발생할 수 있다. 빌드가 안되기 때문!
## 1.3.3 실행 가능한 JAR 파일 

서버 배포를 할 때 올리는게 jar 파일이다.

java -jar 명령어를 통해 애플리케이션 실행을 할 수 있다. 

## 1.3.4 JAR 파일 구조

![[스크린샷 2024-03-25 오후 7.41.39.png]]
- META-INF: 매니패스트가 있다. 그 안에는 주요 파라미터인 MainClass와 StartClass가 들어있다. 
- 스프링부트 로더 컴포넌트: 실행가능한 파일을 로딩하는데 사용하는 로더의 구현체
- BOOT-INF/classes: 컴파일된 모든 클래스
- BOOT-INF/lib: 의존성 


## 1.3.5 스프링 부트 애플리케이션 종료


jar 파일을 프롬프트에서 종료하면 종료된다.
혹은 해당 프로세스 종료

안전 종료 설정 (스프링부트 2.3.0부터 도입 )
```
server.shutdown=graceful //기본 값은 immediate 즉시 종료
spring.lifecycle.timeout-per-shutdown-phase=1m //기본값은 30s 

```



# 1.4 스프링 부트 기타 개념

생산성을 높이기 위해 여러 도구가 존재한다.
## 1.4.1 스프링 부트 스타트업 이벤트
## 1.4.2 스프링 부트 애플리케이션 이벤트 감지
## 1.4.3 커스텀 스프링 부트 스타터
## 1.4.4 커스텀 자동 구성
## 1.4.5 실패 분석기
## 1.4.6 스프링 부트 액추에이터
## 1.4.7 스프링 부트 개발자 도구