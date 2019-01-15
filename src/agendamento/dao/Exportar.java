package agendamento.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import agendamento.VisitaTecnica;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Exportar {
	public void exportarExcel(String ondeSalvar, List<VisitaTecnica> lista) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("alura");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		int i = 0;
		if (lista.isEmpty()) {
			try {
				workbook.close();
			} catch (IOException e) {
				e.getMessage();
			}
			throw new RuntimeException();
		}
		for (VisitaTecnica v : lista) {

			if (i == 0) {
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue("Tecnico");
				row.createCell(1).setCellValue("Tipo");
				row.createCell(2).setCellValue("Numero Chamado");
				row.createCell(3).setCellValue("Tarefa Pai");
				row.createCell(4).setCellValue("Data Inicio");
				row.createCell(5).setCellValue("Data Fim");
				row.createCell(6).setCellValue("Situação");
				row.createCell(7).setCellValue("Cobrada");
				i++;
			}
			if (i > 0) {
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(v.getTecnico());
				row.createCell(1).setCellValue(v.getTipo());
				row.createCell(2).setCellValue(v.getNumeroChamado());
				row.createCell(3).setCellValue(v.getIdEmpresa());
				row.createCell(4).setCellValue(v.getDataInicio().format(format));
				row.createCell(5).setCellValue(v.getDataFim().format(format));
				row.createCell(6).setCellValue(v.getSituacao());
				row.createCell(7).setCellValue(v.getLad());
				i++;
				System.out.println(i);
			}
		}
		try (FileOutputStream file = new FileOutputStream(new File(ondeSalvar))) {
			workbook.write(file);

			Alert alert = new Alert(AlertType.INFORMATION, "Salvo em C:\\Agendamento\\lista visita.xls");
			alert.setTitle("Exportar");
			alert.setHeaderText("Exportação Concluida.");
			alert.show();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
