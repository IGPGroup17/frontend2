package com.example.personalprofile.util;

// simple utility adapter functional interface
@FunctionalInterface
public interface IAdapter<T, V> {
    V adapt(T t);
}
