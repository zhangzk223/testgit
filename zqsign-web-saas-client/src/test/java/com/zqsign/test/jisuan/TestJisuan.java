package com.zqsign.test.jisuan;

public class TestJisuan {

	 public static void main(String[] args) {
		 //位已运算 ^ 转二进制后从高位开始，相同为0，不同为1
		 System.out.println(8^9); //1
		 //位与运算符 & 转二进制后从高位开始，两个数都为1则等于1，否则0
		 System.out.println(12&15);//12
		 //位或运算符 | 转二进制后从高位开始，只要有一个为1,则等于1，否则0
		 System.out.println(12|15);//15
		 //位非运算符 ~
		 System.out.println(~14);  //-15
		 
		 //位移运算
		 //00001000
		 //00100000
		 System.out.println(8<<3);
		 //01000110
		 //00001000
		 System.out.println(70>>3);//8
		 System.out.println(70>>>3);//8
		 //11000110  -70
		 //01000110   70
		 //11110111
		 System.out.println(-70>>3);
//       String str = toBinary(11);
//       System.out.println(str);
		 
     }
 
     static String toBinary(int num) {
         String str = "";
         while (num != 0) {
             str = num % 2 + str;
             num = num / 2;
         }
         return str;
     }
	
}
