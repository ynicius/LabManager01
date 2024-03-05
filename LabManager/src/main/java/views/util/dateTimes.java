package views.util;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAccessor;

import javax.swing.JOptionPane;

public class dateTimes {

	String txt_formulario;
	String txt_saida;
	String idade;
	String data_nascimento;
	LocalDate dataAtual = LocalDate.now();
	LocalDateTime dataAtual1 = LocalDateTime.now();
	private String model_data_form = "dd/MM/yyyy";
	private String model_data_banco = "yyyy-MM-dd";
	private String model_DateTime = "yyyy-MM-dd HH:mm";
	private int dia, mes, ano;
	private int anos, meses, dias;
	private boolean val_data;

	// Edição do formato da data e hora desejado para captura do formulario
	DateTimeFormatter formataData_entrada = DateTimeFormatter.ofPattern(model_data_form);

	// Edição do formato conforme especificação do Banco de dados
	DateTimeFormatter formataData_banco = DateTimeFormatter.ofPattern(model_data_banco);

	// Edição do formato conforme especificação do Banco de dados
	DateTimeFormatter formataDateTime_banco = DateTimeFormatter.ofPattern(model_DateTime);

	public dateTimes(String txt_formulario) {
		super();
		this.txt_formulario = txt_formulario;

	}

	public dateTimes() {

	}

	// conversão de string para a data e no formado do banco
	public String conveter_data_string() {
		// conversão 1/2: Aqui ocorre a conversão da String para tipo genérico
		TemporalAccessor parse01 = formataData_entrada.parse(txt_formulario);

		// conversão 2/2: Aqui ocorre a coversão de tipo genérico para Data.
		LocalDate data_entrada_convert = LocalDate.from(parse01);

		// Conversão de data para String formatada
		txt_saida = data_entrada_convert.format(formataData_banco);

		return txt_saida;
	}

	public Date data_data() {

		// conversão 1/2: Aqui ocorre a conversão da String para tipo genérico
		TemporalAccessor parse01 = formataData_banco.parse(txt_formulario);

		// conversão 2/2: Aqui ocorre a coversão de tipo genérico para Data.
		Date data_entrada_convert = (Date) Date.from((Instant) parse01);

		return data_entrada_convert;

	}

	public String calcula_idade() {
		// variavél tipo generico recebe a string do formulario
		TemporalAccessor data_nasc = formataData_entrada.parse(txt_formulario);

		// conversão do tipo generico em variável 'localDate'.
		LocalDate data_nascimento = LocalDate.from(data_nasc);

		// calculo da idade
		Period periodo = Period.between(data_nascimento, dataAtual);

		idade = (periodo.getYears() + "a");
		return idade;
	}

	// Validação junto com a coversão de estring para data e no formato do banco.
	public boolean valida_data() {
		String[] parts_data = txt_formulario.split("/");
		dia = Integer.valueOf(parts_data[0]);
		mes = Integer.valueOf(parts_data[1]);
		ano = Integer.valueOf(parts_data[2]);

		if (ano < 1900 || ano > 2999 || mes < 1 || mes > 12 || dia < 1 || dia > 31 || ano == 0 || mes == 0
				|| dia == 0) {
			// System.out.println("Data Invalida");
			JOptionPane.showMessageDialog(null, "Por favor digite uma data válida!");
			return val_data = false;
		} else if ((dia <= 31) && (mes <= 12)) {
			if (((dia > 28) && (mes == 2))
					|| ((dia == 31) && ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)))) {

				JOptionPane.showMessageDialog(null, "Por favor digite uma data válida!");
				return val_data = false;
			} else {
				// Caíra nesse bloco se for válida!
				// System.out.println("Data válida");
				// calcula_idade();
				return val_data = true;
			}
		} else {
			throw new IllegalArgumentException("Data inválida!");
		}
	}
	// ***

	// Calculo data de nascimento a partir da idade
	public String calc_dataNacimento(String anos) {
		this.anos = Integer.valueOf(anos);

		// calculo da idade

		if (this.anos > 150) {
			JOptionPane.showMessageDialog(null, "Por favor digite uma idade valida!");
		}
		LocalDate dt_nasc = dataAtual.minusYears(this.anos);
		data_nascimento = dt_nasc.format(formataData_entrada);
		return data_nascimento;

	}

	public String convertDateAtualText() {

		// Conversão de data para String formatada
		txt_saida = this.dataAtual1.format(formataDateTime_banco);
		return txt_saida;
	}

}
