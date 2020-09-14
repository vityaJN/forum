package forum.messenger.Services;

import forum.messenger.entity.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, String> {

}
