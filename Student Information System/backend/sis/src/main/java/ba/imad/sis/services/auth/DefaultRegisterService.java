package ba.imad.sis.services.auth;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.RegisterRequest;
import ba.imad.sis.repositories.UserRepository;
import ba.imad.sis.services.professorinformation.ProfessorInformationService;
import ba.imad.sis.services.studentinformation.StudentInformationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DefaultRegisterService implements RegisterService {
    private final UserRepository userRepository;
    private final StudentInformationService studentInformationService;
    private final ProfessorInformationService professorInformationService;
    private final PasswordEncoder passwordEncoder;

    public DefaultRegisterService(UserRepository userRepository, StudentInformationService studentInformationService, ProfessorInformationService professorInformationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentInformationService = studentInformationService;
        this.professorInformationService = professorInformationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(RegisterRequest registerRequest) {
        boolean exists = userRepository.existsByEmail(registerRequest.email());

        if(exists) {
            throw new IllegalArgumentException("User with email " + registerRequest.email() + " already exists!");
        }

        User user = new User(registerRequest.email(), passwordEncoder.encode(registerRequest.password()), registerRequest.role());
        userRepository.save(user);

        if(Arrays.asList(user.getRole().split(",")).contains("STUDENT")){
            StudentInformation studentInformation = new StudentInformation(registerRequest.firstName(), registerRequest.lastName(), registerRequest.index(), registerRequest.enrollmentYear(), user);

            studentInformationService.saveStudentInformation(studentInformation);
        }
        else if(Arrays.asList(user.getRole().split(",")).contains("PROFESSOR")){
            ProfessorInformation professorInformation = new ProfessorInformation(registerRequest.firstName(), registerRequest.lastName(), user);

            professorInformationService.saveProfessorInformation(professorInformation);
        }

        return user;
    }
}
