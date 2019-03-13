# Spring-boot-CRUD

spring boot를 이용한 CRUD Board Project

Architecture  

> VIEW - CONTROLLER - SERVICE - REPOSITORY - DOIAMIN - DATABASE







## JPA 

Java Persistent API. Java ORM 표준 기술. 구현체로는 Hibernate를 사용하였다.

테이블 생성,변경 관리가 쉬운 장점이 있으며 CRUD작업에 있어 빠른 속도를 가지고 있음.
하지만 러닝커버가 높은 단점을 가지고 있다.



**JPA Annotation**

1.@Id

> PK를 가지는 변수에 선언한다. @GeneratedValue 로 해당 Id값을 어떻게 자동 생성 할지 전략을 선택한다.

2.@Table

> 별도의 이름을 가지는 DB테이블과 매핑 가능함. 기본적으로 @Entity로 선언된 클래스의 이름과 일치하는것을 매핑함. 다르다면 직접 명시해주어야함

3.@Column

> 지정한 멤버 변수와 DB의 컬럼명을 다르게 주고 싶을 때에 @Column(name="~~~") 형식으로 작성해준다



**@OneToMany / @ManyToOne**  

```java
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue
    private Long idx;

    //불필요한 조회를 막으려면 LAZY 사용 항상 가져오면 EAGER
    //관계의 주체가 되는 쪽에서 mappedBy
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Board> boards = new ArrayList<>();

    private String username;
    private String userid;
    @Column(name = "phone_num")
    private String phoneNum;
}

@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue
    private Long idx;

    private String title;
    private String content;
    
    @Temporal(TemporalType.DATE)
    private Date uploadDate;

    @ManyToOne
    @JoinColumn(name = "user_idx" , nullable=false) //실제 FK 컬럼명
    @JsonManagedReference
    private User user; // 이 보드가 어떤 사용자에 의해 작성되었는가

}

```



## Repository

JpaRepository 인터페이스를 이용해서 메소드를 호출하는 것 만으로도 데이터 검색이 가능함.

따로 @Repository를 추가할 필요가 없다.



**@BoardRepository.java **

```java
public interface BoardRepository extends JpaRepository<Board , Long>{
    List<Board> findAll();
    Optional <Board> findById(Long idx);
    <B extends Board> B save(B board);
    void deleteById(Long index);
}
```



method로 save() , findOne() , findAll() , count() , delete()를 제공하고 **규칙에 맞는 메서드**를 추가하면 직접 조회 기능을 추가할 수 있다.

```
Optional<User> findByuserid(String userid);
```

findBy 

> 쿼리를 요청하는 메서드.





## Service

실제로 필요한 Service를 작성한다.

**BoardService.java**

```java
@Service
public class BoardService {
    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }
    public Board findById(Long idx){ 
        return boardRepository.findById(idx).get(); 
    }
   
    public List<Board> findAll(){return boardRepository.findAll();}
    public void saveBoard(Board board){boardRepository.save(board);}
    public void deleteById(Long idx){ boardRepository.deleteById(idx); }

}
```

User/Board 각각 필요한 Service를 Repository에 기반하여 작성함. 다음으로는 서비스들에 대한 TDD를 통해 기능 검증을 한다.







## TDD

 **Test Driven Development** : 테스트 주도 개발.

테스트를 먼저 만들고 테스트를 통과하기 위한 것을 짜는 것이 원칙이다. 처음 해보는 프로그램 주제에 나에 대한 불확실성이 높다면 실행하는 것이 좋다.

```java
    @Before
    public void setup(){
        user = new User();
        board = new Board();

        user.setUserid("npay");
        user.setUsername("kimyumin");
        user.setPhoneNum("010-1234-5678");


        board.setTitle("test");
        board.setContent("content test");
        Date d = new Date();
        board.setUploadDate(d);

        board.setUser(user);
        user.addBoard(board);

    }
```

//testclass method들이 테스트되기 전에 실행 되도록 지정하는 annotation

Test 일부분

```java
    @Test
    public void insert(){
        userService.saveUser(user);
        boardService.saveBoard(board);
        assertThat((board.getUser()).getUsername()).isEqualTo("kimyumin");
    }

```

**assertThat**을 이용하여 결과값을 검증함.

> assertThat(T actual, Matcher<? super T> matcher) 의 형태로 두 값을 비교한다. 다양한 매쳐들을 이용하여 검증할 수 있다.
>
> 참고 : http://sejong-wiki.appspot.com/assertThat



## Controller

**@RestContorller vs @Controller**

HTTP Respose Body가 생성되는 방식의 차이이다. View 화면을 리턴하는 @Controller와 객체를 반환시 데이터가 JSON타입으로 직접 리턴하게 되는 @Restcontroller가 존재한다.



**UserController.java**

```java
@RequestMapping("/board")
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/ushowAll")
    public String list(Model model){
        List<User> list = userService.findAll();
        model.addAttribute("list",list);
        return "user/userList";
    }
```

UserList의 출력을 위해 Controller를 사용하여 VIEW화면을 리턴하게 함. 

model.addAttributd(String name, Object value); 

> value객체를 name이름으로 추가하고 뷰 코드에서는 지정한 이름을 통해 value를 사용한다.



VIEW파일을 통해 시각적으로 데이터들을 표현 한 뒤 Project 마무리한다. 






