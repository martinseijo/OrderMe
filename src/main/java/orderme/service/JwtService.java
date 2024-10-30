package orderme.service;

import orderme.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    String getToken(User user);
}
