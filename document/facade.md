# 퍼사드 <small>(Facade)</small> 패턴

퍼사드 패턴은 외관 패턴으로도 불리며 Facade는 *건물의 정면*을 의미합니다.

퍼사드는 클래스 라이브러리 같은 어떤 소프트웨어의 다른 커다란 코드 부분에 대한 간략화된 인터페이스를 제공하는 객체입니다.

다양한 구현 및 접근 방식에서 Facade는 어댑터 또는 추상 팩토리와 유사할 수 있습니다.

퍼사드는 다른 클래스 또는 클래스 패밀리와의 상호 작용을 단순화하는 것이 목표입니다.

- 우리는 *간략화*에 대해 생각할 때 일반적으로 **어댑터** 디자인 패턴을 생각합니다.
- 우리는 *클래스 패밀리*를 생각할 때 일반적으로 **추상 팩토리** 디자인 패턴을 생각합니다.

모든 혼란은 일반적으로 여기에서 비롯되는데, 더 잘 이해하기 위해 추상 팩토리 패턴에서 사용한 전략 게임을 예시로 살펴보겠습니다.

## 간단하게!

`loadGame()` 메서드를 구현하고 싶다고 가정합니다. 이 메서드는 우리가 이미 생성한 파일을 사용하거나, 최소한 다음이 필요합니다.

1. 최소 2개의 HQ가 생성될 것<small>(그렇지 않으면 게임은 이미 승리한 것)</small>
2. 각 HQ는 건물을 생산해야 함
3. 각 건물은 가지고 있던 유닛을 생산해야 함
4. 모든 유닛은 게임이 저장되었을 때 있던 위치로 순간이동해야 함
5. 유닛에 명령이 내려진 경우 실행을 재개해야 함

