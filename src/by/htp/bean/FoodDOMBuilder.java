package by.htp.bean;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FoodDOMBuilder {
	private Set<Food> foods;
	private DocumentBuilder docBuilder;
	public FoodDOMBuilder(){
		this.foods = new HashSet<Food>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			docBuilder = factory.newDocumentBuilder();
		}catch(ParserConfigurationException e){
			System.out.println("Ошибка конфигурации парсера: " + e);
		}
	}
	public Set<Food> getFoods(){
		return foods;
	}
	
	public void buildSetFoods(String fileName){
		Document doc = null;
		try{
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			
			NodeList foodsList = root.getElementsByTagName("resources/Menu.xml");
			for(int i=0; i<foodsList.getLength();i++){
				Element foodElement = (Element) foodsList.item(i);
				Food food = buildFood(foodElement);
				foods.add(food);
			}
		}catch (IOException e){
			System.err.println("File error or I/O error: " + e);
		}catch (SAXException e){
			System.err.println("Parsing failure: " + e);
		}
	}
	
	private Food buildFood(Element foodElement){
		Food food = new Food();
		
		Integer id = Integer.parseInt(getElementTextContent(foodElement, "id"));
		
		return food;
	}
	
	private static String getElementTextContent(Element element, String elementName){
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
	

}
