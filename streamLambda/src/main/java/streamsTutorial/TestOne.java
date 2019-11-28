package streamsTutorial;

import org.testng.annotations.Test;

import java.util.ArrayList;
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


    }
}
