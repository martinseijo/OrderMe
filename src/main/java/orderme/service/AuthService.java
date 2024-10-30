package orderme.service;

import orderme.service.dto.AuthResponseDto;
import orderme.service.dto.LoginRequestDto;
import orderme.service.dto.RegisterRequestDto;

public interface AuthService {
    AuthResponseDto login(LoginRequestDto request);

    AuthResponseDto register(RegisterRequestDto request);
}
