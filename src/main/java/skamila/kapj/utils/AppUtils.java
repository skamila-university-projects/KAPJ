package skamila.kapj.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class AppUtils {

    public static String getUserLogin() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public static boolean isAnonymousUser() {
        List<String> auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(auth -> ((GrantedAuthority) auth).getAuthority()).collect(Collectors.toList());
        if (auths.contains("ROLE_ANONYMOUS")) {
            return true;
        } else {
            return false;
        }
    }

}
