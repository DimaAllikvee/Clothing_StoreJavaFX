package interfaces;



import java.util.Optional;


public interface AppService<T> {
    Optional<T> add(T t);
}

