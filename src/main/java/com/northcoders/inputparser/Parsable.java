package com.northcoders.inputparser;

public interface Parsable<T> {
    public T parse(String inputInstruction);
}
