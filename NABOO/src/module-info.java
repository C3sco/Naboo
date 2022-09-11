module NABOO {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires com.google.gson;
	requires javafx.fxml;
	requires rome;
	requires telegrambots;
	requires telegrambots.meta;
	requires com.fasterxml.jackson.databind;

	

	opens application to javafx.graphics, javafx.fxml, com.google.gson, javafx.base;
}
