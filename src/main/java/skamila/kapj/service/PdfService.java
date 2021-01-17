package skamila.kapj.service;

import skamila.kapj.domain.Visit;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {

    void generatePdf(Visit visit, HttpServletResponse response);

}
