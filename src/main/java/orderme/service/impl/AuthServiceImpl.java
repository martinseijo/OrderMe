package orderme.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orderme.entities.Role;
import orderme.entities.User;
import orderme.repository.RoleRepository;
import orderme.repository.UserRepository;
import orderme.service.AuthService;
import orderme.service.JwtService;
import orderme.service.dto.AuthResponseDto;
import orderme.service.dto.LoginRequestDto;
import orderme.service.dto.RegisterRequestDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static orderme.service.enums.Role.ROLE_USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private  final UserRepository userRepository;

    private final JwtService jwtService;

    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponseDto login(LoginRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
            final String token = jwtService.getToken(user);
            return AuthResponseDto.builder().token(token).build();
        } catch (BadCredentialsException e){
            log.error(e.getMessage());
            throw new UsernameNotFoundException("Login failed. Please check your credentials and try again");
        }
    }


    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto request) {
        try {

            final Role role = roleRepository.findByName(ROLE_USER.getName()).orElseThrow(() -> new RuntimeException("Role not found"));

            User user = User.builder()
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .role(role)
                    .enabled(true)
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
