package skamila.kapj.service;

import skamila.kapj.domain.AppUser;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {

    void generatePdf(AppUser appUser, HttpServletResponse response);

}
