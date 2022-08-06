module NABOO {
	requires javafx.controls;
	requires telegrambots;
	requires telegrambots.meta;
	requires com.google.gson;
	requires javafx.fxml;

	opens application to javafx.graphics, javafx.fxml;
}
