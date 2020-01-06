package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseBrainfuckCodeInterpreterTest {

  @Test
  void testProcess1() {
    // input
    String exampleCode = ",+[-.,+]";
    String exampleInputData = "Codewars" + (char)255;
    ExerciseBrainfuckCodeInterpreter interpreter = new ExerciseBrainfuckCodeInterpreter(exampleCode);

    // expected
    String expected = "Codewars";

    // actual
    String actual = interpreter.process(exampleInputData);

    // compare
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testProcess2() {
    // input
    String exampleCode = ",[.[-],]";
    String exampleInputData = "Codewars" + (char)0;
    ExerciseBrainfuckCodeInterpreter interpreter = new ExerciseBrainfuckCodeInterpreter(exampleCode);

    // expected
    String expected = "Codewars";

    // actual
    String actual = interpreter.process(exampleInputData);

    // compare
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void testProcess3() {
    // input
    String exampleCode = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.";
    String exampleInputData = "";
    ExerciseBrainfuckCodeInterpreter interpreter = new ExerciseBrainfuckCodeInterpreter(exampleCode);

    // expected
    String expected = "Hello World!";

    // actual
    String actual = interpreter.process(exampleInputData);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void testProcess4() {
    // input
    String exampleCode = "+++++++++++" +
            ">+>>>>++++++++++++++++++++++++++++++++++++++++++++" +
            ">++++++++++++++++++++++++++++++++<<<<<<[>[>>>>>>+>" +
            "+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<[>++++++++++[-" +
            "<-[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]>[<<[>>>+<<<" +
            "-]>>[-]]<<]>>>[>>+>+<<<-]>>>[<<<+>>>-]+<[>[-]<[-]]" +
            ">[<<+>>[-]]<<<<<<<]>>>>>[+++++++++++++++++++++++++" +
            "+++++++++++++++++++++++.[-]]++++++++++<[->-<]>++++" +
            "++++++++++++++++++++++++++++++++++++++++++++.[-]<<" +
            "<<<<<<<<<<[>>>+>+<<<<-]>>>>[<<<<+>>>>-]<-[>>.>.<<<" +
            "[-]]<<[>>+>+<<<-]>>>[<<<+>>>-]<<[<+>-]>[<+>-]<<<-]";
    String exampleInputData = "";
    ExerciseBrainfuckCodeInterpreter interpreter = new ExerciseBrainfuckCodeInterpreter(exampleCode);

    // expected
    String expected = "1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89";

    // actual
    String actual = interpreter.process(exampleInputData);

    // compare
    Assertions.assertEquals(expected, actual);
  }

}