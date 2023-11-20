package tools;

import java.util.Scanner;

public class InputProtection {
    public static int intInput(int beginRange, int endRange){
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        do{
        try {
            number = scanner.nextInt();
            scanner.nextLine();
            
        }catch(Exception e){
            scanner.nextLine();
            System.out.print("input a number you dipshit: ");
            continue;
        }
        if(number >= beginRange && number <= endRange){
        return number;
        }else{
            System.out.printf("input not that high or low number you dipshit(%d .. %d): ",beginRange, endRange);
            continue;
        }
        }while(true);
    }
}
