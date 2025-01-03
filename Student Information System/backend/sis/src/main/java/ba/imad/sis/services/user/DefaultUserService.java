package ba.imad.sis.services.user;

import ba.imad.sis.domain.ProfessorInformation;
import ba.imad.sis.domain.StudentInformation;
import ba.imad.sis.domain.User;
import ba.imad.sis.dtos.RegisterRequest;
import ba.imad.sis.repositories.UserRepository;
import ba.imad.sis.services.course.CourseService;
import ba.imad.sis.services.enrollment.EnrollmentService;
import ba.imad.sis.services.professorinformation.ProfessorInformationService;
import ba.imad.sis.services.studentinformation.StudentInformationService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DefaultUserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final StudentInformationService studentInformationService;
    private final ProfessorInformationService professorInformationService;
    private final EnrollmentService enrollmentService;
    private final CourseService courseService;

    public DefaultUserService(UserRepository userRepository, StudentInformationService studentInformationService, ProfessorInformationService professorInformationService, EnrollmentService enrollmentService, CourseService courseService) {
        this.userRepository = userRepository;
        this.studentInformationService = studentInformationService;
        this.professorInformationService = professorInformationService;
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No user found with email: " + email));
    }

    public User getCurrentUser(){
        return loadUserByUsername(getCurrentUserEmail());
    }

    public String getCurrentUserEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public boolean isAuthenticated() {
        return !getCurrentUserEmail().equals("anonymousUser");
    }

    @Transactional
    public void updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);

        userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("No user found with id: " + id));

        if(Arrays.asList(user.getRole().split(",")).contains("STUDENT")){
            studentInformationService.deleteStudentInformation(id);
            enrollmentService.deleteAllStudentEnrollments(id);
        }
        else if(Arrays.asList(user.getRole().split(",")).contains("PROFESSOR")){
            professorInformationService.deleteProfessorInformation(id);
            courseService.updateProfessorToNull(id);
        }

        userRepository.delete(user);
    }
}
