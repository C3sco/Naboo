module NABOO {
	requires javafx.controls;
	requires telegrambots;
	requires telegrambots.meta;
	requires com.google.gson;
	requires javafx.fxml;
	requires rome;

	opens application to javafx.graphics, javafx.fxml;
}
