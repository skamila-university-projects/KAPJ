package skamila.kapj.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import skamila.kapj.controller.utils.AppUserBuilder;
import skamila.kapj.domain.AppUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class MailServiceTest {

    private final JavaMailSender senderMock = mock(JavaMailSender.class);
    private final MailService mailService = new MailService(senderMock);
    private final ArgumentCaptor<SimpleMailMessage> argumentCaptor = ArgumentCaptor.forClass(SimpleMailMessage.class);

    @Test
    void sendMail() {
        // given
        AppUser appUser = new AppUserBuilder().withFirstName("Jan").withLastName("Kowalski").withEmail("test@test.com")
                .withToken("token").build();
        // when
        mailService.sendMail(appUser);
        // then
        Mockito.verify(senderMock).send(argumentCaptor.capture());
        SimpleMailMessage message = argumentCaptor.getValue();
        assertEquals(1, message.getTo().length);
        assertTrue(message.getTo()[0].equals(appUser.getEmail()));
        assertTrue(message.getText().contains(appUser.getFirstName()));
        assertTrue(message.getText().contains(appUser.getToken()));
        assertTrue(message.getText().contains("http://localhost:8080/activateAccount?token="));
    }
}