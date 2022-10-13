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
####문제 1 : 주문내역을 생성할 때 해당 상품의 재고가 감소해야 함
####문제 2 : 각각의 서비스는 독립적으로 존재하는데 같은 서비스가 동시에 작업 중일 때 각각 다른 데이터베이스를 사용하여 데이터베이스가 분리되는 문제
