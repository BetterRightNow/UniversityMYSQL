package mappers;

import java.util.List;

public interface IMapper<T> {
    void add(T t);

    List<T> getAll();

    T getById(int id);

    void updateById(T t);

    void deleteById(int id);
}
