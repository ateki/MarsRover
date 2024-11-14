package com.northcoders;

import com.northcoders.inputparser.InitInstructionParser;
import com.northcoders.inputparser.MoveInstructionParser;
import com.northcoders.inputparser.StartInstructionParser;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello lets begin roving around Mars!");

        // Temp test of 3 diff input parsers:
        InitInstructionParser  initParser = new InitInstructionParser();
        initParser.parse("test");

        StartInstructionParser startParser = new StartInstructionParser();
        startParser.parse("test");

        MoveInstructionParser moveParser = new MoveInstructionParser();
        moveParser.parse("test");
    }
}