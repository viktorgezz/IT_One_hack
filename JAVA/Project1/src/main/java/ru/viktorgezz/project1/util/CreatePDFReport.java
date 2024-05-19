package ru.viktorgezz.project1.util;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.viktorgezz.project1.model.Category;
import ru.viktorgezz.project1.model.Expense;
import ru.viktorgezz.project1.services.CategoryService;
import ru.viktorgezz.project1.services.ExpenseService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

@Component
public class CreatePDFReport {

    private final CategoryService categoryService;
    private final ExpenseService expenseService;

    @Autowired
    public CreatePDFReport(CategoryService categoryService, ExpenseService expenseService) {
        this.categoryService = categoryService;
        this.expenseService = expenseService;
    }

    public byte[] getPdfFiles(int idAccount) throws IOException {
        String title = "report.pdf";

        PdfWriter pdfWriter = new PdfWriter(title);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        PdfFont font = PdfFontFactory.createFont("src/main/resources/fonts/FreeSans.ttf", PdfEncodings.IDENTITY_H, true);
        Document document = new Document(pdfDocument);
        document.setFont(font);

        HashMap<String, Double> categories = categoryService.getSumCategories(idAccount);
        document.add(new Paragraph("Отчёт по категориям"));
        for (String titleCategory : categories.keySet()) {
            document.add(new Paragraph(titleCategory + " " + categories.get(titleCategory) + " руб"));
        }

        document.add(new Paragraph());
        document.add(new Paragraph("Траты"));
        List<Expense> expenses = expenseService.getListExpense(idAccount);
        int sizeExpense = expenses.size() > 10 ? 10 : expenses.size();
        for (int i = 0; i < sizeExpense; i++) {
            document.add(new Paragraph(expenses.get(i).getTitle() + " " + expenses.get(i).getAmount() + " руб "
                    + expenses.get(i).getCategory().getTitle()));
        }

        document.close();
        return Files.readAllBytes(Path.of(title));
    }
}
