package dsa_psets.Set2;

import org.junit.Test;

/**
 In the children's game hot potato, a group of n children sit in a circle passing an object, called the potato,
 around the circle (say in a clockwise direction). The children continue passing the potato until a leaders rings a bell,
 at which point the child holding the potato must leave the game, and the other children close up the circle.
 This process is then continued until there is only one child remaining, who is declared the winner.
 Suppose the leader always rings the bell immediately after the potato has been passed k times.

 Describe (in pseudo code) an efficient method for implementing this game
 Implement this game
 Use the Sequence ADT
 Program two different implementations of the Sequence ADT, one using doubly-linked list, the other one using an array
 You don't have to implement operations you are not going to use
 Answer the question what the running time of the game in terms of n and k for each sequence implementation
 Let the program play the games given below and show the outcome:
 S=(Alice,Bob,Charles,Darwin,Ed,Fred,George,Holly,Irene), k=5
 S=(USA,Iraq,Syria,Iran,France,Germany,England), k=8
 S=(Mike), k=2
 S=(23,34,104,1025,2,15,27), k=4
 S=(2.3,3.4,10.4,10.25,2.2,1.5,2.7), k=6
 */
public class Tester {
    /**
     * Doubly linked list tests, array tests are after
     */
    @Test
    public void test1_DLL(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT1<>();
        seq.add("Alice");
        seq.add("Bob");
        seq.add("Charles");
        seq.add("Darwin");
        seq.add("Ed");
        seq.add("Fred");
        seq.add("George");
        seq.add("Holly");
        seq.add("Irene");
        String result = game.playHotPotato(seq,5);
        System.out.println("The champion is " + result);
        assert result.equals("George");
    }

    @Test
    public void test2_DLL(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT1<>();
        seq.add("USA");
        seq.add("Iraq");
        seq.add("Syria");
        seq.add("Iran");
        seq.add("France");
        seq.add("Germany");
        seq.add("England");
        String result = game.playHotPotato(seq,8);
        System.out.println("The champion is " + result);
        assert result.equals("England");
    }

    @Test
    public void test3_DLL(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT1<>();
        seq.add("Mike");
        String result = game.playHotPotato(seq,2);
        System.out.println("The champion is " + result);
        assert result.equals("Mike");
    }

    @Test
    public void test4_DLL(){
        HotPotatoGame<Integer> game = new HotPotatoGame<>();
        SequenceADT<Integer> seq = new SequenceADT1<>();
        seq.add(23);
        seq.add(32);
        seq.add(104);
        seq.add(1025);
        seq.add(2);
        seq.add(15);
        seq.add(27);
        Integer result = game.playHotPotato(seq,4);
        System.out.println("The champion is " + result);
        assert result == 15;
    }

    @Test
    public void test5_DLL(){
        HotPotatoGame<Double> game = new HotPotatoGame<>();
        SequenceADT<Double> seq = new SequenceADT1<>();
        seq.add(2.3);
        seq.add(3.4);
        seq.add(10.4);
        seq.add(10.25);
        seq.add(2.2);
        seq.add(1.5);
        seq.add(2.7);
        Double result = game.playHotPotato(seq,6);
        System.out.println("The champion is " + result);
        assert result == 2.2;
    }

    /**
     * Array Sequence ADT tests
     */
    @Test
    public void test1_Arr(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT2<>();
        seq.add("Alice");
        seq.add("Bob");
        seq.add("Charles");
        seq.add("Darwin");
        seq.add("Ed");
        seq.add("Fred");
        seq.add("George");
        seq.add("Holly");
        seq.add("Irene");
        String result = game.playHotPotato(seq,5);
        System.out.println("The champion is " + result);
        assert result.equals("George");
    }

    @Test
    public void test2_Arr(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT2<>();
        seq.add("USA");
        seq.add("Iraq");
        seq.add("Syria");
        seq.add("Iran");
        seq.add("France");
        seq.add("Germany");
        seq.add("England");
        String result = game.playHotPotato(seq,8);
        System.out.println("The champion is " + result);
        assert result.equals("England");
    }

    @Test
    public void test3_Arr(){
        HotPotatoGame<String> game = new HotPotatoGame<>();
        SequenceADT<String> seq = new SequenceADT2<>();
        seq.add("Mike");
        String result = game.playHotPotato(seq,2);
        System.out.println("The champion is " + result);
        assert result.equals("Mike");
    }

    @Test
    public void test4_Arr(){
        HotPotatoGame<Integer> game = new HotPotatoGame<>();
        SequenceADT<Integer> seq = new SequenceADT2<>();
        seq.add(23);
        seq.add(32);
        seq.add(104);
        seq.add(1025);
        seq.add(2);
        seq.add(15);
        seq.add(27);
        Integer result = game.playHotPotato(seq,4);
        System.out.println("The champion is " + result);
        assert result == 15;
    }

    @Test
    public void test5_Arr(){
        HotPotatoGame<Double> game = new HotPotatoGame<>();
        SequenceADT<Double> seq = new SequenceADT2<>();
        seq.add(2.3);
        seq.add(3.4);
        seq.add(10.4);
        seq.add(10.25);
        seq.add(2.2);
        seq.add(1.5);
        seq.add(2.7);
        Double result = game.playHotPotato(seq,6);
        System.out.println("The champion is " + result);
        assert result == 2.2;
    }
}
