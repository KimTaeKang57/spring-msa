# spring-msa
MSA를 사용한 CI/CD 개발

## 비즈니스 로직
<img width="643" alt="스크린샷 2022-09-28 오후 7 39 24" src="https://user-images.githubusercontent.com/83891837/192758778-126090ca-cd0c-4c3e-8128-bc9e82deaee2.png">

## user-service api
<img width="826" alt="스크린샷 2022-10-13 오후 7 18 18" src="https://user-images.githubusercontent.com/83891837/195571101-2a7aebad-0e84-4860-a4cc-5cdf24c47f14.png">

## order-service api
<img width="826" alt="스크린샷 2022-10-13 오후 7 21 26" src="https://user-images.githubusercontent.com/83891837/195571799-45f812a7-af84-4244-baf5-2a96cb4febc8.png">

## catalog-service api
<img width="828" alt="스크린샷 2022-10-13 오후 7 22 47" src="https://user-images.githubusercontent.com/83891837/195572086-4de34223-7342-480f-8a8b-a9e553b649fb.png">

## 사용 서비스
### Kafka -> Spring Kafka의 message Queuing 서비스를 활용하여 각각의 서비스 데이터베이스 동기화
#### 문제 1 : 주문내역을 생성할 때 해당 상품의 재고가 감소해야 함
##### Kafka의 Producers / Consumer 기능을 사용하였음
##### Producers
* 메시지 송신 API, 특정 Topic에 해당하는 메시지를 생성하는 프로세스, 메시지를 Broker에 전달(발생/Publish)
* Producers는 데이터를 그들이 선택한 Topic 으로 publish 한다

##### Consumer
* 메시지 수신 API
* Broker에게서 구독(Subscribe)하는 Topic의 메시지를 가져와 사용하는 프로세스

##### Broker
* 토픽을 기준으로 메세지 관리
* Producers와 Consumer가 만날 수 있도록 메세지를 관리하는 서버 클러스터, Producers에게서 전달받은 메세지를 Topic별로 분류한다.

<img width="1395" alt="스크린샷 2022-10-13 오후 8 11 19" src="https://user-images.githubusercontent.com/83891837/195581641-47b04aaa-d44b-4d6f-94ec-2649a9759f2b.png">

#### 문제 2 : 각각의 서비스는 독립적으로 존재하는데 같은 서비스가 동시에 작업 중일 때 각각 다른 데이터베이스를 사용하여 데이터베이스가 분리되는 문제
##### Kafka의 Connect와 Sink Connector 기능을 사용하였음
##### Connect
* Connector를 동작하게 하는 프로세서 (서버)

##### Source Connector
* data source에 담긴 데이터를 topic에 담는 역할(Producer)을 하는 connector

##### Sink Connector
* Topic에 담긴 데이터를 특정 data source로 보내는 역할(Consumer 역할)을 하는 connector

##### 같은 Order-service 지만 동시에 실행되고 있을 때 요청이 오면 각각의 데이터베이스에 데이터가 저장되어 하나의 같은 서비스임에도 같은 데이터베이스를 사용하지 않는 문제가 발생
<img width="687" alt="스크린샷 2022-10-13 오후 8 16 20" src="https://user-images.githubusercontent.com/83891837/195582546-1c7172b3-6d02-4fcf-9fed-d47af675bfb0.png">

##### Message Queuing을 진행하며 하나의 서비스에서 주문이 생성되면 다른 서비스에 Topic을 전달하여 하나의 데이터베이스에 주문내역이 저장되게 한다.

<img width="1131" alt="스크린샷 2022-10-13 오후 8 35 03" src="https://user-images.githubusercontent.com/83891837/195585904-cbd4c5ba-a092-4664-9b89-f73f451be983.png">
