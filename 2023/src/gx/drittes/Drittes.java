package gx.drittes;

import gx.util.FileReader;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

class Drittes {
    public static void main(String[] args) {

//        String[] arrayData = FileReader.readFile("03/03_example.txt").split("\n");
        String[] arrayData = FileReader.readFile("03/03_meins.txt").split("\n");
        Map<Integer, Map<Integer, List<String>>> indexMap = new HashMap<>();

        for (int i = 0; i < arrayData.length; i++) {
            var result = findPartsWIthIndex(arrayData[i]);
            if (!result.isEmpty()){
                indexMap.put(i, result);
            }
        }

        var result = indexMap.entrySet().stream()
//                .peek(entry -> System.out.println("validating: " + entry))
                .map(entry -> hasSymbol(entry, arrayData))
                .peek(entry -> System.out.println("valid: " + entry))
                .flatMap(Collection::stream)
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);

        System.out.println("result: " + result);
    }

    private static List<String> hasSymbol(Map.Entry<Integer, Map<Integer, List<String>>> entries, String[] data) {
        int offset = data[0].length();

        //find arrays
        int arrayIndexA = Math.max((entries.getKey() - 1), 0);
        int arrayIndexB = entries.getKey() % offset;
        int arrayIndexC = Math.min(entries.getKey() + 1, data.length - 1);

        var above = data[arrayIndexA].getBytes(StandardCharsets.UTF_8);
        var same = data[arrayIndexB].getBytes(StandardCharsets.UTF_8);
        var below = data[arrayIndexC].getBytes(StandardCharsets.UTF_8);

        List<String> result = new ArrayList<>();

        entries.getValue().forEach((key, value) -> {

            // set loop range
            // index -1 + length +2
            int start = key % offset;
            int end = key % offset + value.size() - 1;

            int startIndex = start - 1 < 0 ? 0 : start - 1;
            int endIndex = end + 1 > offset ? offset : end + 1;

            for (int i = startIndex; i <= endIndex; i++) {

                if (isNotNumberOrDot(above[i]) || isNotNumberOrDot(same[i]) || isNotNumberOrDot(below[i])) {
                    result.add(String.join("", value));
                }
            }
        });
        return result;
    }

    private static  Map<Integer, List<String>> findPartsWIthIndex(String data) {
//        System.out.println("input is: " + data);

        Map<Integer, List<String>> indexMap = new HashMap<>();
        List<String> foundNumber = new ArrayList<>();

        boolean newIndex = true;
        int index = 0;

        var bytes = data.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < bytes.length; i++) {

            if(isNumber(bytes[i])) {
                if(newIndex){
                    newIndex = false;
                    index = i;
                }
                foundNumber.add("" + (char)bytes[i]);
            }
            else if(!newIndex){
                newIndex = true;
                indexMap.put(index, foundNumber);
                foundNumber = new ArrayList<>();
            }
        }

        return indexMap;
    }
    private static boolean isNumber(byte value) {
        return value < 58 && value > 47;
    }

    private static boolean isNotNumberOrDot(byte value) {
        return value < 46 && value > 31 || value == 47 || value < 127 && value > 57;
    }
}