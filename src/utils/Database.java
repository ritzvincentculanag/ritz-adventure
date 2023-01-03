package utils;

import models.Character;

import java.util.List;

public interface Database<T> {
    List<Character> getAll();
}
