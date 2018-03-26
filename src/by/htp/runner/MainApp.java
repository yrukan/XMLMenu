package by.htp.runner;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.bean.Food;
import by.htp.bean.MenuSaxHandler;

public class MainApp {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		MenuSaxHandler handler = new MenuSaxHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource("resources/Menu.xml"));
		
		List<Food> menu = handler.getFoodList();
		
		for(Food food: menu){
			System.out.println(food.getName());
		}

	}

}
