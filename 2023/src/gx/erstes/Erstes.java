package gx.erstes;

import gx.util.FileReader;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

class Erstes {
    public static void main(String[] args) {

//        String[] data = FileReader.readFile("01/01_input.txt").split("\n");
        String[] data = FileReader.readFile("01/01_input_meins.txt").split("\n");
//        String[] data = FileReader.readFile("01/01_02_input.txt").split("\n");

        var result = Arrays.stream(data)
                .map(Erstes::findFirstAndLast)
                .map(Integer::valueOf)
                .peek(x -> System.out.println("v: " + x))
                .reduce(0, Integer::sum);

        System.out.println("result: " + result);
    }

    private static String findFirstAndLast(String line){
        int firstValue = Integer.MAX_VALUE;
        int lastValue = Integer.MIN_VALUE;
        int firstIndex = Integer.MAX_VALUE;
        int lastIndex = Integer.MIN_VALUE;

        System.out.println("word is: " + line);
        //parse digits
        var bytes = line.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i <= bytes.length - 1; i++){
            if (isNumber(bytes[i])) {
                firstValue = Integer.parseInt(Character.toString((char)bytes[i]));
                firstIndex = i;
                break;
            }
        }

        for (int i = bytes.length - 1; i >= 0; i--) {
            if (isNumber(bytes[i])) {
                lastValue = Integer.parseInt(Character.toString((char)bytes[i]));
                lastIndex = i;
                break;
            }
        }

        System.out.println("f(@" +  firstIndex + "): " + firstValue + " l:(@" +  lastIndex + "): " + lastValue);

        //words
        for(Words word : Words.values()) {
            int index = line.indexOf(word.name);
            System.out.println("first index for " + word +" is: " + index);

            if (index >= 0) {
                System.out.println("first is: " + firstIndex);
                if(index < firstIndex){
                    firstIndex = index;
                    firstValue = word.value;
                }
                System.out.println("first index is now: " + firstIndex);
            }
        }

        for(Words word : Words.values()) {
            int index = line.lastIndexOf(word.name);
            System.out.println("last index for " + word +" is: " + index);

            if (index >= 0) {
                System.out.println("last is: " + lastIndex);
                if(index > lastIndex){
                    lastIndex = index;
                    lastValue = word.value;
                }
                System.out.println("last index is now: " + lastIndex);
            }
        }

        System.out.println("result: f(@" +  firstIndex + "): " + firstValue + " l:(@" +  lastIndex + "): " + lastValue);
        return "" + firstValue + lastValue;
    }

    private static boolean isNumber(byte value) {
        return value < 58 && value > 47;
    }
}