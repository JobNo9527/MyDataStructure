package Valid_Parentheses;

/**
 * @author 将晖
 */


public class Solution {

    public boolean isValid(String s){

        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '['||c=='{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;

                char topChar = stack.pop();
                if (c == ')' && topChar != '(')
                    return false;
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
            }
        }

        //只有当stack.isEmpty()为空的时候，也就是为TRUE的时候，字符串才算匹配完成
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean v = (new Solution()).isValid("()[]{}");
        System.out.println(v);
        boolean v1 = (new Solution()).isValid("()[]{");
        System.out.println(v1);
    }
}
