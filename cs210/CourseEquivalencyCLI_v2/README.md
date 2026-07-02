# Course Equivalency Lookup CLI

读取一个或多个课程等价 CSV 文件，然后支持双向查找。

例子：

- 输入 `UW + CSE 142`，可以查到 `Bellevue College + CS 210`
- 输入 `BC + CS 210`，也可以反向查到 `UW + CSE 142`
- 输入 `SeattleU + CSSE 151`，可以查到 `BC + CS 210`
- 输入 `UWB + B BIO 180`，可以查到 `BC + BIOL& 212`

项目没有 GUI，只使用命令行、class、object、`ArrayList`、`HashMap`、文件读取、异常处理和字符串处理。

---

## 1. 这几个 CSV 能不能用？

可以用。你上传的这些文件列名和列顺序一致：

```csv
Transfer Institution,Transfer Course,Receive Institution,Equivalent Course,Comments,Begin Date,End Date
```

本项目已经把它们放进 `data/` 文件夹，并且改成了更简单的名字：

```text
CourseEquivalencyCLI/
├── data/
│   ├── UW.csv
│   ├── UWB.csv
│   ├── UWT.csv
│   └── SEATTLEU.csv
└── src/
    ├── App.java
    ├── Course.java
    ├── CourseEquivalency.java
    ├── CsvEquivalencyReader.java
    └── EquivalencySearchEngine.java
```

---

## 2. 如何运行

进入 `src` 文件夹：

```bash
cd CourseEquivalencyCLI/src
javac *.java
```

只读取 UW 一个文件：

```bash
java App ../data/UW.csv
```

一次读取所有学校：

```bash
java App ../data/UW.csv ../data/UWB.csv ../data/UWT.csv ../data/SEATTLEU.csv
```

如果你的 CSV 文件名有空格，需要加引号：

```bash
java App "../SEATTLEU_EQUIVALENCY_LIST.xlsx - EQInMyList.csv"
```

---

## 3. 程序菜单

运行后会看到：

```text
===== Course Equivalency Lookup =====
1. Search by school and course
2. Show schools in loaded data
3. Exit
```

选 `1`：查课.
选 `2`：显示当前加载了哪些学校.
选 `3`：退出。

---

## 4. 查询例子

### Example 1: BC 反查 UW / SeattleU / UWT

如果你一次加载了所有学校，输入：

```text
School: BC
Course: CS 210
```

可能会看到多条结果，因为很多学校都有课程等价 BC 的 `CS 210`。

### Example 2: UW 查 BC

```text
School: UW
Course: CSE 142
```

结果：

```text
Transfer side:   UNIVERSITY OF WASHINGTON - CSE 142 COMPUTER PROGRAMMING I (4)
Equivalent side: BELLEVUE COLLEGE - CS 210 FUNDAMENTALS OF COMPUTER SCIENCE I (5)
```

### Example 3: Seattle University 查 BC

```text
School: SeattleU
Course: CSSE 151
```

结果：

```text
Transfer side:   SEATTLE UNIVERSITY UNDERGRADUATE - CSSE 151 FUNDAMENTALS OF COMPUTER SCIENCE I (5)
Equivalent side: BELLEVUE COLLEGE - CS 210 FUNDAMENTALS OF COMPUTER SCIENCE I (5)
```

### Example 4: UWB 查 BC

```text
School: UWB
Course: B BIO 180
```

结果：

```text
Transfer side:   UNIVERSITY OF WASHINGTON BOTHELL - B BIO 180 INTRODUCTORY BIOLOGY (5)
Equivalent side: BELLEVUE COLLEGE - BIOL& 212 BIOLOGY MAJORS ANIMAL OR CELLULAR OR PLANT (6)
```

### Example 5: UWT 文件

你上传的 UWT 文件里面，学校列本身写的是 `UNIVERSITY OF WASHINGTON`，课程一般带 `T` 或 `TCSS`，例如：

```text
School: UWT
Course: TCSS 142
```

或者：

```text
School: UW
Course: TCSS 142
```

---

## 5. 每个 class 的作用

### `App.java`

程序入口。负责：

- 读取用户输入
- 加载 CSV 文件
- 显示菜单
- 调用搜索引擎
- 打印查询结果

### `Course.java`

表示一门课。保存：

- 学校名
- 课程文字

同时负责 normalize：

- `UW` -> `UNIVERSITY OF WASHINGTON`
- `UWB` -> `UNIVERSITY OF WASHINGTON BOTHELL`
- `UWT` -> `UNIVERSITY OF WASHINGTON`
- `SeattleU` -> `SEATTLE UNIVERSITY`
- `BC` -> `BELLEVUE COLLEGE`
- `CS 210` 和 `CS210` 可以匹配同一个 key

### `CourseEquivalency.java`

表示 CSV 中的一行等价关系。例如：

```text
UW CSE 142  <=>  Bellevue College CS 210
```

它保存：

- transfer side course
- equivalent side course
- comments
- begin date
- end date

### `CsvEquivalencyReader.java`

负责读取 CSV。

这个类自己写了一个简单 CSV parser，不需要外部 library。它可以处理：

- 普通逗号
- 引号里面的逗号
- 引号里面的换行
- CSV 里的 `""` escaped quote

### `EquivalencySearchEngine.java`

负责搜索。

核心数据结构：

```java
HashMap<String, ArrayList<CourseEquivalency>> equivalenciesByCourse;
```

key 例子：

```text
BELLEVUE COLLEGE|CS210
UNIVERSITY OF WASHINGTON|CSE142
SEATTLE UNIVERSITY|CSSE151
UNIVERSITY OF WASHINGTON BOTHELL|BBIO180
```

每一行数据会被放进 HashMap 两次：

1. transfer side 放一次
2. equivalent side 放一次

所以它天然支持双向查找。

---
- class 和 object
- encapsulation: fields are private, access through getters
- `ArrayList`
- `HashMap`
- file input
- exception handling with try/catch
- static helper methods
- method decomposition
- basic string processing
- command-line menu
- documentation / Javadoc comments

