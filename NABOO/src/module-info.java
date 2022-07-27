module NABOO {
	requires javafx.controls;
	requires telegrambots;
	requires telegrambots.meta;
	
	opens application to javafx.graphics, javafx.fxml;
}
