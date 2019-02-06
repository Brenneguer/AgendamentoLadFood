package agendamento.dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import agendamento.VisitaTecnica;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Exportar {
	public void exportarExcel(String ondeSalvar, List<VisitaTecnica> lista) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Lad Food");
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
				XSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue("Tecnico");
				row.createCell(1).setCellValue("Tipo");
				row.createCell(2).setCellValue("Numero Chamado");
				row.createCell(3).setCellValue("Tarefa Pai");
				row.createCell(4, CellType.NUMERIC).setCellValue("Data Inicio");
				
				row.createCell(5).setCellValue("Data Fim");
				row.createCell(6).setCellValue("Valor");
				row.createCell(7).setCellValue("Situação");
				row.createCell(8).setCellValue("Cobrada");
				row.createCell(9).setCellValue("Observação / Cobrança");
				i++;
			}
			if (i > 0) {
				XSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(v.getTecnico());
				row.createCell(1).setCellValue(v.getTipo());
				row.createCell(2).setCellValue(v.getNumeroChamado());
				row.createCell(3).setCellValue(v.getEmpresa());
				row.createCell(4).setCellValue(v.getDataInicio().format(format));
				row.createCell(5).setCellValue(v.getDataFim().format(format));
				row.createCell(6).setCellValue(v.getValor());
				row.createCell(7).setCellValue(v.getSituacao());
				row.createCell(8).setCellValue(v.getLad());
				row.createCell(9).setCellValue(v.getObs());
				i++;
			}
		}
		try (FileOutputStream file = new FileOutputStream(new File(ondeSalvar))) {
			workbook.write(file);

			Alert alert = new Alert(AlertType.INFORMATION, "Salvo em C:\\Agendamento\\lad food\\lista visita.xlsx");
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
