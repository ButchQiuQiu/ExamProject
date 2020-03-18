/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50530
Source Host           : localhost:3306
Source Database       : exam

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2019-12-30 13:40:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书目名称' ,
`mid`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`mid`) REFERENCES `major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `name` (`name`) USING BTREE ,
INDEX `book_ibfk_mid` (`mid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
AUTO_INCREMENT=5

;

-- ----------------------------
-- Records of book
-- ----------------------------
BEGIN;
INSERT INTO `book` VALUES ('1', 'java面向对象程序开发实战', '1'), ('2', 'Java高级特性编程实战', '1'), ('3', '数据结构', '2'), ('4', '计算机组成原理', '3');
COMMIT;

-- ----------------------------
-- Table structure for `chapter`
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '章节名称' ,
`mid`  int(11) NOT NULL COMMENT '书目的编号' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`mid`) REFERENCES `book` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `name` (`name`) USING BTREE ,
INDEX `mid` (`mid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='章节'
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of chapter
-- ----------------------------
BEGIN;
INSERT INTO `chapter` VALUES ('1', 'Java基础语法', '1'), ('2', '认识类和对象', '1'), ('3', '方法及方法重载', '1'), ('4', '封装与继承', '1'), ('5', '方法重写和多态', '1'), ('6', '抽象类和接口', '1'), ('7', '异常', '1'), ('8', '集合框架和泛型', '2'), ('9', '实用类', '2'), ('10', '输入和输出', '2'), ('11', '多线程', '2'), ('12', 'XML', '2'), ('13', '数据结构习题', '3'), ('14', '计算机组成原理习题', '4');
COMMIT;

-- ----------------------------
-- Table structure for `classes`
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级名称唯一' ,
`mid`  int(11) NOT NULL COMMENT '外键专业' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`mid`) REFERENCES `major` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `name` (`name`) USING BTREE ,
INDEX `mid` (`mid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
AUTO_INCREMENT=7

;

-- ----------------------------
-- Records of classes
-- ----------------------------
BEGIN;
INSERT INTO `classes` VALUES ('1', '学士后22班', '1'), ('2', '学士后23班', '1'), ('3', '网络22班', '3'), ('4', 'accp20班', '2'), ('5', 'accp31班', '2'), ('6', '网络12班', '3');
COMMIT;

-- ----------------------------
-- Table structure for `exampaper`
-- ----------------------------
DROP TABLE IF EXISTS `exampaper`;
CREATE TABLE `exampaper` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '试卷编号' ,
`name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '试卷的名称' ,
`qids`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '存储试题的编号' ,
`score`  double NOT NULL COMMENT '分数' ,
`count`  int(11) NOT NULL COMMENT '试题的数量' ,
`time`  int(11) NOT NULL DEFAULT 0 COMMENT '考试的时长' ,
`classid`  int(11) NOT NULL COMMENT '班级的编号' ,
`date`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`classid`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `name` (`name`) USING BTREE ,
INDEX `classid` (`classid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='试卷'
AUTO_INCREMENT=112

;

-- ----------------------------
-- Records of exampaper
-- ----------------------------
BEGIN;
INSERT INTO `exampaper` VALUES ('1', '测试用的 到时候删', '11*23*24*25*26*27*28*29*30*31*32*23*24*25*26*27*28*29*30*31*32*35*36*37*38*39*40*41*42*43*44*79*80*81*82*83*84*85*86*87*88*79*80*81*82*83*84*85*86*87*88', '10', '1', '123', '1', 'Wed Dec 26 22:26:15 CST 2018'), ('4', '士大夫', '151*151*151*152*153*151*152*153*151*152*153*151*152*153*151*152*153*151*152*153', '150', '1', '25', '6', 'Tue Dec 26 22:28:36 CST 2017'), ('111', '1', '1*2*3*4*5*6*7*8*9*10', '100', '10', '0', '2', null);
COMMIT;

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '唯一' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `name` (`name`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
AUTO_INCREMENT=4

;

-- ----------------------------
-- Records of major
-- ----------------------------
BEGIN;
INSERT INTO `major` VALUES ('2', 'accp'), ('1', 'java'), ('3', '网络');
COMMIT;

-- ----------------------------
-- Table structure for `question`
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '试题的编号' ,
`title`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '试题的名称 唯一' ,
`option`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '选项' ,
`solution`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '答案' ,
`analysis`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '解析' ,
`chapid`  int(11) NOT NULL COMMENT '章节的编号' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`chapid`) REFERENCES `chapter` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
UNIQUE INDEX `title` (`title`) USING BTREE ,
INDEX `chapid` (`chapid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='试题'
AUTO_INCREMENT=156

;

-- ----------------------------
-- Records of question
-- ----------------------------
BEGIN;
INSERT INTO `question` VALUES ('1', '在JDK1.7环境中，switch和多重if选择结构的描述正确的是（）（单选）', 'A：都是用来处理多分支条件的结构*B：switch只处理等值判断的情况，而且条件可以是整形变量、字符型变量或字符串*C：if选择结构可处理某个区间时的情况*D：以上说法都对', 'D', '此习题目考察的是Java中的2个循环结构的使用场景 和理解情况.这里需要注意的是：Java7之前，switch只能支持 byte、short、char、int或者其对应的封装类以及Enum类型。在Java7中已经支持String类型。 ', '1'), ('2', '在Java程序的条件结构中，如果需要处理的等值条件判断的变量值为一个字符串，则可以使用的条件结构是（）（单选）', 'A：多重if结构*B：程序控制流语句*C：嵌套if结构*D：以上都可以以上说法都对', 'A', '在java中，可以使用if语句进行单个字符串变量的值的判断。', '1'), ('3', '在Java中定义一个数组String[] strs={\"我\",\"是\",\"小\",\"强\"};数组中strs[4]对应的值是（）（单选）', 'A：强*B：‘强’*C：数组越界嵌套if结构*D：小强以上都可以以上说法都对', 'C', '此题考查Java数组下标的熟练掌握，arr[4]数组越界，所以选择c', '1'), ('4', '下面选项中，对Java数组理解说法正确的是（）（单选）', 'A：声明一个数组是可以用来保存不同数据类型的值*B：数组类型只能是保存整型跟字符串类型的数据*C：String数据类型的数组只能保存String类型的数据*D：以上说法都对小强以上都可以以上说法都对', 'C', 'A有误 一个数组不能保存多个类型的值 B有误 任何数据类型都可以定义数组 ', '1'), ('5', 'String S[]={\"安徽\",\"江苏\",\"山东\",\"河南\"};则S[1]的值是（）（单选）', 'A：\"安徽\"*B：\"江苏\"*C：\"山东\"*D：\"河南\"', 'B', '下标从0开始 ', '1'), ('6', '在Java中，关于数组，以下说法正确的是（）（单选）', 'A：通过数组的length()方法可获得数组的长度*B：通过数组的size属性可获得数组的长度*C：通过数组的length属性可获得数组的长度*D：通过数组的size()方法可获得数组的长度', 'C', '数组的长度，可能过通length属性获得，答案为c', '1'), ('7', '下面Java代码，能够正确执行的有（）（多选）', 'A：double b[]=new double[2]*B：double[] b=new double[2]*C：double b=new double[2]*D：double b=new double[0]', 'AB', '数组的长度，可能过通length属性获得，答案为c', '1'), ('8', '在Java中，int[] sz=new int[10];int m=2;sz=[m+3]=15;的意思是（）（单选）', 'A：数组的第3个元素赋值为15*B：数组的第4个元素赋值为15*C：数组的第m个元素赋值为15*D：数组的第6个元素赋值为15', 'D', '数组是下标从0开始，m=2，sz[5]就是第6个位置。所以本题答案是d ', '1'), ('9', 'Java中，能正确创建数组的是（）（单选）', 'A：byte b[]=new byte[2]*B：byte b[]=new byte[]*C：byte b=new byte[2]*D：byte b=new byte[]', 'A', '创建数组的语法为:数据类型[] 数组名 = new 数据类型[长度] 或者数据类型 数组名[] = new 数据类型[长度]  ', '1'), ('10', '在Java语言中下列对于数组的描述是正确的是（）（多选）', 'A：数组可以存放多个不同类型的值*B：数组可以使用的下标从0开始一直到数组的长度减1*C：数组只能可以存放同一种类型的值*D：使用数组的长度是可以灵活变化的，当放满了以后可以再将数组的长度加大', 'BC', '数组需要先创建才能使用，并且数组只能存放类型相同的值,数组一旦创建，长度就固定，无法改变长度。  ', '1'), ('11', 'Java中，正确创建数组的是（）（单选）', 'A：int b[]=new int[2]*B：int b[]=new int[]*C：int b=new int[2]*D：int b=new int[]', 'A', '创建数组的语法为:数据类型[] 数组名 = new 数据类型[长度] 或者数据类型 数组名[] = new 数据类型[长度] ', '1'), ('12', '在Java中，一直表达式int m[]={0,1,2,3,4,5,6};以下能获得该数组长度的是（）（单选）', 'A：m.length()*B：m.length*C：m.length()-1*D：m.length+1', 'B', '本题考查的是Java数组概念，数组下标是从零开始的，但是数据下标的总量和数据长度相同', '1'), ('13', '在Java中，代码能够正确执行的是（）（多选）', 'A：short b=new short[10]*B：short b=new short[]*C：short[] b=new short[10]*D：short b[]=new short[10]', 'CD', '创建数组的语法为:数据类型[] 数组名 = new 数据类型[长度] 或者数据类型 数组名[] = new 数据类型[长度] ', '1'), ('14', '在Java中，由以下语句定义array1数组：int array1[]=new int[10];则数组的第一个元素的正确引用方法为（）（单选）', 'A：arry1[1]*B：arry1[0]*C：arry1[]*D：arry1', 'B', '我们想获得数组中的值是通过数组名[]的形式进行获得的，而数组下标是从0开始的，要想获得数组中第一个元素的值，就是数组下标为0 的值，所以答案选择B ', '1'), ('15', '在Java中，for关键字的作用是（）（单选）', 'A：定义变量*B：定义函数*C：实现循环*D：定义类', 'C', '在Java中，可以通过while、do-while及for实现循环 ', '1'), ('16', '在jdk1.7环境下switch不支持的数据类型有（）（单选）', 'A：int*B：boolean*C：char*D：String', 'B', '本题考查switch语句的定义。Jdk1.7环境下，switch语句表达式支持的数据类型有int、short、byte、char、枚举、String', '1'), ('17', '在Java中，下列有关switch的说法，正确的是（）（单选）', 'A：switch结构可以完全替代多重if结构*B：条件判断为等值判断，并且判断的条件为数组时，可以使用switch结构*C：条件判断为等值判断，并且判断的条件为字符数组时，可以使用switch结构*D：条件判断为等值判断，并且判断的条件为double变量时，可以使用switch结构', 'C', 'switch语句中控制表达式的类型可以是byte、short、char、int(jdk7后，允许String)。它适用于等值判断，在等值判断时，可以替换if语句,为什么不选D，switch不支持double类型变量。', '1'), ('18', '在Java中，下面是正确的二维数组定义的是（）（单选）', 'A：long[][] ages=new long[2][3]*B：long ages=new long[2][3]*C：long ages=new long[][]*D：long[3][3] ages=new long[2][3]', 'A', '创建二维数组的语法为:数据类型[][] 数组名 = new 数据类型[长度][长度]，或者数据类型 数组名[][] = new 数据类型[长度][长度]，因此答案为A 	', '1'), ('19', '在Java中，下面定义二维数组正确的有（）（多选）', 'A：int[][] ages=new int[][]*B：int[][] ages=new int[3][3]*C：int ages[][]=new int[3][3]*D：int ages[][]=new int{1,2}{3,4}', 'BC', '创建二维数组的语法为:数据类型[][] 数组名 = new 数据类型[长度][长度]，或者数据类型 数组名[][] = new 数据类型[长度][长度]，因此答案为BC ', '1'), ('20', '在Java中，舍友下列数组定义语句：int a[][]={{1,2},{3}};则对此语句的叙述正确的是（）（单选）', 'A：定义了一个名为a的一维数组*B：a数组 a[1][1]为0*C：a数组元素的下标为1~3*D：数组中每个元素的类型都是整数', 'C', '题目中定义了一个二维数组，该数组长度为2，其中a[0][1]为1、a[0][2]为2、a[1][0]为3，不存在a[1][1]这个元素，这种写法下标越界，因为定义了数组的类型为int，因此该数组每个元素的类型都是整数 	', '1'), ('21', '以下Java的多维数组声明语句中，正确的有（）（单选）', 'A：int[] a=new[2,3]*B：int[] a={{1,2,3}}*C：int[] a a=new int[2,3]*D：int[][] a={{1,2,3},{2,3}}', 'D', 'a应该为：int[] a=new int[]{2,3};b应该为：int[][]a={{1,2,3}};c应该为：int[]a = new int[2];a[0]= 2;a[1]=3; ', '1'), ('22', '在Java中，关于多维数组，以下错误的是（）（单选）', 'A：在编程中，不能使用三维以上的数组*B：多维数组的声明，同一维数组一样，可以先声明再分配内存，也可以声明时直接分配内存*C：声明一个int类型的二维数组number，int[][] number=new int[3][3]*D：二维数组，可以理解为用一维数组保存的元素为一维数组', 'C', '此题主要考察学生对多维数组的理解和使用能力。在Java中，很少使用三维数组以上的数组，因为那样使用起来会非常的不方便。 ', '1'), ('23', '在Java中，一组有相同属性、共同行为和共同关系的对象的抽象称作（）（单选）', 'A：类*B：方法*C：属性*D：以上都不对', 'A', '这是类和对象的基本概念，需要记忆 ', '2'), ('24', '在Java中，创建一个名为mypackage的包的语句是（），该语句应该放在程序的位置为：（）（单选）', 'A：class，class语句之前*B：import，所有代码之前*C：package，第一行*D：using，using语句之前', 'C', '此题目考查的是import和package关键字进行包的操作。', '2'), ('25', '如果一个Java Applet程序文件中定义了3个类，则只有（）个类是public类，且使用Sun公司的JDK编译器编译该院程序文件将产生（）个文件名与类名相同而扩展名为class的字节码文件（单选）', 'A：3,1*B：2,3*C：1,3*D：3,3', 'C', '同一个java文件中可以有多个类，但只有一个为public的，编译时，每个对应自己的class文件。 ', '2'), ('26', '在Java中语言中，下面关于对象使用正确的是（）（单选）', 'A：Student stu=new Student();stu.say();*B：Student stu;stu.say();*C：stu=new Student();stu.say();*D：stu.say();Student stu=new Student():', 'A', '对象需要先创建出来，才能使用其方法。', '2'), ('27', '在Java的User类中，以下关于类或者方法的定义中正确的是（）（单选）', 'A：定义User：Class User{}*B：定义方法change：public void change(){}*C：定义方法change：public change(){}*D：定义类User：public Class(){}', 'B', '此题目考查的是Java中类和方法的创建，Java中类的创建方式是： 访问修饰符 class 类名{ } 无参方法的创建方式是：访问修饰符 返回值类型 方法名(){ } ，因此答案是B', '2'), ('28', '在Java中，关于类的定义说法错误的是（）（单选）', 'A：类定义了对象拥有的属性和方法*B：类的命名可以使用除“_”“$”以外的特殊字符*C：类可以通过public class类名{}的方式创建*D：类名每个单词的首字母通常要大写', 'B', '此题主要考察学生对类的创建方式理解和使用能力。 类的命名要遵循一定的规则，不能使用java中的关键字，不能包含任何嵌入的空格或点号”.\",以及除下划线 \"_\",\"$\"字符以外的特殊字符，不能以数字开头。', '2'), ('29', '在Java中，下列（）类声明是正确的（单选）', 'A：public void H1{...}*B：public class Move(){...}*C：public class void number{...}*D：public class Car{...}', 'D', '类用class声明名称不能包括关键字，没有（）。所以选择d ', '2'), ('30', '在Java中，下面关于类和对象的描述正确的是（）（多选）', 'A：类名可以由多个英语单词瓶邪组成，可包含“$”字符*B：在一个类中所有成员都必须是公开的*C：创建对象的过程就是实例化*D：类仅可以描述对象的特征', 'AC', 'a)类名可包含￥、%、$等特殊字符，实际开发中并不常用。b)如果一个类的方法是静态方法，使用该类的其他方法不是静态方法，是不可直接使用方法名的。c）创建类对象的过程又可以称为实例化对象。d)对象是类所表示的抽象事物的具体实例。答案AC ', '2'), ('31', '在Java中，关于类的描述错误的是（）（单选）', 'A：一个.class文件中只有一个类与泪雨文件名相同*B：类的入口静态main方法参数可以有由多个*C：一个类可以被多次实例化*D：实例化对象的时候使用new关键字', 'B', '此题目考察JAVA开发基础，B答案中java程序的入口main方法只能含有一个String[] 类型的参数 public static void main(String[] args){}.C答案相当于一个类可以被new多次。注意题干要求选择错误项。选择b ', '2'), ('32', '以下关于Java对象中属性说法错误的是（）（多选）', 'A：在面向对象的编程思想中，我们把对象的静态特征称为对象的属性*B：可以通过对象.属性()的形式来使用对象的属性*C：属性是用来描述对象动态特征（行为）的一个动作序列*D：对象的属性被存储在一些变量里', 'BC', '答案是B和C。使用对象的属性形式是对象.属性，使用对象的方法形式是对象.方法名()，因此B错误；另外，用来描述对象动态特征（行为）的一个动作序列是对象的方法而非属性，因此C错误。 ', '2'), ('33', '在Java中，面向对象的优点说法错误的是（）（单选）', 'A：能够使用类来模拟现实世界中实体的特征和行为*B：对象的行为和属性被封装在类中*C：使用对象的时候，首先必须知道对象内部的实现细节*D：可以将类理解为模板，利用类可以创建多个类的对象', 'C', '使用者无需知道对象内部的实现细节，只需要知道对象接收的消息即可 ', '2'), ('34', '在Java中下列关于类的成员变量说法错误的是（）（多选）', 'A：成员变量可以与雨布变量同名*B：在方法中能够使用的变量一定是成员变量*C：声明示例变量要用关键字static修饰*D：成员变量在声明中可以不用给初始值', 'BC', '此题目考察的是对类的成员变量的理解，a 说法正确，b能够在方法中使用的也可以是局部变量所以错误，c 修饰实例变量要用public，d成员变量会有默认的初始值，可以不用给。 ', '2'), ('35', '在Java中，定义带参数的方法时，参数可以作为一个或多个，参数之间使用（）符号进行分隔（单选）', 'A：;（分号）*B：,（逗号）*C：空格符*D：-（连字符）', 'B', ';号是中断的意思 ，是隔开的意思 ', '3'), ('36', '在Java中，对于带参数的方法描述错误的是（）（单选）', 'A：定义带参数的方法时，必须要给出参数列表*B：只有基本数据类型可以作为参数进行传递*C：在给出的参数列表中，必须要同时定义参数的数据类型*D：引用数据的数据类型也可以作为参数进行传递', 'B', '选项b，作为参数传递的还可以是对象类型。 ', '3'), ('37', '关于类方法的描述正确的是（）（单选）', 'A：类中被static修饰的方法叫类方法*B：类方法不可以使用实例.方法名()的形式调用*C：实例方法可以使用类名.方法名()的形式调用*D：在类方法内部可以直接调用本类的所有方法', 'A', '在类中，被static修饰的方法叫做静态方法，也叫类方法。类方法可以被类名直接调用，也可以被实例调用，在静态方法中，只能调用静态方法，不可以直接调用非静态方法，因此答案A是正确的 ', '3'), ('38', '在Java中，关于类方法描述错误的是（）（单选）', 'A：static修饰的方法叫类方法*B：类方法可以使用类名实例示例.方法名()的形式调用*C：类方法也可以使用对象名.方法名()的形式调用*D：类方法只能使用类名.方法名()的形式调用', 'D', '在类中，被static修饰的方法叫做静态方法，也叫类方法。类方法可以被类名直接调用，也可以被实例调用，在静态方法中，只能调用静态方法，不可以直接调用非静态方法，因此答案ABC是正确的 ', '3'), ('39', '在Java中，对于带参数的方法，下列选项中的说法错误的是（）（单选）', 'A：使用带参数的方法分为两步：定义带参数的方法和调用带参数的方法*B：带参数的方法返回值可以为void*C：带参数方法的参数个数可以为一个或多个*D：带参数方法的参数必须是基本数据类型的数据', 'D', '此题考查的是java程序的带参数方法。方法的参数可以任意类型，数量也可以任意个，返回值不限。所以正确答案D', '3'), ('40', '给定如下Java程序的方法结构，则方法体实现语句正确的是（）public String change(int i){//方法体}（单选）', 'A：return 100;*B：return \'a\';*C：return i+\"\";*D：return i;', 'C', '此题目考查的是带参方法的定义及调用。返回值类型是String类型，只有i+””的值是String类型。因此C是正确的选项', '3'), ('41', '在Java中，以下关于方法的重载说法正确的是（）（多选）', 'A：两个重载的方法参数个数必须相同*B：两个重载的方法一定有相同的方法名*C：两个重载的方法参数烈性可以不同*D：两个重载的方法返回类型一定不同', 'BC', '正确答案为BC。两个重载的方法参数个数可以不同，因此A错误。两个重载的方法返回类型可以不同，因此D错误。', '3'), ('42', '在Java中，以下关于方法重载的描述错误的是（）（单选）', 'A：方法重载要求方法名称必须相同*B：重载方法的返回类型必须一致*C：重载方法的参数列表必须不一致*D：一个方法在所属的类中能被重载多次', 'B', '正确答案为B。重载的方法返回类型可以不同，因此B错误。 ', '3'), ('43', '在Java中，关于方法的重载说法错误的是（）（单选）', 'A：方法的参数必须不同*B：构造方法可以进行重载*C：方法的返回值可以决定方法的重载*D：方法名必须相同', 'C', '正确答案为C。方法的返回类型不能决定方法的重载，即使改变了，也不受影响。 ', '3'), ('44', '在Java中，下列关于方法的重载说法错误的是（）（单选）', 'A：重载必须方法名相同*B：重载必须参数列表不同*C：重载必须返回类型不同*D：重载方法的访问修饰符可以不用', 'C', 'c错误，重载没有规定返回类型，仅仅规定参数列表必须不同。 ', '3'), ('45', '在Java中，下面的方法声明和（）项属于方法的重载。public void showInfor(){}（多选）', 'A：private void showInfor(int a){}*B：private void showInfor(){}*C：private boolean showInfor(){}*D：private void showInfor(int a,String b){}', 'AD', '在Java中，可以通过while、do-while及for实现循环', '3'), ('46', '在Java中，关于方法重载的说法错误的是（）（单选）', 'A：方法重载要求方法名称必须相同*B：重载方法的参数列表必须不一致*C：重载方法的返回类型可以不一致*D：一个方法在所属的类中只能被重载一次', 'D', '本题考察对方法重载的理解，方法重载的要求是方法名相同，参数不同（包括类型和个数不同），与返回类型无关，所以D错误 ', '3'), ('47', '以下关于Java重载的说法错误的有（）（多选）', 'A：重载一般发生在同一个类中或者父类和子类中*B：重载的方法名称相同*C：重载方法的参数类型和与原方法个数相同*D：重载与方法的返回类型无关', 'AC', '本题考察的是Java oop中方法的重载，我们知道，Java中方法重载的语法规则是：在同一个类中，方法名相同，参数列表不同，与返回值和访问修饰符无关，那么根据这个语法规则，我们可以得出以下结论：A显然是错误的；B 符合语法的要求；C 根据语法要求我们知道，要构成方法的重载，参数列表必须不同，所以c是错误的；D 根据语法要求D是正确的 ', '3'), ('48', '在Java中，下面对方法重载的描述正确的是（）（单选）', 'A：在同一个类中，方法的名字相同，参数的个数如果相同，则对应参数的类型必须不同*B：在同一个类中，方法的名字相同，参数的个数、参数的类型可以必须不同，返回类型必须相同*C：在同一个类中，方法的名字相同，但参数的个数、参数的类型或返回类型必须不同*D：以上说法都不对', 'A', '在同一个类中，方法的名字相同，但参数的个数、参数的类型不同，与返回类型和访问修饰符没有关系，正确答案是a ', '3'), ('49', '在Java中，以下关于static描述错误的是（）（单选）', 'A：static可以用来修饰方法，也可以用来修饰属性*B：static修饰的属性和方法称为类属性、类方法*C：在任何方法里都可以定义static变量*D：不使用static修饰的属性和方法，通常称为实例属性、实例方法', 'C', 'c错误，任何方法都不能定义static变量。', '4'), ('50', '在Java中，设i、j、k为类x中定义的int型变量名，下列类x的构造函数中错误的是（）（单选）', 'A：x(int m){...}*B：void x(int m){...}*C：x(int m,int n){...}*D：x(int h,int m,int n){...}', 'B', '本题考查构造方法的定义。构造方法虽无返回类型，但也无需写void关键字，与方法不同。应选择B。', '4'), ('51', '在Java中，下面对于构造方法的描述正确的是（）（单选）', 'A：类必须显示定义构造方法*B：构造方法的返回类型是void*C：构造方法和类有相同的名称，并且不能带任何参数*D：一个类中可以定义多个构造方法', 'D', '本题考察构造方法重载的实现方式，一个类没有显式提供构造方法，则系统会自动提供一个不带参的默认构造方法，构造方法没有返回值，构造方法可以进行重载，所以d正确。', '4'), ('52', '关于Java中static关键字的说法错误的是（）（单选）', 'A：static可以用来修饰属性、方法和代码块*B：static修饰的属性和方法可称为类属性、类方法*C：不使用static关键字修饰的属性和方法，通常称为实例属性、实例方法*D：使用static修饰的变量和方法只能用类名来访问，不能使用对象名来访问', 'D', 'd，错误，访问静态属性和方法可以使用对象名进行访问。 ', '4'), ('53', '如下关于Java中static说法错误的是（）（单选）', 'A：static可以用来修饰属性、方法和代码块*B：通常把static修饰的属性和方法称为类属性、类方法*C：不使用static关键字修饰的属性和方法，属于单个对象，通常称为实例属性、实例方法*D：类属性、类方法、实例属性、实例方法都可以通过类名和对象名访问', 'D', '通过类名访问必须是静态属性或者静态方法等。 ', '4'), ('54', '在Java中，关于static关键字理解正确的是（）（单选）', 'A：static属性的生命周期是从对象创建至对象结束*B：static方法中可以直接调用本类的非static方法*C：static可以修饰局部变量*D：static关键字所修饰的是类的成员，而非实例成员', 'D', '本题考察的Java中 static的特点，static关键字修饰的是类的成员，通过类名.成员进行调用，所以d正确。', '4'), ('55', '在Java中，为A类的一个无参数无返回值的方法method书写方法头，使得使用类名A作为前缀就可以调用它，该方法头的形式为（）（单选）', 'A：static void method()*B：public void method()*C：final void method()*D：abstract void method()', 'A', '静态变量也叫类变量，被所有该类的对象共享，并且具有返回值。 ', '4'), ('56', '在Java中，下面（）修饰符的变量是所有同一类生成的对象共享的（单选）', 'A：public*B：private*C：static*D：final', 'C', '本题考查是对静态成员的理解。静态变量也叫类变量，被所有该类的对象共享。正确答案是C。 ', '4'), ('57', '如果局部变量和成员变量同名，如何在局部变量作用域内引用成员变量：（）（单选）', 'A：不能引用，必须改名，使它们的名称不相同*B：在成员变量前加this，使用this访问该成员变量*C：在成员变量前加super，使用super访问该成员变量*D：不影响，系统自己区分', 'B', '在类中，局部变量和成员变量可以同名，在局部变量作用域可以直接访问成员变量，也可以添加this访问，局部变量可直接使用 ', '4'), ('58', '下列属于三大特征的是（）（单选）', 'A：方法*B：属性*C：封装*D：修饰', 'C', '面向对象三个特性是封装、继承、多态，因此答案为C ', '4'), ('59', '下列封装说法正确的是（）（单选）', 'A：只能对类中的方法进行封装，不能对属性进行封装*B：对于父类中被封装并私有化的属性，子类仍然可以直接调用*C：封装没有太大的意义，因此尽量不要使用*D：封装的主要作用在于对外隐藏内部实现细节，增强程序的安全性', 'D', '本题考察封装的作用，在面向对象中封装是指隐藏对象的属性和实现的细节，仅对外提供公共访问方式。在类定义中用private关键字来隐藏属性。', '4'), ('60', '封装是指把对象的属性隐藏起来，把（）暴露出来（单选）', 'A：方法*B：属性名*D：接口', 'A', '面向对象三个特性是封装、继承、多态，其中，封装的一个体现就是讲属性私有，方法公开，因此答案为A ', '4'), ('61', '如果父类方法和子类方法同名，如何在子类方法中引用父类的上述方法：（）（单选）', 'A：不能引用，必须改名，使它们的名称不相同*B：在父类方法前加this*C：在父类方法前加super*D：不影响，系统自己区分', 'C', '面向对象三个特性是封装、继承、多态，其中，继承的特性为子类SubClass通过extends来继承父类SuperClass。在父类和子类中可以存在同名同参的方法，子类调用父类的方法需通过super实现 ', '4'), ('62', '关于封装，下面说法不正确的是：（）（单选）', 'A：一般在开发中往往要将类中的属性封装*B：对属性和方法进行封装使用关键字private*C：构造方法不能封装*D：使用private修饰的属性和方法不能在其他类里面直接调用', 'C', '面向对象三个特性是封装、继承、多态，其中，实现封装需通过private，在开发中，往往将属性私有，这些使用private修饰的属性或方法，只能在本类访问，在其他类中不可直接调用，而且构造方法也可以封装 ', '4'), ('63', '在Java中下列说法正确的是（）（单选）', 'A：一个父类可以隐藏部分属性不让子类可见*B：一个子类可以有多个父类，但一个父类只可以有一个子类*C：一个子类可以有一个父类，一个父类也只能有一个子类*D：上述说法都不对', 'A', '一个父类可以将部分属性设置为private，这部分对子类不可见，因此A正确。Java中为单根继承，一个子类只能有一个父类，而一个父类可以有多个子类', '4'), ('64', '在Java中，关于封装属性，描述正确的是（）（单选）', 'A：石油化属性，提供公开的getter/setter方法*B：抽取类的共性，将其私有化*C：将要封装的类改为private，使其它类无法访问*D：在封装的类中，属性必须是public修饰的', 'A', '在封装类的属性时，被封装的属性不能是private，而且属性私有化之后，必须提供公开的getter和setter方法。所以正确答案是a ', '4'), ('65', '若类中把属性age封装起来，则对应的方法名应为（）（多选）', 'A：settage*B：setAge*C：getter*D：getAge', 'BD', '面向对象三个特性是封装、继承、多态，其中，封装的一个体现就是将属性私有，方法公开，将属性私有的方式，getXXX获取值，setXXX设置值 ', '4'), ('66', '若想Dog中的name属性隐藏起来只能在Dog类内部使用，则name类如何定义：（）（单选）', 'A：public String name;*B：private String name;*C：String name;*D：static name;', 'B', 'public：所有的类都可以访问。private：只有在同一个类中才可以访问。protected：同一个类中、同一个包中、子类中都可以访问，包类型的。默认类型：同一个类中、同一个包中可以访问到。所以答案为B ', '4'), ('67', '在Java中，如果用protected来修饰一个方法，name它的访问范围可以是（）（单选）', 'A：可以在子类中访问，但不能在同一包内的其他类中访问*B：可以在其他类中访问，但不能在同一包内的子类中访问*C：既能在子类中访问，又可以在同一包内的其他类中访问*D：可以在其他包中的类中访问', 'C', '本题考查的是访问修饰符的访问权限。protected修饰的成员在本包中与public访问权限等同，在不同包中，只能在子类中访问。应选择C。', '4'), ('68', '在Java中，在考虑子类和父类可能不在同一个包的情况下，下列说法中一定正确的是（）（单选）', 'A：实例方法可直接调用父类实例方法*B：实例方法直接调用父类的类方法*C：实例方法可直接调用其他类的实例方法*D：实例方法可直接调用本类中的类方法', 'D', '本题考查的是类成员的访问权限。当父类或其他类的实例方法使用private或默认修饰符修饰时，外包是无法访问的，因此ABC是错误的。正确答案是D。', '4'), ('69', '属于java的访问控制符的是（）（多选）', 'A：public *B：protected*C：private*D：static', 'ABC', 'public：所有的类都可以访问。private：只有在同一个类中才可以访问。protected：同一个类中、同一个包中、子类中都可以访问，包类型的。默认类型：同一个类中、同一个包中可以访问到。所以答案为ABC ', '4'), ('70', '在Java中，以下关于类的声明正确的是（）（单选）', 'A：abstract final class HI{}*B：abstract private move(){}*C：protected private number{}*D：public abstract class Car{}', 'D', '本题考查类的声明以及访问修饰符的使用。Abstract修饰的类是抽象类，用于被子类实现。final修饰的类不能被子类继承。A选项是错误的。类的声明不能有（），B选项是错误的。protected、private关键字是矛盾的，C选项是错误的。正确答案是D。', '4'), ('71', '在Java中，如果希望某个变量只可以被类本身访问和调用，则应该使用下列（）种访问控制修饰符（单选）', 'A：private *B：protected*C：private protected*D：public', 'A', '本题考查访问修饰符的访问权限。private访问修饰符修饰的成员为私有型成员，只能被本类成员访问，正确答案是A。', '4'), ('72', '在Java中，下列类型修饰符中修饰范围最大的是（）（单选）', 'A：public*B：default*C：private*D：Protected', 'A', '本题考查访问修饰符的访问权限。public修饰的成员可以在任何处被访问，应选择A。 ', '4'), ('73', '在Java中，一个类能被同一包或不同包中的其他类访问的修饰符（）（单选）', 'A：private*B：protected*C：public*D：默认访问修饰符', 'C', '：默认访问修饰符：包内的任何类都可以访问它，而包外的任何类都不能访问它(包括包外继承了此类的子类)，因此，这种类、类属变量及方法对包内的其他类是友好的，开放的，而对包外的其他类是关闭的', '4'), ('74', '在Java中，关于被私有访问控制符private修饰的成员变量，以下说法正确的是（）（单选）', 'A：可以被三种类所引用：该类自身、与它在同一包中的其他类、在其他包中的该类的子类*B：可以被两种类访问和引用：该类本身、该类的所有子类*C：只能被该类自身所访问和修改*D：只能被同一个包中的类访问', 'C', '本题考查访问修饰符的访问权限。private访问修饰符修饰的成员为私有型成员，只能被本类成员访问，正确答案是C。', '4'), ('75', '在Java中，下面关于构造方法描述正确的是（）（单选）', 'A：构造方法的名称必须和类名相同*B：构造方法不能带有参数*C：类只能有一个构造方法*D：构造方法的返回类型是void', 'A', '此题目考查的是Java的构造方法。构造方法必须和类名相同；可以有参数，也可以没有参数；构造方法可以有多个；构造方法没有返回值。因此，答案是A。', '4'), ('76', '在Java中，下面关于构造方法说法错误的是（）（单选）', 'A：当一个类未定义构造方法时，Java虚拟机便提供一个默认构造方法*B：构造方法不能够重载*C：构造方法可以接受参数*D：当类的父类只有一个带参数的构造方法时，这个类必须提供自定义的构造方法', 'B', '构造方法的名称和类名相同，没有返回值类型，构造方法的主要作用就是在创建对象时执行一些初始化操作每个类都有一个默认的无参的构造方法。一个类中可以自定义多个构造方法，称为构造方法的重载。父类写了一个有参的构造方法时，必须写一个无参的构造方法。故答案是B。 ', '4'), ('77', '在Java语言中，关于构造方法说法正确的是（）（多选）', 'A：方法名和类名相同*B：返回值类型为void*C：必须声明为public*D：可以重载', 'AD', '在Java中，可以通过while、do-while及for实现循环', '4'), ('78', '下列选项中关于Java中构造方法的说法错误的是（）（单选）', 'A：构造方法负责对象成员的初始化工作，为实例变量赋予合适的初始值*B：构造方法名与类名相同，返回值类型必须是void类型*C：使用new关键字实例化对象的过程就是调用构造方法的过程*D：可以通过构造方法的重载来表达对象的多种初始化行为', 'B', '此题目考查的是java中的构造方法。构造方法没有返回值，因此说法错误的是B。 ', '4'), ('79', '关于Java语言中多态的说法错误的是（）（单选）', 'A：多态是面向对象三大特征之一*B：通过多态可以提高代码的可扩展性和可维护性*C：把子类转换为父类，称为向下转型*D：使用父类作为方法的形参是使用多态的常用方式', 'C', 'c错误，把子类转换为父类，称为向上转型。 ', '5'), ('80', '在Java中，一个类可同时定义许多同名的方法，这些方法的形式参数个数、类型或顺序各不相同，传回的值也可以不相同。这种面向对象程序的特性称为（）（单选）', 'A：隐藏*B：覆盖*C：重载*D：Java不支持此特性', 'C', '分析题目中的意思，在一个类中方法同命不同参，这里被称为方法重载所以选择c，是一个最基本的概念。 ', '5'), ('81', '在Java中，下列选项（）不是实现多态的条件（单选）', 'A：继承的存在*B：子类重写父类的方法*C：父类引用变量指向子类对象*D：父类必须是抽象类', 'D', '实现多态不要求父类必须是抽象类，多态,就是重载和重写.重载发生在一个类中，重写发生在子类。 ', '5'), ('82', '在Java中，多态的实现不仅能减少编码的工作量，还能大大提高程序的可维护性及可扩展性，那么实现多态的步骤包括以下几个方面除了（）（单选）', 'A：子类重写父类的方法*B：子类重载同一个方法*C：定义方法时，把父类类型作为参数类型；调用方法时，把父类或子类的对象作为参数传入方法*D：运行时，根据实际创建的对象类型动态决定使用哪个方法', 'B', '重载只能发生在同一个类中。有了继承关系才能发生多态 ，重载实现了方法的复用，跟多态无关 ', '5'), ('83', '在Java中，以下关于多态说法错误的是（）（单选）', 'A：实现多态的一个前提是要有继承关系*B：将一个父类的引用指向子类对象，要进行强制类型转化*C：父类引用变量引向其子类对象是实现多态的一个条件*D：使用多态可以提高代码的可拓展性和可维护性', 'D', '此题目考查的是Java中的多态。用父类作为引用类型时，不能调用子类特有的方法，否则会出现编译错误。因此，答案是D。', '5'), ('84', '在Java中，关于重写说法错误的是（）（单选）', 'A：重写方法名相同，参数列表不同的方法*B：方法重写是在一个类继承另一个类时，在子类中重新实现了父类的方法*C：重写方法不能缩小被重写方法的访问权限*D：重写方法的返回值类型可以和被重写方法的返回值类型相同', 'A', '该题考查的是方法重写，方法重写是在两个类存在继承关系时，子类可以重写父类中的同名方法，要求参数列表相同，访问权限不能严于父类，返回值类型与被重写方法相同或是其子类。正确答案是A。', '5'), ('85', '在Java中，（）方法可以比较两个对象的值是否相等，如两个对象值相等，则返回true（单选）', 'A：toString()*B：equals()*C：compare()*D：以上所有选项都不正确', 'B', '此题考察的是equals方法的应用，选项A是字符串输入方法，返回字符串类型，选项B比较两个对象是否相等，选项C方法是java.util.Comparator接口的方法，因此正确答案为B', '5'), ('86', '关于重写和重载的说法正确的是（）（单选）', 'A：重载和重写都是涉及同一个的同名方法，只是重载要求参数列表不同*B：重载方法不能缩小被重载的访问权限*C：重载要求参数列表不同，重写要求参数列表相同*D：重载要求返回值类型相同', 'C', '重载涉及同一个类中的同名方法，要求方法名相同，参数列表不同，与返回值类型无关；重写涉及的是子类和父类之间的同名方法，要求方法名相同、参数列表相同、返回值类型相同。所以可知描述正确的是C选项。 ', '5'), ('87', '在Java中，下列说法正确的有（）（单选）', 'A：class中的构造方法不可省略*B：构造方法必须与class同名，但方法不能与class同名*C：构造方法在一个对象被new时执行*D：一个class只能定义一个构造方法', 'C', 'a项中的构造方法是可以省略的，不写的话有默认的空构造函数，b项方法是可以与class同名的，d项可以有多个构造方法，多个构造方法构成重载。', '5'), ('88', '在Java中，与方法重载和重写，都有关的选项是（）（多选）', 'A：访问修饰符*B：方法名*C：返回值类型*D：形参', 'BD', '本题考察学员对方法重载和重写的基本理解。在四个选项中，和方法重载相关的选项是bd；和方法重写相关的选项是abcd。所以本题选择bd。', '5'), ('89', '在Java中下列关于方法的重载说法正确的是（）（单选）', 'A：Overriding表示方法的重载*B：方法重载种方法名必须相同，参数类型和个数也必须相同*C：方法重载提高了代码的灵活性和重用性*D：可以用方法的返回类型来决定方法的重载', 'C', '此题目考察的是对Java的类的方法 重载的理解和掌握，a Overriding表示方法的重写，而不是重载，b 方法重载参数类型或者个数必须不同， 所以选c 提高了方法的灵活性和重用性 正确，d 不能用方法的返回类型来决定方法的重载 ', '5'), ('90', '下列关于类的描述说法错误的是（）（单选）', 'A：类的关键字是class*B：可以在类当中随便定义其他的类*C：类是对象的类型，是从对象当中抽象出来的概念*D：类与类之间可以继承', 'B', '此题目考察的是对Java类和对象的理解和使用，类的关键字是class  ，子类可以继承父类，  而且类是对象的抽象概念， 类当中可以定义类，但不能随意定义 ，所以选b ', '5'), ('91', '在Java中，用abstract修饰一个方法的话，那么（）（单选）', 'A：它所在的类也必须用abstract来修饰*B：它不能有方法体*C：它所在的类是可以实例化的*D：它所在的类是不能可以实例化的', 'ABD', '本题考查的是抽象类和抽象方法。抽象类和抽象方法要使用abstract关键字修饰，抽象方法所在的类必须为抽象类，抽象方法没有方法体，抽象类不能实例化。正确答案是ABD。', '6'), ('92', '在Java中，如果父类中的某些方法不包含任何逻辑，并且需要由子类重写，应该使用（）关键字来声明父类的这些方法（单选）', 'A：final*B：static*C：abstract*D：void', 'C', '本题考查的是抽象类和抽象方法的使用。如果父类中的方法没有实际含义，而是用来被子类重写，可以把父类中此方法定义为抽象的，使用abstract关键字。正确答案是C。 ', '6'), ('93', '在Java中，定义抽象类和抽象方法的关键字是（）（单选）', 'A：abstract*B：final*C：virtual*D：interface', 'A', 'final是定义常量的关键字；virtual是C#里面定义虚方法的关键字；interface是Java里面的接口。 ', '6'), ('94', '在Java中，下列（）的论述是正确的（单选）', 'A：abstract修饰符可修饰属性、方法和类*B：抽象方法的方法体必须用一对大括号{}包住*C：声明抽象方法，大括号可有可无*D：声明抽象方法不能写出大括号', 'D', '本题考查的是抽象类和抽象方法的使用。abstract修饰符可修饰类和方法，A是错误的。抽象方法没有方法体，无需使用的大括号，B、C是错误的。正确答案是D。 ', '6'), ('95', '在Java中，抽象类是用（）关键字来修饰的（单选）', 'A：abstract*B：final*C：protected*D：private', 'A', '抽象类使用abstract关键字修饰，正确答案是A。 ', '6'), ('96', '在Java中，以下关于抽象类的说法正确的有（）（单选）', 'A：抽象类中的方法都是抽象方法*B：抽象类中可以有抽象方法和非抽象方法*C：抽象类中的抽象方法可以有方法体*D：抽象类可以使用new关键字来创建对象', 'B', '此题目考查抽象类和抽象方法的具体定义规则和使用。抽象类中可以有抽象方法和非抽象方法，抽象方法一定没有方法体，抽象类不能实例化。B答案是正确的', '6'), ('97', '在Java中，try里面有一个return语句，那紧跟其后的finally中的代码会不会执行，什么时候执行（）（单选）', 'A：不会执行*B：会执行，在return语句之前执行*C：会执行，在return语句之后执行*D：会执行，可能在return语句之前执行，也可能在return语句之后执行', 'B', '本题考察对于try-catch-finally中return语句的执行顺序，finally语句中的代码唯一不执行的情况，是在catch中直接退出Java虚拟机。在catch中加入如return的执行顺序，出现异常进入catch，执行里面的代码后执行finally中代码，最后执行return语句。所以选择b ', '7'), ('98', '在Java中，下列（）异常是检查型异常，需要在编写程序时声明（单选）', 'A：NullPointerException*B：ClassCasException*C：FileNotFountException*D：IndexOutBoundsException', 'C', '如果出现 RuntimeException，那么一定是程序员的错误。', '7'), ('99', '下面关于Java异常处理模型的说法错误的是（）（单选）', 'A：一个try只能有一条catch语句*B：一个try中可以不使用catch语句*C：catch块不能单独使用，必须始终与try块在一起*D：finally块不能单独使用，必须始终与try块在一起', 'A', 'try是可以有多个catch的 ', '7'), ('100', '在Java中，如果一个方法中用throws声明抛出了一个异常，那么此方法的调用者（）（单选）', 'A：以下原方法中抛出，所以不用处理*B：必须在本方法中进行抛出*C：既可以在本方法中抛出，也可以用try，catch捕获*D：以上都不对', 'C', '使用throws关键字将异常抛给调用者后，调用者必须将throws声明有异常的方法放置在try-catch语句中调用，在catch块中要指定抛出的异常类或其父类，以便捕捉程序中可能出现的这种类型的异常，并处理。', '7');
INSERT INTO `question` VALUES ('101', '在Java中，异常处理正确的语法是（）（单选）', 'A：try{}catch(...){}*B：try{}*C：catch(...){}*D：catch(...){}finally{}', 'A', 'try catch 必须同时出现，finally可有可无 ', '7'), ('102', '在Java中，处理异常时可以使用异常跟踪栈来跟踪异常发生的根源地，若异常对象是e，那么正确的语句是（）（单选）', 'A：e.printStackTrace();*B：e.toString();*C：System.out.print(e);*D：e.print();', 'A', '本题考查对异常处理机制的理解。Java中，Throwable类的printStackTrace()方法可以将此 throwable 及其追踪输出到指定的 PrintWriter。本题应选择A。', '7'), ('103', '在Java的异常处理中，能单独与finally语句一起使用的块是（）（单选）', 'A：try*B：catch*C：throw*D：throws', 'A', '这是常用的异常处理，大家要牢记，扑捉异常使用try-catch、try-catch-finally，抛出异常使用throw ', '7'), ('104', '关于Java中catch语法块的特点描述正确的是（）（多选）', 'A：Java中允许使用多个catch语法块*B：Java中不允许使用多个catch语法块*C：Java中的catch语法块可以省略，可以使用try{}finally{}的语法格式*D：Java中的catch语法块可以省略，不能使用try{}finally{}的语法格式', 'AC', '本题考查对try-catch-finally语句的理解。', '7'), ('105', '在Java中，抛出异常使用（）关键字（单选）', 'A：throw*B：throwable*C：throwing*D：catch', 'A', '此题主要考查异常处理的关键字的区别，其中throw抛出异常；throwable类是 Java 语言中所有错误或异常的超类。只有当对象是此类（或其子类之一）的实例时，才能通过 Java 虚拟机或者关键字抛出异常；throws是声明抛出异常的关键字；catch用来捕捉异常。 ', '7'), ('106', '在Java编程中，如果一个方法体里可能会遇到异常缺又不知如何处理，那么在声明方法时，（）说法是正确的（单选）', 'A：捕获异常*B：抛出异常*C：声明异常*D：嵌套异常', 'C', '此题主要考核Java中用来抛出异常，首先需要声明异常因此因此答案选择C 	', '7'), ('107', 'throw语句是主动产生一个异常，格式为（）（单选）', 'A：throws 异常*B：throw 异常*C：异常 throws*D：异常 throw', 'B', 'throw：生成并抛出异常（位于方法体内部）；throws：声明方法内部抛出了异常哪个（必须跟在方法参数列表后面）。 ', '7'), ('108', '有关Java中的throws关键字，下列说法错误的是（）（多选）', 'A：throws表示出现异常的一种可能性，并不一定会发生这些异常*B：在方法声明时，告诉调用这该方法可能抛出的异常，异常的捕获、处理交由调用者去实施*C：throws可以抛出多个异常*D：有throws的方法中必须有throws关键字', 'CD', 'throws用来声明方法内部可能有异常，如果知道具体异常，就用“throw new+异常”抛出异常，如果不确定具体异常，可以不用throw ', '7'), ('109', '在Java中，throws的作用是（）（单选）', 'A：表示方法可能会抛出异常*B：表示后面是方法的输出量*C：方法的标志，每个方法都必须有*D：没有意义', 'A', '此题考查throws的作用，在java中，throws的作用就是声明并抛出异常 ', '7'), ('110', '使用JDBC访问数据库时，不会发生的异常是（）（单选）', 'A：SQLException*B：IOException*C：ClassNotFountException*D：Exception', 'B', 'IOException是文化读取流异常。选B ', '7'), ('111', '在Java中，以下关键字（）是声明异常的（单选）', 'A：throw*B：throws*C：try-catch*D：try-catch-finally', 'A', 'A抛出一个异常  C D发现异常处理异常 ', '7'), ('112', '在Java中，表示方法受到非法参数的异常是（）（单选）', 'A：ArithmsException*B：NullPointerException*C：ClassNotFoundException*D：IllegalArgumenException', 'D', '此题目考查的是Java中的异常类型。接受到非法参数，应该报IllegalArgumentException。因此答案是D。', '7'), ('113', '在Java中，出现算术异常时，会抛出（）异常（单选）', 'A：ArithmsException*B：NullPointerException*C：IOException*D：ClassNotFoundException', 'A', '异常分类、运行时异常和非运行时异常（Checked）的区别 ', '7'), ('114', '在Java中，以下对自定义的异常描述正确的是（）（单选）', 'A：自定义的异常必须继承Exception*B：自定义的异常可以更加明确的定位异常出错的位置，同时给出详细的异常信息*C：自定义的异常类的父类可以继承Error*D：Java中不能自定义异常类', 'B', '本题考察对异常体系的掌握，自定义异常可以继承Exception或Throwable 类，在java中可以自定义异常，B正确 ', '7'), ('115', '在Java的异常处理模型中，用户自定义的异常类应该是（）的子类（单选）', 'A：Exception*B：NullPointerException*C：ClassNotFoundException*D：IllegalArgumenException', 'A', '本题考查的是异常的类型。异常类中Exception是父类。故异常自定义时的父类都是Exception。故答案是A。', '7'), ('116', '在Java中，不属于运行时异常的是（）（单选）', 'A：NullPointerException*B：IndexOutOfBoundsException*C：IOException*D：ClassNotFoundException', 'C', '本题考查对异常处理的理解。Java的Exception分两大类运行时异常和非运行时异常(编译异常)。程序中应当尽可能去处理这些异常。', '7'), ('117', 'Java异常处理机制主要为了捕获（）错误，发生错误时，将引发异常，该异常由一个Java代码块捕获（单选）', 'A：编译期*B：运行期*C：测试期D：调试期', 'B', 'Java中可能出现运行异常的代码最佳的处理时期就是在编译的时候处理并且捕获。', '7'), ('118', '在Java的异常处理模型中，下列泪在多重catch中同时使用时，（）应该最后列出（单选）', 'A：ArithmsException*B：NullPointerException*C：IOException*D：Exception', 'D', '本题考查的是多重catch块的使用。一个try语句块后面可以写多个catch语句块，分别处理不同的异常。但排列顺序必须是从子类到父类，最后一个一般都是Exception类。故答案是D。 ', '7'), ('119', '在Java中的错误处理是通过异常处理模型来完成的，那么异常处理模块可以处理的错误是（）（单选）', 'A：运行时错误*B：逻辑错误*C：语法错误*D：内部错误', 'A', '逻辑错误，语法错误，内部错误这些在编译的时候就会呈现出来的。', '7'), ('120', '在Java中，尝试对null对象操作时，会产生（）类型的异常（单选）', 'A：ArithmsException*B：NullPointerException*C：IOException*D：ClassNotFoundException', 'B', 'Java异常的分类。当应用程序试图在需要对象的地方使用 null 时，抛出该异常。这种情况包括：调用 null 对象的实例方法。访问或修改 null 对象的字段。将 null 作为一个数组，获得其长度。将 null 作为一个数组，访问或修改其时间片。将 null 作为 Throwable 值抛出。 ', '7'), ('121', '在Java中，关于异常类型说明，下列选项中错误的是（）（单选）', 'A：ArithmsException：算术错误情形，如以零做除数*B：NullPointerException：方法接受到非法参数*C：ClassNotFoundException：不能加载所需的类*D：Exception：异常层次结构的根类', 'B', '此题目考查的是Java中的异常类型。NullPointerException是空指针异常，因此说法错误的是B。 ', '7'), ('122', '在Java中，下列（）不是Java异常处理机制主要依赖的关键字（单选）', 'A：try*B：array*C：catch*D：throw', 'B', '本题考查Java异常处理语句。提供两种异常处理机制：捕获异常和声明抛弃异常。', '7'), ('123', '在Java中，集合API中Set接口的特点是（）（单选）', 'A：不允许重复元素，元素有顺序*B：允许重复元素，元素无顺序*C：允许重复元素，元素有顺序*D：不允许重复元素，元素无顺序', 'D', '略', '8'), ('124', '在Java中，实现了Set接口的类是（）（单选）', 'A：ArrayList*B：HashTable*C：HashSet*D：Collection', 'C', '略', '8'), ('125', '在Java中，表示键值对概念的接口是（）（单选）', 'A：Set*B：List*C：Collection*D：Map', 'D', '略', '8'), ('126', '在Java中，下列（）项是泛型的优点（多选）', 'A：不用向下强制类型转换*B：代码容易编写*C：类型安全*D：运行速度快', 'AC', '略', '8'), ('127', '在Java中，创建一个只能存放String的泛型ArrayList的语句是（）（单选）', 'A：ArrayList<int> al=new ArrayList<int>();*B：ArrayList<String> al=new ArrayList<String>();*C：ArrayList al=new ArrayList<String>();*D：ArrayList<String> al =new List<String>();', 'B', '略', '8'), ('128', '下列选项中，基本数据类型和包装类对应不正确的是（）（单选）', 'A：byte对应Byte*B：boolean对应Booolean*C：short对应Short*D：char对应Char', 'D', 'char的包装类为Character，所以D错误 ', '9'), ('129', '下列（）类不是Java基本数据类型的封装类（单选）', 'A：Interger*B：Byte*C：String*D：Character', 'C', '本题考查的是包装类。String类型是引用数据类型，但不属于包装类。正确答案是C。 ', '9'), ('130', 'Java的基本数据类型char的包装类型是（）（单选）', 'A：int*B：char*C：String*D：Character', 'D', '本题考查的是包装类。基本数据类型Char的包装类是Character，正确答案是D。', '9'), ('131', '关于Java的Float数据类型，下列说法正确的是（）（多选）', 'A：Float是一个类*B：Float在Java.lang包中*C：Float a=1.0是正确的赋值方法*D：Float a=new Float(1.0)是正确的赋值方法', 'ABD', 'Float是包装类，所有的包装类都属性Java.lang包。', '9'), ('132', '在Java中，int和Integer的区别错误是（）（单选）', 'A：int是Java的基本数据类型，Integer是Java为int提供的包装类*B：包装类和基本数据类型的行为完全不同，并且它们具有不同的语义*C：包装类和基本数据类型具有不同的特征和用法*D：Integer是Java的基本数据类型，Int是java为int提供的包装类', 'D', 'int是Java中的基本数据类型，存储整型数值，其包装类为Integer。Java用包装类来把基本数据类型转换为对象。包装类中提供了一系列实用的方法，包装类和基本数据类型两者行为完全不同，具有不同的特征和用法 ', '9'), ('133', '在Java中，能够用来关闭流的方法是（）（单选）', 'A：release()*B：close()*C：flush()*D：remove()', 'B', 'close()是java中关闭流的正确方法。 ', '10'), ('134', '在Java中，被看作为输出流基类的选项是（）（多选）', 'A：OutputStream*B：ReaderC：InputStream*D：Writer', 'AD', 'Java中字节输出流的父类是OutputStream，字符输出流的父类是Writer。 ', '10'), ('135', '在Java中，流的作用是（）（单选）', 'A：建立程序的逻辑结构*B：进行更加有效的数据传输操作*C：只是完成文件内容的读出*D：只是完成用户输入的获取', 'B', '使用流，是为了简化数据的读写操作，让程序员能专注在有效合理的数据处理上，而不是底层的数据写入对应的物理地址，磁盘驱动器的状态等等方面。所以，此答案为B ', '10'), ('136', '下面（）选项是Java中流的分类（多选）', 'A：数据流和非数据流*B：字符流和字节流*C：基本数据流和对象流*D：输入流和输出流', 'BD', '概念题，流的分类中处理单位不同分成字符和字节，根据方向，分输入和输出，答案a的提法有问题，非数据是很模糊，不明确的提法。答案C中，基本数据流的提法比较笼统，不严谨。所以答案B，D ', '10'), ('137', '在Java中，以下File类的方法中（）用来判断是否是目录（单选）', 'A：isFile()*B：getFile()*C：isDirectory()*D：getPath()', 'C', '在Java中，File类的方法中isDirectory()用来判断是否是目录 ', '10'), ('138', '在Java中，使用File类实现文件或目录操作时，能够判断文件或目录是否存在的方法是（）（单选）', 'A：exist()*B：exists()*C：fileExist()*D：isFile()', 'B', '该题目考察文件操作，在Java中需要借助File类完成文件操作，其中File类可以指文件，也可以指目录，其方法exists()判断某个文件或目录是否存在，返回值为boolean类型。isFile()方法判断其是否为一个文件。因此正确答案为B ', '10'), ('139', '在Java中，下列有关线程的叙述中正确的一项是（）（单选）', 'A：一旦一个线程被创建，它立即开始执行*B：使用start()方法可以使一个线程成为可运行的，但是它不一定立即开始运行*C：当一个线程因为抢占机制而停止运行时，它被放在可运行队列的前面*D：一个线程执行sleep()方法后处于死亡状态', 'B', '本题考察线程的创建和启动。当线程被创建后，执行start()方法后，线程处于可运行状态，选项A错误。当线程执行完一个时间片后继续回到就绪状态，等待CPU再次分配时间片，而不存在一个队列，C选择错误。线程休眠后，处于阻塞状态，D选择错误。正确答案是B。 ', '11'), ('140', '在Java中，以下关于创建线程和启动线程的说法错误的是（）（单选）', 'A：通过继承java.lang.Thread类的方式可以创建线程类*B：通过实现java.lang.Runnable接口的方式可以创建线程类*D：已调用start()方法启动后的线程对象，可再次调用start()方法程序不会出错', 'D', '本题主要考查线程的创建和启动，线程类的定义有继承Thead类和实现Runable接口两种方式，通过实现run()方法编写线程体，已启动的线程不能重复调用start()方法，否则会报IllegalThreadStateException异常。正确答案为D。', '11'), ('141', '在Java中，以下关于线程的说法错误的是（）（单选）', 'A：调用join()方法可能抛出InterruptedException*B：sleep()方法是Thread类的静态方法*C：调用Thread类的sleep()方法可以终止一个线程对象*D：线程启动后执行的代码放在其run()方法中', 'C', '本题考察线程调度的几个方法。一个线程对象执行Thread.sleep()方法后，进入阻塞状态，应选择c。 ', '11'), ('142', '在Java中，以下（）选项能够最精准描述synchronized关键字（单选）', 'A：允许两线程并行执行，而且互相通信*B：保证在某时刻只有一个线程可访问对象或方法*C：保证允许两个或更多线程同时开始和结束*D：解决线程执行效率低的问题', 'B', '本题考察对象synchronized关键字的理解。synchronized用来定义同步方法或同步代码块，解决多个线程共享资源时带来的问题，同步后，只允许一个线程进入同步方法或同步代码块。正确答案是b。 ', '11'), ('143', '在Java中，以下关于线程同步的说法错误的是（）（单选）', 'A：在静态方法可以使用synchronized关键字*B：一个运行时间比较唱的方法声明成synchronized会影响效率*C：当一个线程执行一个对象的同步代码块时，其他线程仍可以执行对象的非同步代码块*D：synchronized关键字也可以修饰类', 'D', '本题考察线程同步。synchronized关键字可以修饰方法和代码块，不可修改类。应选择d。 ', '11'), ('144', '使用Dom4j解析XML文件时，Element对象的（）方法用来获取节点的属性（单选）', 'A：addElement()*B：addAttribute()*C：clearContent()*D：attribute()', 'D', '此题目考察DOM4J解析XML文档，请注意，其解析XML文档的相应API和方法都是固定的，获取节点属性是通过Element的attribute()实现的，因此正确答案为D ', '12'), ('145', 'Dom4j解析xml文件的实现步骤，以下描述正确的是（）', 'A：导入Dom4j.jar包，指定要解析的xml文件，把xml文件转换为Document对象，获取节点属性或文本的值*B：导入Dom4j.jar包，指定要解析的xml文件，把xml文件转换为Element对象，获取节点属性或文本的值*C：导入Dom4j.jar包，指定要解析的xml文件，把xml文件转换为SaxRead对象，获取节点属性或文本的值*D：导入Dom4j.jar包，指定要解析的xml文件，把xml文件转换为Node对象，获取节点属性或文本的值', 'A', 'b,c,d都是把xml文件转为其他的对象是不正确的，正确的为Document对象 ', '12'), ('146', '若入栈顺序为A、B、C、D、E，则下列（）出栈序列是不可能的（单选）', 'A：ABCDE*B：BCDAE*C：CDBEA*D：DECAB', 'D', '略', '13'), ('147', '递归程序可借助于（）转化为非递归程序（单选）', 'A：线性表*B：队列*C：栈*D：数组', 'C', '略', '13'), ('148', '在计算递归函数时，如不用递归过程，应借助于（）这种数据结构（单选）', 'A：线性表*B：栈*C：队列*D：双向队列', 'B', '略', '13'), ('149', '若带头节点的链表只射尾结点指针，下列选择中（）最适于队列（单选）', 'A：单链表*B：双向链表*C：循环单链表*D：双向循环链表', 'C', '略', '13'), ('150', '栈和队列的一个共同点是（）（单选）', 'A：都是先进先出*B：都是先进后出*C：只允许在端点处插入和删除元素*D：没有共同点', 'C', '略', '13'), ('151', '为了缩短指令中某个地址段的位数，有效的方法是采取（）（单选）', 'A：立即寻址*B：变址寻址*C：间接寻址*D：寄存器寻址', 'C', '略', '14'), ('152', '某计算机字长是16位它的容量是64KB，按字节编址，它们寻址范围是（）（单选）', 'A：64K*B：32K*C：32K*D：16K', 'C', '略', '14'), ('153', '寄存器间接寻址方式中，操作数处在（）（单选）', 'A：通用寄存器*B：贮存单元*C：程序计数器*D：堆栈', 'B', '略', '14'), ('154', 'RISC是（）的简称（单选）', 'A：精简指令系统计算机*B：大规模集成电路*C：复杂指令计算机*D：超大规模集成电路', 'A', '略', '14'), ('155', '某一RAM芯片其容量为512*8位，除电源和接地端外该芯片引用的最少数目是（）（单选）', 'A：21*B：17*C：19*D：20', 'C', '略', '14');
COMMIT;

-- ----------------------------
-- Table structure for `result`
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '成绩编号' ,
`stuid`  int(11) NOT NULL COMMENT '学生学号' ,
`examname`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '试卷的编号' ,
`option`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '选项' ,
`score`  double NOT NULL COMMENT '分数' ,
`datetime`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '试卷提交的时间' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`stuid`) REFERENCES `student` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `stuid` (`stuid`) USING BTREE ,
INDEX `eid` (`examname`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin
COMMENT='成绩表'
AUTO_INCREMENT=18

;

-- ----------------------------
-- Records of result
-- ----------------------------
BEGIN;
INSERT INTO `result` VALUES ('17', '321', '士大夫', 'C*C*B*', '0', 'Thu Dec 26 22:56:23 CST 2019');
COMMIT;

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
`id`  int(11) NOT NULL COMMENT '学生的编号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生的姓名' ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码' ,
`address`  varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '地址' ,
`phone`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '电话' ,
`cid`  int(11) NOT NULL COMMENT '班级的编号' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`cid`) REFERENCES `classes` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `cid` (`cid`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin

;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1', '1', '1', '1', '1', '1'), ('2', '2', '2', '2', '15509101234', '3'), ('321', '321', '321', '123', '13266526375', '6'), ('20180102', '王安生', '123456', '广东广州', '13696964589', '1'), ('20180123', '陈国柏', '123456', '广东东莞', '13456982541', '1'), ('20180222', '张顺谷', '123456', '广东清远', '13561258748', '2'), ('20180225', '岳烈阳', '123456', '广东汕头', '13694587458', '2'), ('20180306', '孙立生', '123456', '广东湛江', '13694587698', '3'), ('20180312', '易江伟', '123456', '广东肇庆', '13689456987', '3'), ('20180421', '周驰', '123456', '广东潮州', '13684759875', '4'), ('20180426', '张伟森', '123456', '广东韶关', '13698745698', '4'), ('20180527', '邱一书', '123456', '广东茂名', '13584698745', '5'), ('20180528', '潘培燕', '123456', '广东汕尾', '13694578459', '5'), ('20180632', '陆崇峰', '123456', '广东阳江', '13698745612', '6'), ('20180633', '肖柏伟', '123456', '广东中山', '13645789411', '1'), ('20190101', '1', '1', '1', '18709091231', '4'), ('20190102', '111', '1', '1', '18781219123', '3'), ('20190103', 'xiao', '1', '11', '15521213123', '5'), ('20190104', '1231', '123', '123', '13423131123', '1'), ('20190202', '11', '2', '2', '15587481231', '3');
COMMIT;

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
`id`  int(11) NOT NULL COMMENT '老师的工号' ,
`name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名' ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_bin

;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES ('1', '1', '1'), ('20150502', '张三', '123456');
COMMIT;

-- ----------------------------
-- Auto increment value for `book`
-- ----------------------------
ALTER TABLE `book` AUTO_INCREMENT=5;

-- ----------------------------
-- Auto increment value for `chapter`
-- ----------------------------
ALTER TABLE `chapter` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for `classes`
-- ----------------------------
ALTER TABLE `classes` AUTO_INCREMENT=7;

-- ----------------------------
-- Auto increment value for `exampaper`
-- ----------------------------
ALTER TABLE `exampaper` AUTO_INCREMENT=112;

-- ----------------------------
-- Auto increment value for `major`
-- ----------------------------
ALTER TABLE `major` AUTO_INCREMENT=4;

-- ----------------------------
-- Auto increment value for `question`
-- ----------------------------
ALTER TABLE `question` AUTO_INCREMENT=156;

-- ----------------------------
-- Auto increment value for `result`
-- ----------------------------
ALTER TABLE `result` AUTO_INCREMENT=18;
