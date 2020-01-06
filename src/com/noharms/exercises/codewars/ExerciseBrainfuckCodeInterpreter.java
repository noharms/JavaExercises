package com.noharms.exercises.codewars;

import java.util.ArrayList;
import java.util.List;

public class ExerciseBrainfuckCodeInterpreter {

  private String instructions;
  private List<Byte> data = new ArrayList<Byte>();
  int instructionPtr = 0;
  int dataPtr = 0;

  public ExerciseBrainfuckCodeInterpreter(String code) {
    instructions = code;
    data.add((byte)0);
  }

  public String process(String inputstream) {
    String output = "";
    int inputDataPtr = 0;
    while (instructionPtr < instructions.length()) {
      Character command = instructions.charAt(instructionPtr);
      if (command.equals('>')) {
        moveForward();
      } else if (command.equals('<')) {
        moveBackward();
      } else if (command.equals('+')) {
        increment();
      } else if (command.equals('-')) {
        decrement();
      } else if (command.equals('.')) {
        output = appendByteToOutput(output);
      } else if (command.equals(',')) {
        if (inputDataPtr < inputstream.length()) {
          writeByte((byte) inputstream.charAt(inputDataPtr++));
        } else {
          writeByte((byte)0);
          //System.out.println("Code expects more input than given.");
        }
      } else if (command.equals('[')) {
        processWhileLoopStart();
      } else if (command.equals(']')) {
        processWhileLoopEnd();
      } else {
        // Syntax error in code ! unknown command
        System.out.println("Syntax error in code. Unknown command: " + command);
      }
      ++instructionPtr;
    }
    return output;
  }

  private void moveForward() {
    if (dataPtr >= data.size() - 1) {
      data.add((byte) 0);
    }
    ++dataPtr;
  }
  private void moveBackward() {
    if (dataPtr > 0) {
      --dataPtr;
    }
  }

  private void increment() {
    Byte newVal = (byte) ((data.get(dataPtr) + 1) % 256);
    data.set(dataPtr, newVal);
  }

  private void decrement() {
    Byte newVal = (byte) ((data.get(dataPtr) - 1) % 256);
    data.set(dataPtr, newVal);
  }

  private String appendByteToOutput(String output) {
    return output + (char)(data.get(dataPtr) & 0xFF);
  }

  private void writeByte(Byte b) {
    while (dataPtr >= data.size()) {
      data.add((byte)0);
    }
    data.set(dataPtr, b);
  }

  private void processWhileLoopStart() {
    if (data.get(dataPtr).equals((byte)0)) {
      int openSquareBrackets = 0;
      while (instructionPtr  < instructions.length()) {
        if (instructions.charAt(instructionPtr) == '[') {
          ++openSquareBrackets;
        } else if (instructions.charAt(instructionPtr) == ']') {
          --openSquareBrackets;
        }
        if (openSquareBrackets == 0) {
          break;
        }
        ++instructionPtr;
      }
      if (instructionPtr  >= instructions.length()) {
        // given code contains syntax error ! no matching ']' found
        System.out.println("Syntax error in code. No matching ] found.");
      }
    }
  }

  private void processWhileLoopEnd() {
    if (data.get(dataPtr).compareTo((byte)0) != 0) {
      int openSquareBrackets = 0;
      while (instructionPtr  >= 0) {
        if (instructions.charAt(instructionPtr) == ']') {
          ++openSquareBrackets;
        } else if (instructions.charAt(instructionPtr) == '[') {
          --openSquareBrackets;
        }
        if (openSquareBrackets == 0) {
          break;
        }
        --instructionPtr;
      }
      if (instructionPtr < 0) {
        // given code contains syntax error ! no matching ']' found
        System.out.println("Syntax error in code. No matching [ found.");
      }
    }
  }

}
