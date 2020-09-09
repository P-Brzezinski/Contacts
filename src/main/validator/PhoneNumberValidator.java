package main.validator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
1. The phone number should be split into groups using a space or dash. One group is also possible.
2. Before the first group, there may or may not be a plus symbol.
3. The first group or the second group can be wrapped in parentheses,
    but there should be no more than one group which is wrapped in parentheses.
    There may be no groups wrapped in parentheses.
4. A group can contain numbers, uppercase, and lowercase English letters.
    A group should be at least 2 symbols in length. But the first group may be only one symbol in length.
 */

public class PhoneNumberValidator {

    public String checkNumber(String number){
        if (isValid(number)){
            return number;
        }else {
            System.out.println("Wrong number format!");
            return "[no number]";
        }
    }

    private static boolean isValid(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(\\+?\\(\\w+\\)[-\\s]+\\w{2,}|\\+?\\w+[-\\s]+\\(?\\w{2,}\\)?)([-\\s]\\w{2,})*|[\\+?\\(?[\\w]\\)?]*$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true){
            String numner = scanner.nextLine();
            System.out.println(isValid(numner));
        }
    }
}
