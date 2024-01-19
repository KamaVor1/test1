import java.util.Scanner;

class Testovoe {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int X1;
        int X2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Должно быть выражение");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("Несуществующая математическая операция");
        if (Rim.isRoman(operands[0]) && Rim.isRoman(operands[1])) {
            X1 = Rim.convertToArabian(operands[0]);
            X2 = Rim.convertToArabian(operands[1]);
            isRoman = true;
        }
        else if (!Rim.isRoman(operands[0]) && !Rim.isRoman(operands[1])) {
            X1 = Integer.parseInt(operands[0]);
            X2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (X1 > 10 || X2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(X1, X2, oper);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Rim.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Rim {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}

