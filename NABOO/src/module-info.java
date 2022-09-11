module NABOO {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires telegrambots;
	requires telegrambots.meta;
	requires com.google.gson;
	requires javafx.fxml;
	requires rome;
	requires slf4j;

	opens application to javafx.graphics, javafx.fxml, com.google.gson, javafx.base;
}
