package gx.erstes;

import gx.util.FileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Erstes {
    public static void main(String[] args) throws URISyntaxException, IOException {

//        String[] data = FileReader.readFile("01_input.txt").split("\n");
        String[] data = FileReader.readFile("01_input_meins.txt").split("\n");

        int result = Arrays.stream(data)
                .map(Erstes::findFirstAndLast)
                .map(Integer::valueOf)
                .peek(System.out::println)
                .reduce(0, Integer::sum);

        System.out.println("result: " + result);
    }

    private static String findFirstAndLast(String line){
        int first = Integer.MAX_VALUE;
        int last = Integer.MIN_VALUE;

        //parse digits
        var bytes = line.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i <= bytes.length - 1; i++){
            if (isNumber(bytes[i])) {
                first = Integer.parseInt(Character.toString((char)bytes[i]));
                break;
            }
        }

        for (int i = bytes.length - 1; i >= 0; i--) {
            if (isNumber(bytes[i])) {
                last = Integer.parseInt(Character.toString((char)bytes[i]));
                break;
            }
        }


        return "" + first + last;
    }

    private static boolean isNumber(byte value) {
        return value < 58 && value > 47;
    }
}