package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.service.EmailTemplateService;
import com.nosy.admin.nosyadmin.service.InputSystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailAdminControllerTest {

    @InjectMocks
    EmailAdminController emailAdminController;

    @Mock
    EmailTemplateService emailTemplateService;

    @Mock
    InputSystemService inputSystemService;
    @Test
    public void emailTemplatePost() {
        EmailTemplateDto emailTemplateDto=new EmailTemplateDto();
        emailTemplateDto.setSubject("TestSubject");
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.
                emailTemplatePost("dasda", "dasdas", null,
                        principal).getStatusCode());
        assertEquals("TestSubject",emailTemplateDto.getSubject());
    }

    @Test
    public void newType() {
        InputSystemDto inputSystemDto=new InputSystemDto();
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.CREATED, emailAdminController.newType(inputSystemDto,principal).getStatusCode());

    }

    @Test
    public void getInputSystems() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.getInputSystems(principal).getStatusCode());
    }

    @Test
    public void getEmailAllProviders() {
        Principal principal=mock(Principal.class);
        when(emailTemplateService.getAllEmailProviders()).thenReturn(null);
        assertEquals(HttpStatus.OK, emailAdminController.getEmailAllProviders(principal).getStatusCode());
    }

    @Test
    public void updateInputSystemName() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.updateInputSystemName("inputSystemId", null, principal).getStatusCode());
    }

    @Test
    public void newEmailTemplate() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.CREATED, emailAdminController.
                newEmailTemplate("inputSystemId",null,principal).getStatusCode());
    }

    @Test
    public void getEmailTemplateByInputSystemAndEmailTemplateId() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.OK, emailAdminController.
                getEmailTemplateByInputSystemAndEmailTemplateId("emailTemplateId","inputSystemId",principal).getStatusCode());
    }

    @Test
    public void updateEmailTemplate() {
        Principal principal=mock(Principal.class);

        assertEquals(HttpStatus.OK, emailAdminController.
                updateEmailTemplate( "inputSystemId","emailTemplateId", null, principal)
                .getStatusCode());
    }


    @Test
    public void getEmailTemplates() {
        Principal principal=mock(Principal.class);

        assertEquals(HttpStatus.OK, emailAdminController.
                getEmailTemplates("inputSystemId", principal).getStatusCode());
    }

    @Test
    public void deleteEmailTemplate() {
        Principal principal=mock(Principal.class);

        assertEquals(HttpStatus.NO_CONTENT, emailAdminController.
                deleteEmailTemplate("inputSystemId", "emailTemplateId",principal).getStatusCode());
    }

    @Test
    public void deleteInputSystem() {
        Principal principal=mock(Principal.class);

        assertEquals(HttpStatus.NO_CONTENT, emailAdminController.
                deleteInputSystem("inputSystemId", principal).getStatusCode());
    }
}


