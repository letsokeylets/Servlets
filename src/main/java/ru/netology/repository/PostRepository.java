package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

/**
 * Класс реализующий репозиторий
 */
public interface PostRepository {

    /**
     * Метод для вывода всех элементов Post
     */
    List<Post> all();

    /**
     * Метод для получения Post по id
     */
    Optional<Post> getById(long id);

    /**
     * Метод для сохранения нового Post или перезапись существующего
     */
    Post save(Post post);

    /**
     * Метод для удаления Post по id
     */
    void removeById(long id);

}
