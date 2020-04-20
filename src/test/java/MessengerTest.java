import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

public class MessengerTest {
    private Messenger messenger;
    private Client client;
    private MailServer mailserver;
    private Template template;
    private TemplateEngine templateEngine;

    @Test
    public void test_send_msg_null_client(){
        client = mock(Client.class);
        mailserver= mock(MailServer.class);
        template = mock(Template.class);
        templateEngine = mock(TemplateEngine.class);
        messenger = new Messenger(mailserver, templateEngine);

        assertThatThrownBy(() -> messenger.sendMessage(null, template)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void test_send_msg_mock(){
        client = mock(Client.class);
        mailserver= mock(MailServer.class);
        template = mock(Template.class);
        templateEngine = mock(TemplateEngine.class);
        messenger = new Messenger(mailserver, templateEngine);

        String message = "string";
        String email = "email@gmail.com";

        when(client.getEmail()).thenReturn(email);
        when(templateEngine.prepareMessage(template,client))
                .thenReturn(message);

        messenger.sendMessage(client, template);

        verify(templateEngine).prepareMessage(template,client);
        verify(mailserver).send(email,message);

    }

    @Test
    public void test_send_msg_spy(){
        client = spy(Client.class);
        mailserver = spy(MailServer.class);
        template = spy(Template.class);
        templateEngine = spy(TemplateEngine.class);
        messenger = new Messenger(mailserver, templateEngine);

        String message = "string";
        String email = "email@gmail.com";

        when(client.getEmail()).thenReturn(email);
        when(templateEngine.prepareMessage(template, client)).thenReturn(message);
        messenger.sendMessage(client, template);

        verify(templateEngine).prepareMessage(template,client);
        verify(mailserver).send(email, message);

    }
}
