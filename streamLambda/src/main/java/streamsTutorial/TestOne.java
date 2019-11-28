package streamsTutorial;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestOne {

    @Test
    public void regular(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Julián");
        names.add("Alejandro");
        names.add("Oscar");
        names.add("Agustina");
        names.add("Mauro");

        int count = 0;

        for(int i = 0; i<names.size(); i++){
            String actual = names.get(i);
            if(actual.startsWith("A")){
                count++;
            }
        }

        System.out.println(count);
    }

    @Test
    public void streamFilter(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Julián");
        names.add("Alejandro");
        names.add("Oscar");
        names.add("Agustina");
        names.add("Mauro");

        Long countArray = names.stream().filter(name -> name.startsWith("A")).count();
        System.out.println("Count using an array: " + countArray);

        Long countStream = Stream.of("Julián", "Alejandro", "Oscar", "Agustina", "Mauro").filter(name -> name.startsWith("A")).count();
        System.out.println("Count using only a stream: " + countStream);

        Long countStreamLambda = Stream.of("Julián", "Alejandro", "Oscar", "Agustina", "Mauro").filter(name -> {
            name.startsWith("A");
            return true;
        }).count();
        System.out.println("Count using only a stream with a lambda function: " + countStreamLambda);

        System.out.println("Names with 6 or more words: ");
        names.stream().filter(name -> name.length() > 5).forEach(name -> System.out.println(name));
    }

    @Test
    public void streamMap(){
        ArrayList<String> namesOne = new ArrayList<String>();
        namesOne.add("Diego");
        namesOne.add("Augusto");
        namesOne.add("Rodrigo");
        namesOne.add("Mariano");
        namesOne.add("Dario");

        System.out.println("Names ending with 'a' in upper case mode:");
        Stream.of("Julián", "Alejandro", "Oscar", "Agustina", "Mauro").filter(name -> name.endsWith("a")).map(name -> name.toUpperCase())
                .forEach(name -> System.out.println(name));

        System.out.println("Names starting with 'a' and ordered in upper case: ");
        List<String> namesTwo = Arrays.asList("Julián", "Alejandro", "Oscar", "Agustina", "Mauro");
        namesTwo.stream().filter(name -> name.startsWith("A")).sorted().map(name -> name.toUpperCase())
                .forEach(name -> System.out.println(name));

        Stream<String> bothStream = Stream.concat(namesOne.stream(), namesTwo.stream());

        System.out.println("Two names list mixed in one stream, sorted and printed:");
        //bothStream.sorted().forEach(name -> System.out.println(name));

        String nameToSearch = "agustina";
        boolean nameFounded = bothStream.anyMatch(name -> name.equalsIgnoreCase(nameToSearch));
        System.out.println("Is '" + nameToSearch + "' name in the array? : " + nameFounded);

    }

    @Test
    public void streamCollect(){
        List<String> stringList = Stream.of("Julián", "Alejandro", "Oscar", "Agustina", "Mauro").filter(name -> name.startsWith("A"))
                .map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println(stringList.get(0));

        List<Integer> values = Arrays.asList(3,4,5,7,9,4,4,1,3);
        System.out.println("Values of array: ");
        values.stream().distinct().sorted().forEach(number -> System.out.println(number));

        List<Integer> valuesSorted = values.stream().distinct().sorted().collect(Collectors.toList());
        System.out.println("The 3rd value is: " + valuesSorted.get(2));

    }
}
