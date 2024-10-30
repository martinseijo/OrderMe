package orderme.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orderme.entities.User;
import orderme.repository.UserRepository;
import orderme.service.AuthService;
import orderme.service.JwtService;
import orderme.service.dto.AuthResponseDto;
import orderme.service.dto.LoginRequestDto;
import orderme.service.dto.RegisterRequestDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private  final UserRepository userRepository;

    private final JwtService jwtService;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        return null;
    }


    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto request) {
        try {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .build();

            userRepository.save(user);

            return AuthResponseDto.builder()
                    .token(jwtService.getToken(user))
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            throw new UsernameNotFoundException("This user is already registered");
        }
    }
}
