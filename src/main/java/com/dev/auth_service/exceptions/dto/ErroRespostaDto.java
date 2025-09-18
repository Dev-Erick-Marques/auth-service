package com.dev.auth_service.exceptions.dto;

import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class ErroRespostaDto {

	private String mensagem;
	private int status;
	private String path;
	private Instant timestamp;
	private List<ErroCampoDto> erros;
	
	public ErroRespostaDto(String mensagem, int status, String path) {
		this.mensagem = mensagem;
		this.status = status;
		this.path = path;
		this.timestamp = Instant.now();
	}
	
	public ErroRespostaDto(String message, int status, String path, List<ErroCampoDto> erros) {
		this(message, status, path);
		this.erros = erros;
	}
}
