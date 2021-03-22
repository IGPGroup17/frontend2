package com.example.personalprofile.repositories;

public interface IAdapter<T> {
    T adapt(String json);
}
