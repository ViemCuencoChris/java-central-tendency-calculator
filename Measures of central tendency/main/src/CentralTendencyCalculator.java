import java.util.Scanner;
import java.util.InputMismatchException;

public class CentralTendencyCalculator {
    public static void main(String[] args) {
        System.out.println("Welcome to measures of central tendency calculator");
        
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running){
            try {
                System.out.println("""
                        \nDo you wish to run the program?
                        1) Yes
                        2) No
                        """);
                
                System.out.print("Enter choice: ");
                int choice = input.nextInt();

                switch(choice){
                    case 1:
                        break;
                    case 2:
                        System.out.println("\nThank you and Goodbye!");
                        return;
                    default:
                        System.out.println("\nInvalid Choice!\n");
                        continue;
                }
                
                System.out.print("\nEnter range of values: ");
                int range = input.nextInt();
                System.out.println();

                if (range <= 0) {
                    System.out.println("Range must be greater than 0.");
                    continue;
                } 

                int[] numbers = new int[range];

                for(int i = 0; i < numbers.length; i++){
                    System.out.print("Enter number: ");
                    numbers[i] = input.nextInt();
                }

                System.out.println("""
                    \nKindly choose the operation:
                    1) Mean
                    2) Median
                    3) Mode
                    4) Exit
                        """);
                
                System.out.print("Enter option: ");
                int operation = input.nextInt();

                switch(operation){
                    case 1:
                        System.out.printf("\nThe mean is %.1f%n", mean(numbers));
                        break;
                    case 2:
                        System.out.printf("\nThe median is %.1f%n", median(numbers));
                        break;
                    case 3:
                        Integer result = mode(numbers);
                        System.out.println("\nThe mode is " + (result == null ? "none" : result));
                        break;
                    case 4:
                        System.out.printf("\nThank you and Goodbye!");
                        return;
                    default:
                        System.out.printf("\nInvalid Option!\n");
                        continue;
                }

            } catch (InputMismatchException e){
                System.out.println("\nEnter a valid number!\nTry Again\n");
                input.nextLine();
            }
        }

        input.close();
    }

    static double mean(int[] numbers){
        double sum = 0;

        for(int i = 0; i < numbers.length; i++){
            sum += numbers[i];
        }

        return sum / numbers.length;
    }

    static double median(int[] numbers){
        int[] copyNumbers = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            copyNumbers[i] = numbers[i];
        }

        for(int i = 0; i < copyNumbers.length - 1; i++){
            for(int j = 0; j < copyNumbers.length - 1 - i; j++){
                if (copyNumbers[j] > copyNumbers[j+1]){
                    int temp = copyNumbers[j];
                    copyNumbers[j] = copyNumbers[j+1];
                    copyNumbers[j+1] = temp;
                }
            }
        }

        if(copyNumbers.length % 2 != 0){
            int term = (copyNumbers.length + 1) / 2;
            return copyNumbers[term - 1];
        } else {
            int firstTerm = copyNumbers[copyNumbers.length / 2 - 1];
            int secondTerm = copyNumbers[copyNumbers.length / 2];
            return (firstTerm + secondTerm) / 2.0;
        }
    }

    static Integer mode(int[] numbers){
        Integer mode = null;
        int maxCount = 1;

        for(int i = 0; i < numbers.length; i++){
            int count = 0;

            for(int j = 0; j < numbers.length; j++){
                if(numbers[i] == numbers[j]){
                    count++;
                }
            }

            if(count > maxCount){
                maxCount = count;
                mode = numbers[i];
            }
        }

       return mode;
    }
}
