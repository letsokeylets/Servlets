package ru.netology.repository;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Класс реализующий репозиторий
 */
public class PostRepository {

    private AtomicLong numId = new AtomicLong(0);
    private ConcurrentHashMap<Long, Post> listPost = new ConcurrentHashMap();

    /**
     * Метод для вывода всех элементов Post
     */
    public List<Post> all() {
        return new ArrayList<>(listPost.values());
    }

    /**
     * Метод для получения Post по id
     */
    public Optional<Post> getById(long id) {
        if (listPost.containsKey(id)) {
            return Optional.of(listPost.get(id));
        }
        throw new NotFoundException("Не найдена запись с id = " + id);
    }

    /**
     * Метод для сохранения нового Post или перезапись существующего
     */
    public Post save(Post post) {
        long id = post.getId();
        if (id == 0) {
            post = new Post(numId.incrementAndGet(), post.getContent());
            listPost.put(post.getId(), post);
        } else {
            if (listPost.containsKey(id)) {
                listPost.put(id, post);
            } else {
                throw new NotFoundException("Не найдена запись с id = " + id);
            }
        }
        return post;
    }

    /**
     * Метод для удаления Post по id
     */
    public void removeById(long id) {
        if (listPost.containsKey(id)) {
            listPost.remove(id);
        } else {
            throw new NotFoundException("Не найдена запись с id = " + id);
        }
    }

}
