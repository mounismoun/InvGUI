# InvGUI

[English](#english) | [한국어](#한국어)

---

# English

## InvGUI - Inventory GUI Framework

This is a library that helps you easily create a UI that utilizes inventory when developing plugins.

- Specify inventory size
- Coordinate-based item and button placement
- Play click sound
- Inventory UI page


### 사용 예시
```kotlin
val menu = gui(plugin, "Main menu",  3){

            fill(pane())

            button(4, 1, item(Material.DIAMOND){
                name("1Page click test")
                lore("This is click test")
            }){
                onClick {
                    player.sendMessage("click!")
                    sound(Sound.BLOCK_BELL_USE, 1.0f, 1.0f)
                }
            }
            button(4, 2, item(Material.BARRIER){
                name("exit")
            }){
                onClick {
                    player.closeInventory()
                }
            }
            button(8, 2, item(Material.MAGENTA_GLAZED_TERRACOTTA){
                name("nextPage")
            }){
                onClick {
                    session.nextPage()
                    
                }
            }



            page {
                fill(pane())

                button(4, 1, item(Material.DIAMOND){
                    name("2Page Click test")
                    lore("This is click test")
                }){
                    onClick {
                        player.sendMessage("click!")
                        sound(Sound.BLOCK_BELL_USE, 1.0f, 1.0f)
                    }
                }

                button(4, 2, item(Material.BARRIER){
                    name("exit")
                }){
                    onClick {
                        player.closeInventory()
                    }
                }

                button(0, 2, item(Material.MAGENTA_GLAZED_TERRACOTTA){
                    name("prevPage")
                }){
                    onClick {
                        session.prevPage()
                    }
                }
            }

            onClose {
                player.sendMessage("closed gui")
            }
        }
```

## 📦 Installation

This library is available on **Maven Central**.

- **GroupId:** `io.github.mounismoun`
- **ArtifactId:** `InvGUI`
- **Version:** `1.0.0`

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.mounismoun:InvGUI:1.0.0")
}
```

### Gradle (Groovy DSL)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.mounismoun:InvGUI:1.0.0'
}
```

### Maven

```xml
<dependency>
  <groupId>io.github.mounismoun</groupId>
  <artifactId>inv-gui</artifactId>
  <version>1.0.0</version>
</dependency>
```



Build using `shadowJar` and place the JAR inside your `plugins/` folder.

---

# 한국어

## InvGUI - 인벤토리 GUI 프레임워크

플러그인 개발시에 인벤토리를 활용한 UI를 손쉽게 만들수있도록 도와주는 라이브러리 입니다.

- 인벤토리 크기 지정
- 좌표 기반 아이템, 버튼 배치
- 클릭 사운드 재생
- 페이지 기능


### 사용 예시
```kotlin
val menu = gui(plugin, "메인 메뉴",  3){

            fill(pane())

            button(4, 1, item(Material.DIAMOND){
                name("1페이지 클릭테스트")
                lore("클릭테스트 입니다.")
            }){
                onClick {
                    player.sendMessage("클릭됨.")
                    sound(Sound.BLOCK_BELL_USE, 1.0f, 1.0f)
                }
            }
            button(4, 2, item(Material.BARRIER){
                name("메뉴 나가기")
            }){
                onClick {
                    player.closeInventory()
                }
            }
            button(8, 2, item(Material.MAGENTA_GLAZED_TERRACOTTA){
                name("다음페이지")
            }){
                onClick {
                    session.nextPage()
                    
                }
            }



            page {
                fill(pane())

                button(4, 1, item(Material.DIAMOND){
                    name("2페이지 클릭테스트")
                    lore("클릭테스트 입니다.")
                }){
                    onClick {
                        player.sendMessage("클릭됨.")
                        sound(Sound.BLOCK_BELL_USE, 1.0f, 1.0f)
                    }
                }

                button(4, 2, item(Material.BARRIER){
                    name("메뉴 나가기")
                }){
                    onClick {
                        player.closeInventory()
                    }
                }

                button(0, 2, item(Material.MAGENTA_GLAZED_TERRACOTTA){
                    name("이전페이지")
                }){
                    onClick {
                        session.prevPage()
                    }
                }
            }

            onClose {
                player.sendMessage("gui닫음")
            }
        }
```

## 📦 설치 방법

이 라이브러리는 **Maven Central**에 배포되어 있습니다.

- **GroupId:** `io.github.mounismoun`
- **ArtifactId:** `InvGUI`
- **Version:** `1.0.0`

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.mounismoun:InvGUI:1.0.0")
}
```

### Gradle (Groovy DSL)

```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'io.github.mounismoun:InvGUI:1.0.0'
}
```

### Maven

```xml
<dependency>
  <groupId>io.github.mounismoun</groupId>
  <artifactId>InvGUI</artifactId>
  <version>1.0.0</version>
</dependency>
```

플러그인 프로젝트를 `shadowJar`로 빌드하세요.

---