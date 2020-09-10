package com.swagger.SwaggerAggregator.service;

import static com.swagger.SwaggerAggregator.utils.IConstants.HTML_FILE_EXT;
import static com.swagger.SwaggerAggregator.utils.IConstants.PDF_FILE_EXT;
import static com.swagger.SwaggerAggregator.utils.IConstants.coverPageImg;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.Placement;
import org.asciidoctor.SafeMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.Swagger2MarkupConverter.Builder;
import io.github.swagger2markup.Swagger2MarkupProperties;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import io.swagger.util.Json;
import io.swagger.util.Yaml;

@Service
public class SwaggerServiceImpl implements SwaggerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerServiceImpl.class);

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${UPLOADED_FOLDER}")
	private String uploadFolder;

	public String fetchSwagger(String serviceName, String serviceVersion, String URL) {
		String swaggerJSON = null;
		try {
			ResponseEntity<String> apiDocResponse = restTemplate.getForEntity(URL, String.class);
			swaggerJSON = apiDocResponse.getBody();
			swaggerJSON = formatString(swaggerJSON);

			if (createSwagger(serviceName, swaggerJSON, Paths.get(uploadFolder))) {
				LOGGER.info(".json and .yml files with name " + serviceName + " generated at " + uploadFolder);
			}
			LOGGER.info("Swagger JSON for requested service:-" + serviceName);
		} catch (RestClientException ex) {
			LOGGER.error("Error while getting service definition for service : {} Error : {} " + serviceName + " "
					+ ex.getMessage());
			swaggerJSON = EMPTY;
		}
		return swaggerJSON;
	}

	public boolean generateDocuments(String swaggerJSON, String URL, String serviceName) {
		boolean status = Boolean.FALSE;
		try {
			generateAsciDoc(swaggerJSON, serviceName, URL);
			generatePdf(serviceName);
			generateHtml(serviceName);

			status = Boolean.TRUE;
		} catch (Exception e) {
			LOGGER.error("Error while generating the documents :: " + e.getLocalizedMessage());
			status = Boolean.FALSE;
		}
		return status;
	}

	private static String formatString(String inputString) {
		inputString = inputString.replace("\\", "");
		inputString = inputString.replace("\\", "");

		inputString = inputString.replace("\"[", "[");
		inputString = inputString.replace("]\"", "]");

		inputString = inputString.replace("\"{", "{");

		inputString = inputString.replace("}\"", "}");

		return inputString;
	}

	private boolean createSwagger(String fileName, String swaggerJson, Path swaggerTargetFolder) {
		boolean status = Boolean.FALSE;
		try {
			String json = Json.pretty().writeValueAsString(swaggerJson);
			LOGGER.debug("JSON format of the swagger :: " + json);
			Path jsonFile = swaggerTargetFolder.resolve(fileName + ".json");
			Files.write(jsonFile, swaggerJson.getBytes(StandardCharsets.UTF_8));

			String yaml = Yaml.mapper().writeValueAsString(swaggerJson);
			LOGGER.debug("YAML format of the swagger :: " + yaml);
			Path yamlFile = swaggerTargetFolder.resolve(fileName + ".yaml");
			Files.write(yamlFile, swaggerJson.getBytes(StandardCharsets.UTF_8));

			status = Boolean.TRUE;
		} catch (IOException e) {
			status = Boolean.FALSE;
			LOGGER.error("Error while creating JSON and YAML files for swagger :: " + e.getLocalizedMessage());
		}
		return status;
	}

	private void generateAsciDoc(String json, String fileName, String URL)
			throws Exception, UnsupportedEncodingException {

		ResponseEntity<String> apiDocResponse = null;
		String swaggerJson = "";
		try {
			apiDocResponse = restTemplate.getForEntity(URL, String.class);
			swaggerJson = apiDocResponse.getBody();
			LOGGER.info("Rest Template call successful for " + URL);
		} catch (RestClientException | IllegalArgumentException e) {
			swaggerJson = json;
			LOGGER.error("Rest Template call un-successful for :: " + URL + e.getLocalizedMessage());
		}

		Builder builder = Swagger2MarkupConverter.from(swaggerJson);

		Map<String, String> configMap = new HashMap<>();
		configMap.put(Swagger2MarkupProperties.MARKUP_LANGUAGE, MarkupLanguage.ASCIIDOC.toString());
		configMap.put(Swagger2MarkupProperties.OUTPUT_LANGUAGE, Language.EN.toString());
		configMap.put(Swagger2MarkupProperties.PATHS_GROUPED_BY, GroupBy.TAGS.toString());
		configMap.put(Swagger2MarkupProperties.INLINE_SCHEMA_ENABLED, Boolean.TRUE.toString());
		configMap.put(Swagger2MarkupProperties.INTER_DOCUMENT_CROSS_REFERENCES_ENABLED, Boolean.TRUE.toString());
		configMap.put(Swagger2MarkupProperties.GENERATED_EXAMPLES_ENABLED, Boolean.TRUE.toString());
		configMap.put(Swagger2MarkupProperties.OVERVIEW_DOCUMENT, Boolean.TRUE.toString());

		Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder(configMap).build();

		builder.withConfig(config);

		Swagger2MarkupConverter converter = builder.build();
		converter.toFile(Paths.get(uploadFolder + fileName));
		Path path = Paths.get(uploadFolder + fileName + ".adoc");
		Charset charset = StandardCharsets.UTF_8;

		String content = new String(Files.readAllBytes(path), charset);
		content = ":title-logo-image: /images/" + coverPageImg + "\n\n" + content;
		Files.write(path, content.getBytes(charset));
		LOGGER.info(".adoc file " + fileName + " generated at " + uploadFolder);
	}

	private void generatePdf(String fileName) {
		createDocument(fileName, PDF_FILE_EXT, "erubis", "");
		LOGGER.info(".pdf file" + fileName + " generated at " + uploadFolder);
	}

	private void generateHtml(String fileName) {
		createDocument(fileName, HTML_FILE_EXT, "", "true");
		LOGGER.info(".html file" + fileName + " generated at " + uploadFolder);
	}

	private void createDocument(String fileName, String fileExt, String eruby, String idprefix) {
		Asciidoctor asciidoctor = Asciidoctor.Factory.create();
		OptionsBuilder optionsBuilder = OptionsBuilder.options();
		try {
			optionsBuilder.destinationDir(new File(uploadFolder + fileName + ".pdf"));
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
		optionsBuilder.option(Attributes.BACKEND, fileExt);
		optionsBuilder.option(Attributes.SOURCE_HIGHLIGHTER, "coderay");
		optionsBuilder.option(Attributes.DOCTYPE, "book");
		optionsBuilder.option(Attributes.TOC, "left");
		optionsBuilder.option(Attributes.TOC_POSITION, Placement.RIGHT.getPosition());

		if (StringUtils.isNotBlank(eruby)) {
			// Set only in case of .pdf
			optionsBuilder.eruby(eruby);
		}
		optionsBuilder.compact(true);
		optionsBuilder.safe(SafeMode.SAFE);
		optionsBuilder.option("pagenums", "left");
		optionsBuilder.option("revnumber", "1.0");

		Map<String, Object> attributes = new HashMap<>();
		attributes.put(Attributes.ICONS, "font");
		attributes.put(Attributes.TOC, "left");
		attributes.put(Attributes.SET_ANCHORS, "true");
		String now = DateFormatUtils.format(new Date(), "dd MMM yyyy");
		attributes.put("revdate", now);
		attributes.put("numbered", "true");
		attributes.put("pagenums", "right");
		attributes.put("docinfo1", "true");
		attributes.put("idseparator", "true");
		if (StringUtils.isNotBlank(idprefix)) {
			// Set only in case of .html
			attributes.put("idprefix", "true");
		}
		optionsBuilder.attributes(attributes);
		// convert .adoc file to pdf
		File file = new File(uploadFolder + fileName + ".adoc");
		LOGGER.info("Fetch the .adoc file :: " + file.getAbsolutePath());
		asciidoctor.convertFile(new File(uploadFolder + fileName + ".adoc"), optionsBuilder);
	}
}
