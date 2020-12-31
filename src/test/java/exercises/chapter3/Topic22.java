package exercises.chapter3;

import org.junit.Test;

import java.util.Stack;

/**
 * 编写程序计算后缀表达式的值
 * 后缀表达式：6 5 2 3 + 8 * + 3 + *
 * 结果为：288
 * @author kubin
 * @version V1.0
 * @Package exercises.chapter3
 * @date 2020/12/18 9:29
 */
public class Topic22 {
    @Test
    public void test(){
        Integer value = getSuffixValue("6 5 2 3 + 8 * + 3 + *");
        System.out.println(value);
        String s = middleTosuffix("1 * ( 2 + 3 ) - 4 * 5");
        System.out.println(s);
    }

    /**
     * 计算后缀表达式的值
     * @param expression 后缀表达式
     * @return 后缀表达式的结果
     */
    public Integer getSuffixValue(String expression){
        Stack<Integer> stack = new Stack<Integer>();
        String[] strings = expression.split(" ");
        for (String s : strings) {
            try{
                Integer item = Integer.valueOf(s);
                stack.push(item);
            }catch(NumberFormatException e){
                // 操作符
                Integer i1 = stack.pop();
                Integer i2 = stack.pop();
//                Integer result = MyUtils.compute(i1,s,i2);
//                stack.push(result);
            }
        }
        return stack.pop();
    }

    /**
     * 将中序表达式转成后缀表达式
     * @param expression 中序表达式
     * @return 返回后缀表达式
     */
    public String middleTosuffix(String expression){
        Stack<String> stack = new Stack<String>();
        String[] strings = expression.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String s : strings) {
            try{
                Integer item = Integer.valueOf(s);
                sb.append(item);
            }catch(NumberFormatException e){
                // 操作符
                String option = stack.pop();
                if(option != null){
                    if("(".equals(option)){
                        stack.push(s);
                    }else{

                    }
                }else{
                    stack.push(s);
                }
            }
        }
        return null;
    }
}
