# COMP603_Project
AN AUT-COMP603 PROJECT! ---- Oct.14 2019



# 项目说明

本项目为AUT课程 COMP603 的 Assignment1

选题是：An English vocabulary (英语词汇) learning software



# 编码规范

### 缩进

采用设置4个空格的缩进方式。



### 换行

1. 一行不超过75个字符

2. 如果太长，可以在：“,”后换行，或者运算符前换行

   

### 空格

1. 在关键字和（）之间加空格，如：while ()
2. 运算符前后加空格
3. 在参数列表的“,”后加空格， 如：test(int id, string name)
4. 强制转换类型后加空格，如：(object) x



### 括号

不省略大括号和小括号



### 空行

1. 导入的包之后加空行
2. 两个方法之间加空行
3. 局部变量和该方法中第一个逻辑语句之间加空行
4. if, while, for等控制语句之前加空行
5. 行注释前加空行



### 命名

1. 最重要的是要meaningful
2. 不使用生僻的单词
3. 太长的方法名，可能是包含了多个功能，可以尝试拆分方法
4. 缩写时，建议把元音带上，如message => MSG
5. 驼峰命名法
6. 常量：需要全大写，单词与单词之间加"_".
7. 包：单词中的每个字母都小写，例如: java.lang
8. 类的命令：单词需是名词，并且首字母大写
9. 接口：单词需是名词，并且首字母大写
10. 方法：单词需是动词, 并且首字母小写
11. 集合数组：名字需是复数
12. 不重要的，临时变量：采用标准名词，如 Object o, Exception e, character c, d, e



### 注释

1. 类和方法的注释一定要有
2. 去除无用的comment,通过变量名就可以了解含义
3. 如果有特殊的代码块，容易引起误解的，需要加注释
4. 如果有多层循环，可以在循环体的尾部加注释， 如//end while
5. 如果在switch中使用了fallthrough,则需要加注释，否则，通常是要加break



### 其他

1. 类成员变量：需采用get, set方法读取、设置
2. 重要的或重复的表达式或者方法应该提取出来
3. 异常处理：不忽略任何的异常信息。如果要追踪更高级的异常，不要忽略低级别的异常信息
4. 释放资源：如果有需要释放资源，需要放入finally块中



# 数据库设计

**USERS表**

该表在Database进行初始化时自动创建

注：PASSWORD字段为原密码进行sha256加密后的字符串

| ID   | USERNAME | PASSWORD                                                     |
| ---- | -------- | ------------------------------------------------------------ |
| 1    | yyz      | 8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92 |
| 2    | zyw      | 472bbe83616e93d3c09a79103ae47d8f71e3d35a966d6e8b22f743218d04171d |
| 3    | lly      | c0034605ea413370d5ad022b8d2f7fe33461bf6d7e5f4ac78f02c27b793673c9 |
| 4    | hpc      | 54bb6a0d2ea7d49744e886aa20859d70b6fc4ee0b9f144353ecb4b39195767f3 |

**单词表**

该表在Database进行初始化时，根据以下目录内的txt文件进行表的创建 

```
COMP603_Project\Memorize_helper\vocabulary_config
```

表名为文件名，例如存在以下文件

```
COMP603_Project\Memorize_helper\vocabulary_config\cet4.txt
```

则创建后的表名为 CET4，注意：该表名会被自动转换为大写

| ID   | WORD    | CHINESE               | PHONTIC  |
| ---- | ------- | --------------------- | -------- |
| 1    | a       | art.一(个)每一(个)    | ei,ə     |
| 2    | abandon | vt.丢弃放弃抛弃n.放纵 | ə'bændən |
| 3    | ability | n.能力能耐本领        | ə'biliti |



**MEMORIZE表**

该表在Database进行初始化时自动创建

| ID   | USER_ID | WORD_ID | WORD_SOURCE | CORRECT | WRONG | LAST_MEM_TIME | AGING |
| ---- | ------- | ------- | ----------- | ------- | ----- | ------------- | ----- |
| 1    | 1       | 1       | CET4        | 1       | 0     | 1571540000    | 0     |
| 2    | 1       | 2       | CET4        | 1       | 3     | 1571541022    | 0     |
| 3    | 2       | 13      | CET6        | 2       | 174   | 1571543389    | 1     |

