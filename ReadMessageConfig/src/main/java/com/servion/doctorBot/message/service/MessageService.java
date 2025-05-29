package com.servion.doctorBot.message.service;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.servion.doctorBot.exception.ConfigLoadException;
import com.servion.doctorBot.message.pojo.MessagePOJO;
import com.servion.doctorBot.util.ValidationUtil;

public class MessageService {

	private static MessageService instance;
	private final MessagePOJO config;


	public static synchronized MessageService getInstance(String file) {
		if (instance == null) {
			instance = new MessageService(file);
		}
		return instance;
	}

	public String getMessage() {
		return config.getMessage();
	}

	private MessageService(String file) {
		InputStream is = null;
		try {
			is = getInputStream(file);
			if (is == null) {
				throw new ConfigLoadException("YML file not found at path: " + file);
			}
			this.config = getConfig(is);
		} 
		catch (JsonMappingException e) {
			StringBuilder errorMsg = new StringBuilder("YAML structure mapping error:\n");
			errorMsg.append(e.getOriginalMessage()).append("\n");

			if (e.getLocation() != null) {
				errorMsg.append("At line: ").append(e.getLocation().getLineNr())
				.append(", column: ").append(e.getLocation().getColumnNr()).append("\n");
			}

			e.getPath().forEach(ref ->
			errorMsg.append(" -> at field: ").append(ref.getFieldName()).append("\n"));
			throw new ConfigLoadException(errorMsg.toString(), e);
		} 
		catch (JsonParseException e) {
			throw new ConfigLoadException("YAML syntax error in file '" + file + "': " + e.getOriginalMessage(), e);
		}
		catch (Exception e) {
			throw new ConfigLoadException(e.getMessage());
		}
		finally {
			closeInputStream(is);
		}
	}

		private InputStream getInputStream(String file) {
			InputStream is = null;
			ValidationUtil.requireNonBlank(file, "YML file cannot be null empty or blank");
			is = getClass().getClassLoader().getResourceAsStream(file);
			return is;
		}
		
		private void closeInputStream(InputStream is) {
			if (is != null) {
				try {
					is.close();
				} 
				catch (IOException e) {
					// log or ignore
				}
			}
		}


		private MessagePOJO getConfig(InputStream is) throws StreamReadException, DatabindException, IOException {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			MessagePOJO config = mapper.readValue(is, MessagePOJO.class);
			return config;
		}


	}


